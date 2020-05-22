package com.cathy.challenge.keypadcombination.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Combinations {

    private final String originalInput;
    private final List<String> combinationsList;
    private final int pageOffset;
    private final int combinationsPerPage;
    private int currentResults;
    private final int totalNumberOfCombinations;

    public int getCurrentResults() {
        return combinationsList.size();
    }
}
