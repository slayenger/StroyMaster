package com.stroymaster.dao.api;

import com.stroymaster.entity.Job;

import java.util.List;
import java.util.UUID;

public interface JobDAO {

    public List<Job> getAllJobs();

    public void addNewJob(Job job);

    public Job getJob(UUID jobId);

    public void updateJob(UUID jobId, Job updatedJob);

    public void deleteJob(UUID jobId);

}
