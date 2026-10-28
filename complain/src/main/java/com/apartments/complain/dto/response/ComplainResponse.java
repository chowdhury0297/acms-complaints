package com.apartments.complain.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data

public class ComplainResponse {


    private String ticketNumber; // by this resident can trace the  compliant

    // M:1 Relationships  jwt will fetch

    private int residentId; // we will fetch it from jwt token


    private int assignedStaffId;

    // Issue Info flat number

    private String unitNumber;


    private String title;


    private String description;


    private String  category;


    private String locationType;



    private String status ; // by default submitted


    private String priority ; // by default medium

}
