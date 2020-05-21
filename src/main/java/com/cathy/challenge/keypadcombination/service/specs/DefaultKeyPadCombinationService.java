package com.cathy.challenge.keypadcombination.service.specs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DefaultKeyPadCombinationService implements KeyPadCombinationService {

    private final Map<Character, List<String>> KEYPAD = ImmutableMap.<Character, List<String>>builder()
            .put('2', ImmutableList.of("a", "b", "c"))
            .put('3', ImmutableList.of("d", "e", "f"))
            .put('4', ImmutableList.of("g", "h", "i"))
            .put('5', ImmutableList.of("j", "k", "l"))
            .put('6', ImmutableList.of("m", "n", "o"))
            .put('7', ImmutableList.of("p", "q", "r", "s"))
            .put('8', ImmutableList.of("t", "u", "v"))
            .put('9', ImmutableList.of("w", "x", "y", "z"))
            .build();

    @Override
    public List<String> generateKeyPadCombinations(String input) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(input)) {
            return Collections.emptyList();
        }
        final StringBuilder combinationsPlaceHolder = new StringBuilder();
        aggregateCombinations(input, combinationsPlaceHolder, result);
        return result;
    }

    public void aggregateCombinations(String input, StringBuilder combinationPlaceHolder, List<String> combinations) {
        if (input.length() == combinationPlaceHolder.length()) {
            combinations.add(combinationPlaceHolder.toString());
            return;
        }
        final List<String> letters = KEYPAD.getOrDefault(input.charAt(combinationPlaceHolder.length()), Collections.emptyList());
        letters.stream().forEach(letter -> {
            combinationPlaceHolder.append(letter);
            aggregateCombinations(input, combinationPlaceHolder, combinations);
            combinationPlaceHolder.deleteCharAt(combinationPlaceHolder.length() - 1);
        });
    }

}
