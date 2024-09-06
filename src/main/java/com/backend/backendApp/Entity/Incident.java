package com.backend.backendApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Incident {

    @Id
    private String id;
    private String title;
    private String description;
    private String severity;
    private String incidentDate;
    private String reportedAt;

}
