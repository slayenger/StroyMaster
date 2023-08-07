package com.stroymaster.dao.impl;

import com.stroymaster.dao.api.ReviewDAO;
import com.stroymaster.entity.Review;
import com.stroymaster.exception.NotFoundException;
import com.stroymaster.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class IReviewDAO implements ReviewDAO {

    private final ReviewRepository repository;

    @Override
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @Override
    public void addNewReview(Review review)
    {
        Review newReview = repository.save(review);
    }

    @Override
    public Review getReview(UUID reviewId)
    {
        if (repository.existsById(reviewId))
        {
            return repository.getReferenceById(reviewId);
        }
        else
        {
            throw new NotFoundException("Review with ID " + reviewId + " not found!");
        }
    }

    private void copyRequestProperties(Review source, Review target)
    {
        target.setId(source.getId());
        target.setAuthor(source.getAuthor());
        target.setComment(source.getComment());
        target.setStatus(source.getStatus());
        target.setCreatedAt(source.getCreatedAt());
    }

    @Override
    public void updateReview(UUID reviewId, Review updatedReview)
    {
        if (repository.existsById(reviewId))
        {
            Review existingReview = repository.getReferenceById(reviewId);
            copyRequestProperties(updatedReview,existingReview);
            repository.save(existingReview);
        }
        else
        {
            throw new NotFoundException("Review with ID " + reviewId + " not found!");
        }
    }

    @Override
    public void deleteReview(UUID reviewId)
    {
        repository.deleteById(reviewId);
    }
}
