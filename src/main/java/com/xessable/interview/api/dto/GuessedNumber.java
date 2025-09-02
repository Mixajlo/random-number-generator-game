package com.xessable.interview.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record GuessedNumber(
        @Min(value = 1, message = "Number must be greater than 0")
        @Max(value = 1001, message = "Number must be less or equal to 1000")
        Integer number
) {

}
