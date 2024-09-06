package com.backend.backendApp.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DateBody {
    private String from;
    private String to;
}
