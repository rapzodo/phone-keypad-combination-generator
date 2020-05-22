package com.cathy.challenge.keypadcombination.service.specs;

import com.cathy.challenge.keypadcombination.models.Combinations;

public interface KeyPadCombinationService {

    Combinations generateKeyPadCombinations(String input, int pageOffSet, int pageSize);
}
