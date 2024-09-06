package com.backend.backendApp.Controller;


import com.backend.backendApp.Entity.DateBody;
import com.backend.backendApp.Entity.Incident;
import com.backend.backendApp.Response.IncidentCreationResponseMessage;
import com.backend.backendApp.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//base URL:   http://localhost:7070/
@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    //Creates a new incident report by validating the incident details and saving the report if the details
    @PostMapping("/report")
    public ResponseEntity<IncidentCreationResponseMessage> reportIncident(@RequestBody Incident incident){
        IncidentCreationResponseMessage res = incidentService.createIncident(incident);
        return new ResponseEntity<>(res , res.getStatus());
    }

    //Updates an existing incident report identified by the id. The updated incident details are provided in the request body.
    @PutMapping("/report/{id}")
    public ResponseEntity<String> updateIncident(@RequestBody Incident newIncident , @PathVariable String id){
        return new ResponseEntity<>(incidentService.updateIncident(newIncident , id) , HttpStatus.OK);
    }

    //Fetches all incidents stored in the system.
    @GetMapping("/getAll")
    public ResponseEntity<List<Incident>> getAllIncident(){
        return new ResponseEntity<>(incidentService.getAllIncidents() , HttpStatus.OK);
    }

    //Retrieves a list of incidents that match the specified severity level.
    @GetMapping("/{severity}")
    public ResponseEntity<Optional<List<Incident>>> getIncidentWithServerity(@PathVariable String severity){
        return new ResponseEntity<>(incidentService.getBySeverityLevel(severity) , HttpStatus.OK);
    }

    //Searches for incidents that were reported between the specified date range.
    @PostMapping("/searchByDate")
    public ResponseEntity<List<Incident>> getByDateRange(@RequestBody DateBody date){
        return new ResponseEntity<>(incidentService.getDateBetween(date.getFrom() , date.getTo()) , HttpStatus.OK);
    }


    //Retrieves a specific incident by its unique identifier.
    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<Incident>> searchById(@PathVariable String id){
        return new ResponseEntity<>(incidentService.getById(id) , HttpStatus.OK);
    }


}
