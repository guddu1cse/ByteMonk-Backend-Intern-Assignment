package com.backend.backendApp.Service.Impl;

import com.backend.backendApp.Entity.Incident;
import com.backend.backendApp.Repo.IncidentRepo;
import com.backend.backendApp.Response.ErrorField;
import com.backend.backendApp.Response.IncidentCreationResponseMessage;
import com.backend.backendApp.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncidentServiceImpl implements IncidentService {
    @Autowired
    private IncidentRepo incidentRepo;


    //This method is responsible for creating a new Incident record.
    //It performs several validations before saving the Incident entity to the database,
    //ensuring the data adheres to the business requirements.
    @Override
    public IncidentCreationResponseMessage createIncident(Incident incident) {
        incident.setId(UUID.randomUUID().toString());
        incident.setReportedAt(getCurrentDate());

        List<ErrorField> errorMessage = new ArrayList<>();

        //checking title is unique or not
        if(titleIsUnique(incident.getTitle())) errorMessage.add(new ErrorField("title" , "title must be unique"));

        //checking length of title is greater then 10 or not
        if(incident.getTitle().length()<10) errorMessage.add(new ErrorField("title" , "title must be 10 or more characters"));

        //checking incident date is not be past of greater than 30 days or future
        if(validateDateOfIncident(incident.getIncidentDate())) errorMessage.add(
                new ErrorField("incidentDate" , "incidents cannot be created with a past date greater than 30 days or a future date."));
        boolean status = errorMessage.isEmpty();

        String message = "Invalid Incident Details !!";
        if(status){
             incidentRepo.save(incident);
             message = "Incident Report Successfully created !!";
        }

        //building response message
        return new IncidentCreationResponseMessage(message , errorMessage , status , status ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    //getting current date in string format(DD/MM/YYYY)
    String getCurrentDate(){
        Date currDate = new Date();
        return currDate.getDate()+"/"+(currDate.getMonth()+1)+"/"+currDate.getYear();
    }


    //This method checks whether the provided incident date is within the valid range for reporting.
    //Incidents cannot be created if the date is more than 30 days in the past or set to a future date.
    public boolean validateDateOfIncident(String incidentDate) {
        //calculating time in milliseconds
        //time in milliseconds since 1 January 1970
        long timeOfIncident = parseDate(incidentDate).getTime();
        long currentTime = new Date().getTime();
        long timeOf30Days = 1000L * 60L * 60L * 24L * 30L;

        return !(currentTime-timeOf30Days<=timeOfIncident && timeOfIncident<=currentTime);
    }

    //This method checks if the given incident title is unique by comparing it with the titles of all existing incidents in the database.
    boolean titleIsUnique(String title){
        List<Incident> incidents = incidentRepo.findAll();
        for(Incident inc : incidents){
            if(inc.getTitle().equalsIgnoreCase(title)) return true;
        }
        return false;
    }

    //This method is responsible for updating an existing incident.
    //The current implementation is incomplete and returns an empty string.
    //The intended functionality is to update the details of an incident if the incident exists.
    @Override
    public String updateIncident(Incident incident , String id) {
        Incident oldIncident = incidentRepo.findById(id).orElse(null);
        if(oldIncident == null) return "incident not found for given id";
        incident.setId(id);
        incidentRepo.save(incident);

        return "incident report successfully updated !";
    }

    //This method retrieves a list of incidents that match the specified severityLevel.
    @Override
    public Optional<List<Incident>> getBySeverityLevel(String severityLevel) {
        return incidentRepo.findBySeverity(severityLevel);
    }

    //This method retrieves all incidents from the repository.
    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepo.findAll();
    }

    //This method retrieves incidents that were reported between two given dates.
    @Override
    public List<Incident> getDateBetween(String from, String to) {
        Date start = parseDate(from);
        Date end = parseDate(to);

        return incidentRepo.findAll().stream().filter(incident ->
                (start.getTime()<=parseDate(incident.getIncidentDate()).getTime()
                        && parseDate(incident.getIncidentDate()).getTime()<=end.getTime())).toList();
    }

    //This utility method converts a date string (in DD/MM/YYYY format) into a Date object.
    Date parseDate(String date){
        date = date.substring(0 , 10); // DD/MM/YYYY
        String arr[] = date.split("/");

        Calendar calendar = new GregorianCalendar();
        calendar.set(
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[1])-1,
                Integer.parseInt(arr[0])
        );

        return calendar.getTime();
    }

    //This method retrieves a specific incident by its unique ID.
    @Override
    public Optional<Incident> getById(String id) {
        return incidentRepo.findById(id);
    }
}
