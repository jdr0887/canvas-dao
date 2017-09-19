package org.renci.canvas.dao.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Scratch {

    @Test
    public void scratch() {

        String ref = "ACGG";
        String alt = "GCGG";

        // String alt = "A";

        List<Character> referenceChars = ref.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> alternateChars = alt.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        StringBuilder frontChars2Remove = new StringBuilder();
        StringBuilder backChars2Remove = new StringBuilder();

        List<Character> charsToRemoveFromRef = new ArrayList<>();

        if (StringUtils.isEmpty(ref) || StringUtils.isEmpty(alt)) {
            return;
        }

        if (referenceChars.size() == alternateChars.size()) {

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
                ref = ref.replaceFirst(frontChars2Remove.toString(), "");
                alt = alt.replaceFirst(frontChars2Remove.toString(), "");
            }

            if (backChars2Remove.length() > 0) {
                backChars2Remove.reverse();
                ref = StringUtils.removeEnd(ref, backChars2Remove.toString());
                alt = StringUtils.removeEnd(alt, backChars2Remove.toString());
            }

        }

    }
}
