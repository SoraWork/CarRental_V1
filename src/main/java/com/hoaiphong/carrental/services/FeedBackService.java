package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.dtos.feedback.FeedBackDTO;


public interface FeedBackService {
    List<FeedBackDTO> findAll();

    FeedBackDTO findById(UUID id);

    Page<FeedBackDTO> findAll(Pageable pageable);

    Page<FeedBackDTO> findByRating(int rating, Pageable pageable);

    FeedBackDTO findByRating(int rating);

    FeedBackDTO create(FeedBackDTO feedBackDTO);


}
