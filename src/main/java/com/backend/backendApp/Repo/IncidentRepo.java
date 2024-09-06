package com.backend.backendApp.Repo;


import com.backend.backendApp.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidentRepo extends JpaRepository<Incident , String> {
    List<Incident> findByTitle(String title);
    Optional<List<Incident>> findBySeverity(String severityLevel);
}
