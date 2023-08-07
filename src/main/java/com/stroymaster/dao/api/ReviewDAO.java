package com.stroymaster.dao.api;

import com.stroymaster.entity.Request;
import com.stroymaster.entity.Review;
import com.stroymaster.entity.Work;

import java.util.List;
import java.util.UUID;

public interface ReviewDAO {

    public List<Review> getAllReviews();

    public void addNewReview(Review review);

    public Review getReview(UUID reviewId);

    public void updateReview(UUID reviewId, Review updatedReview);

    public void deleteReview(UUID reviewId);


}
