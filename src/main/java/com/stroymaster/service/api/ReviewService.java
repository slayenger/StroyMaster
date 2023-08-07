package com.stroymaster.service.api;

import com.stroymaster.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ReviewService {

    public ResponseEntity<?> getAllReviews();

    public ResponseEntity<?> addNewReview(Review review);

    public ResponseEntity<?> getReview(UUID reviewId);

    public ResponseEntity<?> updateReview(UUID reviewId, Review updatedReview);

    public ResponseEntity<?> deleteReview(UUID reviewId);
}
