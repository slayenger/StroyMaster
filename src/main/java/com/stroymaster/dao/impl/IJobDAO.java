package com.stroymaster.dao.impl;

import com.stroymaster.dao.api.JobDAO;
import com.stroymaster.entity.Job;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.repository.JobRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class IJobDAO implements JobDAO {

    private final JobRepository repository;

    @Override
    public List<Job> getAllJobs()
    {
        return repository.findAll();
    }

    @Override
    public void addNewJob(Job job)
    {
       Job newJob = repository.save(job);
    }

    @Override
    public Job getJob(UUID jobId)
    {
        if (repository.existsById(jobId))
        {
            return repository.getReferenceById(jobId);
        }
        else
        {
            throw new NotFoundException("Job with ID " + jobId + " not found!");
        }
    }

    private void copyRequestProperties(Job source, Job target)
    {
        //target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
    }

    @Override
    @Transactional
    public void updateJob(UUID jobId, Job updatedJob)
    {
        if (repository.existsById(jobId))
        {
            Job existingJob = repository.getReferenceById(jobId);
            copyRequestProperties(updatedJob, existingJob);
            repository.saveAndFlush(existingJob);
        }
        else
        {
            throw new NotFoundException("Job with ID " + jobId + " not found!");
        }
    }

    @Override
    public void deleteJob(UUID jobId)
    {
        repository.deleteById(jobId);
    }
}
