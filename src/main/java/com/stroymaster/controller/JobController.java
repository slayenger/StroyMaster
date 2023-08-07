package com.stroymaster.controller;

import com.stroymaster.entity.Job;
import com.stroymaster.service.api.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/job")
@Tag(name = "Jobs", description = "Operations related to jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<?> getAllJobs()
    {
        return jobService.getAllJobs();
    }

    @PostMapping()
    public ResponseEntity<?> addNewJob(@RequestBody Job job)
    {
        return jobService.addNewJob(job);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJob(@PathVariable UUID jobId)
    {
        return jobService.getJob(jobId);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable UUID jobId, @RequestBody Job updatedJob)
    {
        return jobService.updateJob(jobId, updatedJob);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable UUID jobId)
    {
        return jobService.deleteJob(jobId);
    }


}
