package com.bharadwaj.demotwo.response;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CustomErrorResponse {

    private int status;
    private String message;
    private List<String> errors;
}

