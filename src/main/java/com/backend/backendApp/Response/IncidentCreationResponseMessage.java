package com.backend.backendApp.Response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class IncidentCreationResponseMessage {

    private String message;
    private List<ErrorField> validationError;
    private boolean success;
    private HttpStatus status;

}
