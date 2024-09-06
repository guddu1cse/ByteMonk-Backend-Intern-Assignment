package com.backend.backendApp.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorField {

    private String errorField;
    private String errorMessage;
}
