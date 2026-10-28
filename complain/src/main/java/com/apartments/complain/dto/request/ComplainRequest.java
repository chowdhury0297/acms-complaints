package com.apartments.complain.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplainRequest {



    // M:1 Relationships

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
