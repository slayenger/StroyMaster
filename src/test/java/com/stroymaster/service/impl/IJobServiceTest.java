package com.stroymaster.service.impl;

import com.stroymaster.dao.api.JobDAO;
import com.stroymaster.dao.impl.IJobDAO;
import com.stroymaster.entity.Job;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.repository.JobRepository;
import com.stroymaster.service.api.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IJobServiceTest {

    @InjectMocks
    private IJobService jobService;
    @Mock
    private IJobDAO jobDAO;


    @Test
    void testGetAllJobs_ReturnsJobsList() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Job job1 = new Job(uuid1, "abc", "abc", "100");
        Job job2 = new Job(uuid2, "def", "def", "150");

        when(jobDAO.getAllJobs()).thenReturn(List.of(job1, job2));

        ResponseEntity<?> response = jobService.getAllJobs();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(List.of(job1, job2), response.getBody());
    }

    @Test
    void testGetAllJobs_ReturnsNoContent() {

        when(jobDAO.getAllJobs()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Job>> response = jobService.getAllJobs();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void testAddNewJob_Success() {
        UUID uuid = UUID.randomUUID();
        Job job = new Job(uuid, "destroy bubblegum", "effective", "50");

        ResponseEntity<?> response = jobService.addNewJob(job);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddNewJob_ThrowsException() {
        Job job = new Job();

        doThrow(new RuntimeException("Error occurred while adding job"))
                .when(jobDAO).addNewJob(any(Job.class));

        ResponseEntity<?> response = jobService.addNewJob(job);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetJob_Success() {
        UUID uuid = UUID.randomUUID();
        Job job = new Job();
        job.setId(uuid);

        doReturn(job).when(jobDAO).getJob(uuid);

        ResponseEntity<?> response = jobService.getJob(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(job, response.getBody());
    }

    @Test
    void testGetJob_ThrowsNotFound() {
        UUID uuid = UUID.randomUUID();
        doThrow(NotFoundException.class).when(jobDAO).getJob(uuid);

        ResponseEntity<?> response = jobService.getJob(uuid);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Job not found", response.getBody());
    }

    @Test
    void testGetJob_ThrowsException() {
        UUID uuid = UUID.randomUUID();

        doThrow(RuntimeException.class).when(jobDAO).getJob(uuid);

        ResponseEntity<?> response = jobService.getJob(uuid);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred while getting job", response.getBody());
    }

    @Test
    void TestUpdateJob_Success() {
        UUID uuid = UUID.randomUUID();
        Job existingJob = new Job();
        existingJob.setId(uuid);

        Job updatedJob = new Job();
        updatedJob.setId(uuid);
        updatedJob.setTitle("Updated title");

        //when(jobDAO.getJob(uuid)).thenReturn(existingJob);
        doNothing().when(jobDAO).updateJob(uuid, updatedJob);

        ResponseEntity<?> response = jobService.updateJob(uuid, updatedJob);

        //verify(jobDAO, times(1)).getJob(uuid);
        verify(jobDAO, times(1)).updateJob(uuid, updatedJob);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void TestUpdateJob_NotFound() {
        UUID uuid = UUID.randomUUID();
        Job existingJob = new Job();
        existingJob.setId(uuid);

        Job updatedJob = new Job();
        updatedJob.setId(uuid);
        updatedJob.setTitle("Updated title");

        doThrow(NotFoundException.class).when(jobDAO).updateJob(eq(uuid), any(Job.class));

        ResponseEntity<?> response = jobService.updateJob(uuid, updatedJob);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Job not found", response.getBody());

    }

    @Test
    void TestUpdateJob_InternalServer() {
        UUID uuid = UUID.randomUUID();
        Job existingJob = new Job();
        existingJob.setId(uuid);

        Job updatedJob = new Job();
        updatedJob.setId(uuid);
        updatedJob.setTitle("Updated title");

        doThrow(HttpServerErrorException.InternalServerError.class).when(jobDAO).updateJob(eq(uuid), any(Job.class));

        ResponseEntity<?> response = jobService.updateJob(uuid, updatedJob);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred while updating job", response.getBody());

    }

    @Test
    void deleteJob_Success() {
        UUID uuid = UUID.randomUUID();
        Job job = new Job();
        job.setId(uuid);

        doNothing().when(jobDAO).deleteJob(uuid);

        ResponseEntity<?> response = jobService.deleteJob(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteJob_NotFound()
    {
        UUID uuid = UUID.randomUUID();
        Job job = new Job();
        job.setId(uuid);

        doThrow(NotFoundException.class).when(jobDAO).deleteJob(uuid);

        ResponseEntity<?> response = jobService.deleteJob(uuid);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Job not found", response.getBody());
    }

    @Test
    void deleteJob_InternalError()
    {
        UUID uuid = UUID.randomUUID();
        Job job = new Job();
        job.setId(uuid);

        doThrow(HttpServerErrorException.InternalServerError.class).when(jobDAO).deleteJob(uuid);

        ResponseEntity<?> response = jobService.deleteJob(uuid);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred while deleting job", response.getBody());
    }
}