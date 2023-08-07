package com.stroymaster.service.api;

import com.stroymaster.entity.Request;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    public ResponseEntity<?> getAllRequests();

    public ResponseEntity<?> addNewRequest(Request request);

    public ResponseEntity<?> getRequest(UUID requestId);

    public ResponseEntity<?> updateRequest(UUID requestId, Request updatedRequest);

    public ResponseEntity<?> deleteRequest(UUID requestId);
}
