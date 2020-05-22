package com.cathy.challenge.keypadcombination.service;

import com.cathy.challenge.keypadcombination.models.Combinations;
import com.cathy.challenge.keypadcombination.service.specs.KeyPadCombinationService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class keypadCombinationServiceTest {

    @Autowired
    public KeyPadCombinationService service;

    @Test
    public void shouldReturnEmptyListWhenInputIsEmpty() {
        String input = "";
        assertThat(service.generateKeyPadCombinations(input, 0,0).getCombinationsList()).isEmpty();

    }

    @Test
    public void shouldReturnEmptyListForOneOrZero(){
        String input = "01";
        assertThat(service.generateKeyPadCombinations(input, 0, 10).getCombinationsList()).isEmpty();
    }

    /**For interview purposes I wont validate all the digits, but in real world we should certainly validate all entries**/
    @Test
    public void shouldReturnThreeCombinations() {
        String input = "2";
        final ImmutableList<String> expectedCombinations = ImmutableList.of("a","b","c");
        assertThat(service.generateKeyPadCombinations(input, 0, 10))
                .matches(combinations -> combinations.getCombinationsList().containsAll(expectedCombinations)
                        && combinations.getTotalNumberOfCombinations() == 3);

    }

    @Test
    public void shouldReturnFiveCombinations() {
        String input = "23";
        final ImmutableList<String> expectedCombinations = ImmutableList.of("ae", "af", "bd", "be", "bf");
        Combinations combinations = service.generateKeyPadCombinations(input, 1,5);
        assertThat(combinations.getCombinationsList()).hasSameElementsAs(expectedCombinations);
        assertThat(combinations.getTotalNumberOfCombinations()).isEqualTo(9);
        assertThat(combinations.getCombinationsPerPage()).isEqualTo(5);
        assertThat(combinations.getCombinationsList()).hasSize(5);
    }

    @Test
    public void shouldReturnNineCombinations() {
        String input = "23";
        final ImmutableList<String> expectedCombinations = ImmutableList.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        Combinations combinations = service.generateKeyPadCombinations(input, 0,10);
        assertThat(combinations.getCombinationsList()).hasSameElementsAs(expectedCombinations);
        assertThat(combinations.getTotalNumberOfCombinations()).isEqualTo(9);
        assertThat(combinations.getCombinationsPerPage()).isEqualTo(10);
        assertThat(combinations.getCombinationsList()).hasSize(9);
    }

}
