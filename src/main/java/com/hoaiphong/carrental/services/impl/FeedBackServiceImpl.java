package com.hoaiphong.carrental.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoaiphong.carrental.dtos.feedback.FeedBackDTO;
import com.hoaiphong.carrental.entities.FeedBack;
import com.hoaiphong.carrental.repositories.FeedBackRepository;
import com.hoaiphong.carrental.services.FeedBackService;

import jakarta.persistence.EntityNotFoundException;
@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
    private final FeedBackRepository feedBackRepository;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository) {
            this.feedBackRepository = feedBackRepository;
    }

    @Override
    public List<FeedBackDTO> findAll() {
         // Get List of entities
        var feedbacks = feedBackRepository.findAll();

         // Convert entities to DTOs
        var feedbackDTOs = feedbacks.stream().map(feedBack -> {
            var feedbackDTO = new FeedBackDTO();
            feedbackDTO.setId(feedBack.getId());
            feedbackDTO.setRating(feedBack.getRating());
            feedbackDTO.setContent(feedBack.getContent());
            feedbackDTO.setDateTime(feedBack.getDateTime());

            return feedbackDTO;
        }).toList();
        return feedbackDTOs;

    }

    @Override
    public Page<FeedBackDTO> findAll(Pageable pageable) {
       // Fetch paginated feedback entities
        Page<FeedBack> feedbacks = feedBackRepository.findAll(pageable);

        // Convert entities to DTOs
        return feedbacks.map(feedBack -> {
            FeedBackDTO feedbackDTO = new FeedBackDTO();
            feedbackDTO.setId(feedBack.getId());
            feedbackDTO.setRating(feedBack.getRating());
            feedbackDTO.setContent(feedBack.getContent());
            feedbackDTO.setDateTime(feedBack.getDateTime());
            // Add any additional fields as needed
            return feedbackDTO;
        });
    }

    @Override
    public Page<FeedBackDTO> findByRating(int rating, Pageable pageable) {
        // Fetch paginated feedback entities by rating
    Page<FeedBack> feedbacks = feedBackRepository.findAll((root, query, criteriaBuilder) -> {
        // WHERE rating = :rating
        return criteriaBuilder.equal(root.get("rating"), rating);
    }, pageable);

    // Convert entities to DTOs
    return feedbacks.map(feedBack -> {
        FeedBackDTO feedbackDTO = new FeedBackDTO();
        feedbackDTO.setId(feedBack.getId());
        feedbackDTO.setRating(feedBack.getRating());
        feedbackDTO.setContent(feedBack.getContent());
        feedbackDTO.setDateTime(feedBack.getDateTime());
        return feedbackDTO;
    });
    }

    @Override
    public FeedBackDTO findByRating(int rating) {
        // Retrieve the feedback entity by rating
        FeedBack feedback = feedBackRepository.findByRating(rating);
        
        // If no feedback is found, handle accordingly (e.g., throw an exception)
        if (feedback == null) {
            throw new EntityNotFoundException("No feedback found with rating: " + rating);
        }
        
        // Convert the entity to DTO
        FeedBackDTO feedbackDTO = new FeedBackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setContent(feedback.getContent());
        feedbackDTO.setDateTime(feedback.getDateTime());
        // Set other necessary fields as required

        return feedbackDTO;
    }

    @Override
    public FeedBackDTO create(FeedBackDTO feedBackDTO) {
        // Check if feedBackDTO is null then throw exception
        if (feedBackDTO == null) {
            throw new IllegalArgumentException("FeedBack is required");
        }

        // Convert DTO to entity
        FeedBack feedBack = new FeedBack();
        feedBack.setRating(feedBackDTO.getRating());
        feedBack.setContent(feedBackDTO.getContent());
        feedBack.setDateTime(feedBackDTO.getDateTime());

        // Save entity
        feedBack = feedBackRepository.save(feedBack);

        // Convert entity to DTO
        FeedBackDTO feedbackDTO = new FeedBackDTO();
        feedbackDTO.setId(feedBack.getId());
        feedbackDTO.setRating(feedBack.getRating());
        feedbackDTO.setContent(feedBack.getContent());
        feedbackDTO.setDateTime(feedBack.getDateTime());
        return feedbackDTO;
    }

    @Override
    public FeedBackDTO findById(UUID id) {
         // Get entity by id
        FeedBack feedback = feedBackRepository.findById(id).orElse(null);
        
        // Check if entity is null then return null
        if (feedback == null) {
            return null;
        }
        
        // Convert entity to DTO
        FeedBackDTO feedbackDTO = new FeedBackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setContent(feedback.getContent());
        feedbackDTO.setDateTime(feedback.getDateTime());
        // Set other necessary fields as required

        return feedbackDTO;
       
    }






}

