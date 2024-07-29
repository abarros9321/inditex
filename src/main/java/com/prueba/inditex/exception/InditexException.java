package com.prueba.inditex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@EqualsAndHashCode(callSuper = true)
@Data
public class InditexException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public InditexException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static boolean isValidDate(String d, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        try {
            df.parse(d);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
