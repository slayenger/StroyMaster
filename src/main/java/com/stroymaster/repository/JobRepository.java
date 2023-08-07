package com.stroymaster.repository;


import com.stroymaster.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
