package com.cathy.challenge.keypadcombination;

import com.cathy.challenge.keypadcombination.service.specs.KeyPadCombinationService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class keypadCombinationServiceTest {

    @Autowired
    public KeyPadCombinationService service;

    @Test
    public void shouldReturnEmptyListWhenInputIsEmpty() {
        String input = "";
        assertThat(service.generateKeyPadCombinations(input)).isEmpty();

    }

    @Test
    public void shouldReturnEmptyListForOneOrZero(){
        String input = "01";
        assertThat(service.generateKeyPadCombinations(input)).isEmpty();
    }

    /**For interview purposes I wont validate all the digits, but in real world we should certainly validate all entries**/
    @Test
    public void shouldReturnThreeCombinationsForOneValidDigit() {
        String input = "2";
        final ImmutableList<String> expectedCombinations = ImmutableList.of("a","b","c");
        assertThat(service.generateKeyPadCombinations(input)).hasSameElementsAs(expectedCombinations);

    }

    @Test
    public void shouldReturnThreeCombinationsForNumberTwoAndThree() {
        String input = "23";
        final ImmutableList<String> expectedCombinations = ImmutableList.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        final List<String> actual = service.generateKeyPadCombinations(input);
        assertThat(actual).hasSameElementsAs(expectedCombinations);
        assertThat(actual).hasSize(9);
    }

}
