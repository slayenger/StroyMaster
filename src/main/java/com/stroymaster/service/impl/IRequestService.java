package com.stroymaster.service.impl;

import com.stroymaster.dao.api.RequestDAO;
import com.stroymaster.entity.Job;
import com.stroymaster.entity.Request;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.service.api.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IRequestService implements RequestService {

    private final RequestDAO requestDAO;

    @Override
    public ResponseEntity<?> getAllRequests() {
        List<Request> requests = requestDAO.getAllRequests();
        if (requests.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else
        {
            return ResponseEntity.ok(requests);
        }
    }

    @Override
    public ResponseEntity<?> addNewRequest(@Validated Request request) {
        if (request == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Request cannot be null.");
        }
        try
        {
            requestDAO.addNewRequest(request);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding request");
        }
    }

    @Override
    public ResponseEntity<?> getRequest(UUID requestId) {
        try
        {

            Request request = requestDAO.getRequest(requestId);
            return ResponseEntity.ok(request);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while getting request");
        }
    }

    @Override
    public ResponseEntity<?> updateRequest(UUID requestId, Request updatedRequest) {
        try
        {
            requestDAO.updateRequest(requestId,updatedRequest);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating request");
        }
    }

    @Override
    public ResponseEntity<?> deleteRequest(UUID requestId) {
        try
        {
            requestDAO.deleteRequest(requestId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting request");
        }
    }
}
