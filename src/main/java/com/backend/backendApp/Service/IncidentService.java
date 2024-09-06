package com.backend.backendApp.Service;

import com.backend.backendApp.Entity.Incident;
import com.backend.backendApp.Response.IncidentCreationResponseMessage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IncidentService {

    IncidentCreationResponseMessage createIncident(Incident incident);
    String updateIncident(Incident incident , String id);
    Optional<List<Incident>> getBySeverityLevel(String severityLevel);
    List<Incident> getAllIncidents();
    List<Incident> getDateBetween(String from , String to);
    Optional<Incident> getById(String id);
    boolean validateDateOfIncident(String incidentDate);
}
