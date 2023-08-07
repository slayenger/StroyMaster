package com.stroymaster.dao.impl;

import com.stroymaster.dao.api.RequestDAO;
import com.stroymaster.entity.Request;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Repository
public class IRequestDAO implements RequestDAO {

    private final RequestRepository repository;

    @Override
    public List<Request> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public void addNewRequest(Request request)
    {
        Request newRequest = repository.save(request);
    }

    @Override
    public Request getRequest(UUID requestId)
    {
        if (repository.existsById(requestId))
        {
            return repository.getReferenceById(requestId);
        }
        else
        {
            throw new NotFoundException("Request with ID " + requestId + " not found!");
        }

    }

    private void copyRequestProperties(Request source, Request target)
    {
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setAddress(source.getAddress());
        target.setEmail(source.getEmail());
        target.setId(source.getId());
        target.setService(source.getService());
        target.setPhoneNumber(source.getPhoneNumber());
    }

    @Override
    public void updateRequest(UUID requestId, Request updatedRequest)
    {
        if(repository.existsById(requestId))
        {
            Request existingRequest = repository.getReferenceById(requestId);
            copyRequestProperties(updatedRequest,existingRequest);
            repository.save(existingRequest);
        }
        else
        {
            throw new NotFoundException("Request with ID " + requestId + " not found!");
        }
    }

    @Override
    public void deleteRequest(UUID requestId)
    {
        repository.deleteById(requestId);
    }


}
