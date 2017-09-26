Variants are represented in CANVAS as "locvars" (located variants). This representation predates
representations such as what is used in VCF or GA4GH (and may change in the future as other
specifications solidify within the community).

# Columns in the `var.loc_var` table

| column            |                                                                                          |
| ----------------- | ---------------------------------------------------------------------------------------- |
| loc_var_id        | generated primary key                                                                    |
| ref_id            | foreign key to ref.genome_ref for each genome assembly                                   |
| ref_ver_accession | chromosome (versioned accession) of the reference sequence                               |
| pos               | 1-based inclusive start position: the 1st base altered in the variant (EXCEPT for ins)   |
| type              | one of “ref”, “snp”, “ins”, “del”, “sub”                                                 |
| seq               | the “alt” sequence for a variant (EXCEPT for "del", where it is equal to the ref)        |
| end_pos           | 1-based exclusive end position (so the first base that is *not* affected by the variant) |
| ref               | reference sequence at the site enclosed by pos:end_pos (inclusive left, exclusive right) |


# The process of producing a loc_var:

## Inputs:
* persisted as input: ref_id, ref_ver_accession
* oneBasedStart (e.g., 1-based start position from VCF file, or VariantContext.getStart() )
* refAllele
* altAllele

1. Trimming:
   * Common prefix and suffix are removed from both refAllele and altAllele, leaving only the part that is “different” between the two alleles.
   * The ‘offset' for new start is calculated as the length of the prefix that is removed
   * EXCEPTION for “ref” alleles, which are not trimmed because by definition they ref == alt at these positions

2. Determination of ‘type’ (after trimming…):
   * “ref” variants will have trimmedRefAllele == trimmedAltAllele, else
   * “snp" variants will have len(trimmedRefAllele) == len(trimmedAltAllele) == 1, else
   * “ins” variants will have len(trimmedRefAllele) == 0, else
   * “del” variants will have len(trimmedAltAllele) == 0, else
   * other variants are of type “sub”

3. Representation of ref, seq, pos, end_pos:
   * “ref”:
      * ref = value of trimmedRefAllele (note: this will also be the base a position ‘pos’ in the reference sequence)
      * seq = value of trimmedAltAllele
      * pos = oneBasedStart + offset
      * end_pos = pos + length(ref) (note: this will always be pos+1 so long as “ref”-type alleles are always single-base
   * “snp”:
      * ref = value of trimmedRefAllele (note: this will also be the base at position ‘pos’ in the reference sequence)
      * seq = value of trimmedAltAllele
      * pos = oneBasedStart + offset (position of the changed base)
      * end_pos = pos + length(ref) = pos + 1
   * “ins”:
      * ref = value of trimmedRefAllele (which in this case happens to be the empty string)
      * seq = value of trimmedAltAllele
      * pos = oneBasedStart + offset - 1 (so pos is the base *before* the insertion)
      * end_pos = oneBasedStart = pos + 1 (end_pos is the position *after* the insertion)
   * “del”:
      * ref = value of trimmedRefAllele
      * seq = valued of trimmedRefAllele (NOTE: this is weird, because one might expect this to be the value of trimmedAltAllele, which would be the empty string)
      * pos = oneBasedStart + offset (position of the first deleted base)
      * end_pos = pos + length(ref) (position of the first undeleted base)
   * “sub”:
      * ref = value of trimmedRefAllele
      * seq = value of trimmedAltAllele
      * pos = oneBasedStart + offset
      * end_pos = pos + length(ref)

Things to be careful about//exceptions/inconsistencies:
1. pos is the input oneBasedStart plus the size of the removed prefix EXCEPT for “ins” type where this is decremented by 1
2. end_pos is pos + length(ref) for all types EXCEPT for “ins”, where you add 1 even though length(ref) is 0
3. ‘seq’ is the sequence that you would now see between pos and end_pos after the modification EXCEPT for “del” variants
