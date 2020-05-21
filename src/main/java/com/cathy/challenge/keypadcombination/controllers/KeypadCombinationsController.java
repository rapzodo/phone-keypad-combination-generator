package com.cathy.challenge.keypadcombination.controllers;

import com.cathy.challenge.keypadcombination.annotations.Validate;
import com.cathy.challenge.keypadcombination.models.Combinations;
import com.cathy.challenge.keypadcombination.service.specs.KeyPadCombinationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "keypad/v1/generator", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class KeypadCombinationsController {

    private final KeyPadCombinationService keyPadCombinationService;

    @GetMapping("phone/{input}")
    @ApiOperation("")
    public ResponseEntity<Combinations> getCombinations(@PathVariable @Validate String input,
                                                        @RequestParam int pageNum, @RequestParam int pageOffSet,
                                                        @RequestParam int pageSize){
        final List<String> combinations = keyPadCombinationService.generateKeyPadCombinations(input);
        return ResponseEntity.ok(new Combinations(combinations, pageOffSet));
    }
}
