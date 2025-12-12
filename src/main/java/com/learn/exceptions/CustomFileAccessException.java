package com.learn.exceptions;

import java.io.IOException;

public class CustomFileAccessException extends IOException {
    public CustomFileAccessException(String message) {
        super(message);
    }
}
