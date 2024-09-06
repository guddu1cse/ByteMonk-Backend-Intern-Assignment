package com.backend.backendApp;

import com.backend.backendApp.Entity.Incident;
import com.backend.backendApp.Service.IncidentService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendAppApplicationTests {

    @Autowired
    private IncidentService incidentService;

	@Test
	void contextLoads() {
	}

    @Test
    void validateDateOfIncidentTest(){
        //test case 1
        boolean actualResult = incidentService.validateDateOfIncident("01/09/2024");
        boolean expected = false;
        Assertions.assertEquals(expected, actualResult);

        //test case 2
        boolean actualResult1 = incidentService.validateDateOfIncident("01/08/2024");
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actualResult1);
    }


    @Test()
    void getByIdTest(){
        //incident id must present in database
        //test case 1
        Incident actualData1 =  incidentService.getById("d05c43d7-0850-4e02-aaa9-038d1572ef01").orElse(null);
        Assertions.assertNotNull(actualData1);

        //
        Incident actualData2 = incidentService.getById("idisnotpresentindatabase").orElse(null);
        Assertions.assertNull(actualData2);
    }

}
