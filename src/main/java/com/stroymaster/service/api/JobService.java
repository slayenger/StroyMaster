package com.stroymaster.service.api;


import com.stroymaster.entity.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface JobService {

    public ResponseEntity<List<Job>> getAllJobs();

    public ResponseEntity<?> addNewJob(Job job);

    public ResponseEntity<?> getJob(UUID jobId);

    public ResponseEntity<?> updateJob(UUID jobId, Job updatedJob);

    public ResponseEntity<?> deleteJob(UUID jobId);
}
