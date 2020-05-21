package com.cathy.challenge.keypadcombination.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Combinations {

    private final List<String> combinations;
    private int totalNumberOfCombinations;
    private final int pageOffset;

    public int getTotalNumberOfCombinations() {
        return combinations.size();
    }
}
