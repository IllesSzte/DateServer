package com.date.date.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMesageEnum {

    USER_NOT_FOUND("This user is not found"),
    USER_HAS_NO_PARTNER("User has no partner."),
    ;
   final private String message;

}
