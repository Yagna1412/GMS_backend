package com.gms.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GmsException extends ResponseStatusException {
    public GmsException(HttpStatus status, String message) {
        super(status, message);
    }
}