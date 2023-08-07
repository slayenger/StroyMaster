package com.stroymaster.repository;

import com.stroymaster.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkRepository extends JpaRepository<Work, UUID> {
}
