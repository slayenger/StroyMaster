package com.stroymaster.repository;

import com.stroymaster.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
