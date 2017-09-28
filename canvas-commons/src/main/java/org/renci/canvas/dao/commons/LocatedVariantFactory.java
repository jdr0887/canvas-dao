package org.renci.canvas.dao.commons;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.renci.canvas.dao.ref.model.GenomeRef;
import org.renci.canvas.dao.ref.model.GenomeRefSeq;
import org.renci.canvas.dao.var.model.LocatedVariant;
import org.renci.canvas.dao.var.model.VariantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocatedVariantFactory {

    private static final Logger logger = LoggerFactory.getLogger(LocatedVariantFactory.class);

    public static LocatedVariant create(GenomeRef genomeRef, GenomeRefSeq genomeRefSeq, Integer position, String reference,
            String alternate, List<VariantType> allVariantTypes) {

        LocatedVariant locatedVariant = new LocatedVariant(genomeRef, genomeRefSeq);

        try {

            if (StringUtils.isEmpty(reference)) {
                logger.error("ref is empty");
                return null;
            }

            if (alternate == null) {
                alternate = "";
            }

            if (reference.equals(alternate)) {
                logger.error("reference and alternate are equal");
                return null;
            }

            Triple<Integer, String, String> positionReferenceAlternateTriple = trim(position, reference, alternate);

            Integer pos = positionReferenceAlternateTriple.getLeft();
            String ref = positionReferenceAlternateTriple.getMiddle();
            String alt = positionReferenceAlternateTriple.getRight();

            if (ref.length() == 1 && alt.length() == 1) {

                locatedVariant.setVariantType(allVariantTypes.stream().filter(a -> a.getId().equals("snp")).findAny().get());
                locatedVariant.setSeq(alt);
                locatedVariant.setRef(ref);
                locatedVariant.setPosition(pos);
                locatedVariant.setEndPosition(pos + locatedVariant.getRef().length());

            } else if (alt.length() == 0) {

                locatedVariant.setVariantType(allVariantTypes.stream().filter(a -> a.getId().equals("del")).findAny().get());
                locatedVariant.setRef(ref);
                locatedVariant.setSeq(ref);
                locatedVariant.setPosition(pos);
                locatedVariant.setEndPosition(locatedVariant.getPosition() + ref.length());

            } else if (ref.length() == 0) {

                locatedVariant.setVariantType(allVariantTypes.stream().filter(a -> a.getId().equals("ins")).findAny().get());
                locatedVariant.setRef("");
                locatedVariant.setSeq(alt);
                locatedVariant.setPosition(pos - 1);
                locatedVariant.setEndPosition(locatedVariant.getPosition() + 1);

            } else {

                locatedVariant.setVariantType(allVariantTypes.stream().filter(a -> a.getId().equals("sub")).findAny().get());
                locatedVariant.setRef(ref);
                locatedVariant.setSeq(alt);
                locatedVariant.setPosition(pos);
                locatedVariant.setEndPosition(locatedVariant.getPosition() + ref.length());

            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return locatedVariant;

    }

    private static int findPrefixLength(String a, String b) {
        int smallerLength = Math.min(a.length(), b.length());
        for (int i = 0; i < smallerLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return smallerLength;
    }

    private static Triple<Integer, String, String> trim(Integer position, String reference, String alternate) {

        int prefixLength = findPrefixLength(reference, alternate);

        String reversedRefWithoutPrefix = new StringBuilder(reference.substring(prefixLength)).reverse().toString();
        String reversedAltWithoutPrefix = new StringBuilder(alternate.substring(prefixLength)).reverse().toString();

        int suffixLength = findPrefixLength(reversedRefWithoutPrefix, reversedAltWithoutPrefix);

        return Triple.of(position + prefixLength, reference.substring(prefixLength, reference.length() - suffixLength),
                alternate.substring(prefixLength, alternate.length() - suffixLength));
    }

}
