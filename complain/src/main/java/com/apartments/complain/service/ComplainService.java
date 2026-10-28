package com.apartments.complain.service;

import com.apartments.complain.dto.request.ComplainRequest;
import com.apartments.complain.dto.response.ComplainResponse;

public interface ComplainService {


    public ComplainResponse createComplaint(ComplainRequest  complainRequest);

    ComplainResponse getComplaintById(int complaintId);
}
