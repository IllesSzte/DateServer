package com.date.date.exceptions;

import com.date.date.model.User;
import lombok.Getter;

@Getter
public class UserExceptions extends  Exception{
    public UserExceptions(ExceptionMesageEnum exceptionMesageEnum){
        super(exceptionMesageEnum.getMessage());
    }
}
