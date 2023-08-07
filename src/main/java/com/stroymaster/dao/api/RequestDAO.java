package com.stroymaster.dao.api;

import com.stroymaster.entity.Request;
import com.stroymaster.entity.Review;
import com.stroymaster.entity.Work;

import java.util.List;
import java.util.UUID;

public interface RequestDAO {

    public List<Request> getAllRequests();

    public void addNewRequest(Request request);

    public Request getRequest(UUID requestId);

    public void updateRequest(UUID requestId, Request updatedRequest);

    public void deleteRequest(UUID requestId);

}
