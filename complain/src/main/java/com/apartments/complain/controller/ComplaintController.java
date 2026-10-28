package com.apartments.complain.controller;

import com.apartments.complain.dto.request.ComplainRequest;
import com.apartments.complain.dto.response.ComplainResponse;
import com.apartments.complain.repository.ComplaintRepository;
import com.apartments.complain.service.ComplainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/complain")
@RestController
public class ComplaintController {


    @Autowired
    private ComplainService complainService;


    @PostMapping("create")
    public ResponseEntity<ComplainResponse> registerComplaint(@RequestBody ComplainRequest request){

        ComplainResponse complaintResponse = complainService.createComplaint(request);


        return new ResponseEntity<>(complaintResponse, HttpStatusCode.valueOf(200));

    }

    @GetMapping("info/{id}")
    public ResponseEntity<ComplainResponse> getComplaint(@PathVariable("id") int complaint_id){
        log.info("get complaint request came..");
        ComplainResponse complaintResponse =complainService.getComplaintById(complaint_id);

        return ResponseEntity.status(HttpStatus.OK).body(complaintResponse);
    }


}
