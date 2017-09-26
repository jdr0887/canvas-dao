package org.renci.canvas.dao.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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

            if (StringUtils.isEmpty(alternate)) {
                logger.error("alt is empty");
                return null;
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

    private static Triple<Integer, String, String> trim(Integer position, String reference, String alternate) {

        List<Character> referenceChars = reference.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> alternateChars = alternate.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        StringBuilder frontChars2Remove = new StringBuilder();
        StringBuilder backChars2Remove = new StringBuilder();

        List<Character> charsToRemoveFromRef = new ArrayList<>();

        if (referenceChars.size() == alternateChars.size()) {
            logger.debug("referenceChars.size() == alternateChars.size(): {}", referenceChars.size() == alternateChars.size());

            for (int i = 0; i < referenceChars.size(); ++i) {
                if (referenceChars.get(i) != alternateChars.get(i)) {
                    break;
                }
                charsToRemoveFromRef.add(referenceChars.get(i));
                frontChars2Remove.append(referenceChars.get(i));
            }

            for (int i = 0; i < charsToRemoveFromRef.size(); ++i) {
                referenceChars.remove(charsToRemoveFromRef.get(i));
            }

            if (CollectionUtils.isNotEmpty(referenceChars)) {
                Collections.reverse(referenceChars);
                Collections.reverse(alternateChars);
                for (int i = 0; i < referenceChars.size(); ++i) {
                    if (referenceChars.get(i) != alternateChars.get(i)) {
                        break;
                    }
                    backChars2Remove.append(referenceChars.get(i));
                }
            }

            if (frontChars2Remove.length() > 0) {
                reference = reference.replaceFirst(frontChars2Remove.toString(), "");
                alternate = alternate.replaceFirst(frontChars2Remove.toString(), "");
            }

            if (backChars2Remove.length() > 0) {
                backChars2Remove.reverse();
                reference = StringUtils.removeEnd(reference, backChars2Remove.toString());
                alternate = StringUtils.removeEnd(alternate, backChars2Remove.toString());
            }

        } else if (referenceChars.size() > alternateChars.size()) {

            logger.debug("referenceChars.size() > alternateChars.size(): {}", referenceChars.size() > alternateChars.size());

            for (int i = 0; i < referenceChars.size(); ++i) {
                if (i == alternateChars.size() || referenceChars.get(i) != alternateChars.get(i)) {
                    break;
                }
                frontChars2Remove.append(referenceChars.get(i));
            }

            if (CollectionUtils.isNotEmpty(referenceChars) && CollectionUtils.isNotEmpty(alternateChars)) {

                String tmpRef = referenceChars.stream().map(a -> a.toString()).collect(Collectors.joining());
                tmpRef = tmpRef.replaceFirst(frontChars2Remove.toString(), "");
                referenceChars = tmpRef.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                String tmpAlt = alternateChars.stream().map(a -> a.toString()).collect(Collectors.joining());
                tmpAlt = tmpAlt.replaceFirst(frontChars2Remove.toString(), "");
                alternateChars = tmpAlt.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                Collections.reverse(referenceChars);
                Collections.reverse(alternateChars);
                for (int i = 0; i < referenceChars.size(); ++i) {
                    if (i == alternateChars.size() || referenceChars.get(i) != alternateChars.get(i)) {
                        break;
                    }
                    backChars2Remove.append(referenceChars.get(i));
                }
            }

            if (frontChars2Remove.length() > 0) {
                reference = reference.replaceFirst(frontChars2Remove.toString(), "");
                alternate = alternate.replaceFirst(frontChars2Remove.toString(), "");
            }

            if (backChars2Remove.length() > 0) {
                backChars2Remove.reverse();
                reference = StringUtils.removeEnd(reference, backChars2Remove.toString());
                alternate = StringUtils.removeEnd(alternate, backChars2Remove.toString());
            }

        } else if (referenceChars.size() < alternateChars.size()) {

            logger.debug("referenceChars.size() < alternateChars.size(): {}", referenceChars.size() < alternateChars.size());

            for (int i = 0; i < referenceChars.size(); ++i) {
                if (referenceChars.get(i) != alternateChars.get(i)) {
                    break;
                }
                charsToRemoveFromRef.add(referenceChars.get(i));
                frontChars2Remove.append(referenceChars.get(i));
            }

            for (int i = 0; i < charsToRemoveFromRef.size(); ++i) {
                logger.debug("removing referenceChars: {} at {}", charsToRemoveFromRef.get(i), i);
                referenceChars.remove(charsToRemoveFromRef.get(i));
            }

            if (CollectionUtils.isNotEmpty(referenceChars)) {
                Collections.reverse(referenceChars);
                Collections.reverse(alternateChars);
                for (int i = 0; i < referenceChars.size(); ++i) {
                    if (referenceChars.get(i) != alternateChars.get(i)) {
                        break;
                    }
                    backChars2Remove.append(referenceChars.get(i));
                }
            }

            if (frontChars2Remove.length() > 0) {
                reference = reference.replaceFirst(frontChars2Remove.toString(), "");
                alternate = alternate.replaceFirst(frontChars2Remove.toString(), "");
            }

            if (backChars2Remove.length() > 0) {
                backChars2Remove.reverse();
                reference = StringUtils.removeEnd(reference, backChars2Remove.toString());
                alternate = StringUtils.removeEnd(alternate, backChars2Remove.toString());
            }

        }

        position = position + (frontChars2Remove.length() > 0 ? frontChars2Remove.length() : 0);
        return Triple.of(position, reference, alternate);
    }

}
