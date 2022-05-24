package com.andersenlab.atink.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String message;
}
