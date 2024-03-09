package com.otus.mita.simpleservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
public class Error {

    @NotEmpty
    private Integer code;

    @NotEmpty
    private String message;
}
