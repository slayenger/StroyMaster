package com.stroymaster.service.impl;

import com.stroymaster.dao.api.JobDAO;
import com.stroymaster.entity.Job;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.service.api.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IJobService implements JobService {


    private final JobDAO jobDAO;

    @Override
    public ResponseEntity<List<Job>> getAllJobs()
    {
        List<Job> jobs = jobDAO.getAllJobs();
        if (jobs.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else
        {
            return ResponseEntity.ok(jobs);
        }
    }


    @Override
    public ResponseEntity<?> addNewJob(@Validated Job job)
    {
        try
        {
            jobDAO.addNewJob(job);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding job");
        }
    }

    @Override
    public ResponseEntity<?> getJob(UUID jobId)
    {
        try
        {
            Job job = jobDAO.getJob(jobId);
            return ResponseEntity.ok(job);
        }
        catch (NotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while getting job");
        }
    }

    @Override
    public ResponseEntity<?> updateJob(UUID jobId, Job updatedJob) {
        try
        {
            jobDAO.updateJob(jobId, updatedJob);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating job");
        }
    }

    @Override
    public ResponseEntity<?> deleteJob(UUID jobId) {
        try
        {
            jobDAO.deleteJob(jobId);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting job");
        }
    }
}
