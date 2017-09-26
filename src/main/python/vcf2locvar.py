#!/usr/bin/python

import sys

def prefixlen(a, b):
    for (i, (ca, cb)) in enumerate(zip(a, b)):
        if ca != cb:
            return (i, a[i:], b[i:])
    minlen = min(len(a), len(b))
    return (minlen, a[minlen:], b[minlen:])

def trim(a, b):
    plen, a, b = prefixlen(a, b)
    a, b = [x[::-1] for x in prefixlen(a[::-1], b[::-1])[1:]]
    return (plen, a, b)

for line in sys.stdin:
    if line.startswith("#"):
        continue
    row = line.rstrip().split("\t")
    (chrom, orig_pos, snpid, orig_ref, orig_alt) = line.rstrip().split("\t")[:5]
    orig_pos = int(orig_pos)
    alts = orig_alt.split(',')

    for orig_alt in alts:
        offset, ref, alt = trim(orig_ref, orig_alt)
        pos = orig_pos + offset
        end_pos = pos + len(ref)

        vartype = 'sub'
        if (len(ref) == len(alt) and len(alt) == 1):
            vartype = 'snp'
        elif len(ref) == 0:
            vartype = 'ins'
            # wierdness
            pos -= 1
        elif len(alt) == 0:
            vartype = 'del'
            # weirdness
            alt = ref
        print "\t".join([str(x) for x in (pos, end_pos, ref, alt, vartype)])
