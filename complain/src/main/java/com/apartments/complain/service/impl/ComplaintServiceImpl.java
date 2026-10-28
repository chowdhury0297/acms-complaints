package com.apartments.complain.service.impl;

import com.apartments.complain.constrants.ComplaintPriority;
import com.apartments.complain.constrants.ComplaintStatus;
import com.apartments.complain.dto.request.ComplainRequest;
import com.apartments.complain.dto.response.ComplainResponse;
import com.apartments.complain.entity.Complaint;
import com.apartments.complain.exception.ComplaintNotFoundException;
import com.apartments.complain.repository.ComplaintRepository;
import com.apartments.complain.service.ComplainService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Service
public class ComplaintServiceImpl implements ComplainService {

//    private final static Logger log= LoggerFactory.getLogger(ComplaintServiceImpl.class);

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository){
        this.complaintRepository=complaintRepository;
    }



    /**
     * register a complaint where resident has passed only ComplainRequest Dto
     *
      */

    @Override
    public ComplainResponse createComplaint(ComplainRequest complainRequest) {

        log.info("Complaint request come || ComplaintServiceImpl || createComplaint()");

        Complaint complaint=covertDtoToEntity(complainRequest);

        Complaint savedComplaint=complaintRepository.save(complaint);



        return convertEntityToResponseDto(savedComplaint);
    }

//    @Cacheable(value = "complaints", key = "#complaintId")
    @Override
    public ComplainResponse getComplaintById(int complaintId) {

        log.info("Find Complaint request received for ID: {}|| ComplaintServiceImpl || getComplaintById()",complaintId);

        Complaint complaint =complaintRepository.findById(complaintId).orElseThrow(

                () -> {
                    log.atWarn()
                            .setMessage("Complaint search failed: Complaint not found")
                            .addKeyValue("complaintId", complaintId)
                            .log();
                   return new ComplaintNotFoundException("Complaint not found for this ID: " + complaintId);
                });


        return convertEntityToResponseDto(complaint);
    }


    // staff member can read the complaint and update the status of the ticket status


    public static Complaint covertDtoToEntity(ComplainRequest request){
        Complaint complaint=new Complaint();
        complaint.setResidentId(request.getResidentId());
        complaint.setCategory(request.getCategory());
        complaint.setDescription(request.getDescription());
        complaint.setAssignedStaffId(request.getAssignedStaffId());
        complaint.setLocationType(request.getLocationType());

        ComplaintPriority priority=Arrays.stream(ComplaintPriority.values()).filter(p->p.name().equalsIgnoreCase(request.getPriority()))
                .findFirst().orElseThrow(RuntimeException::new);
        complaint.setPriority(priority.name());
        complaint.setStatus(ComplaintStatus.SUBMITTED.toString());
        complaint.setUnitNumber(request.getUnitNumber());//
        complaint.setTitle(request.getTitle());
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setTicketNumber("COMP-"+ UUID.randomUUID());
        return complaint;
    }

    public static  ComplainResponse convertEntityToResponseDto(Complaint complaint){

        ComplainResponse response=new ComplainResponse();
        response.setCategory(complaint.getCategory());
        response.setPriority(complaint.getPriority());
        response.setStatus(complaint.getStatus());
        response.setDescription(complaint.getDescription());
        response.setTitle(complaint.getTitle());
        response.setLocationType(complaint.getLocationType());
        response.setResidentId(complaint.getResidentId());
        response.setAssignedStaffId(complaint.getAssignedStaffId());
        response.setUnitNumber(complaint.getUnitNumber());
        response.setTicketNumber(complaint.getTicketNumber());
        log.atInfo()
                .setMessage("Complaint registered successfully")
                .addKeyValue("complaintId", complaint.getId())
                .addKeyValue("payload",response)
                .log();
        return response;
    }


}
