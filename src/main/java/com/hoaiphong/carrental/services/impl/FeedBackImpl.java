package com.hoaiphong.carrental.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.entities.FeedBack;
import com.hoaiphong.carrental.repositories.FeedBackRepository;
import com.hoaiphong.carrental.services.FeedBackService;

@Service
public class FeedBackImpl implements FeedBackService{
    

private final FeedBackRepository feedBackRepository;

    public FeedBackImpl(FeedBackRepository feedBackRepository) {    
        this.feedBackRepository = feedBackRepository;}

    @Override
    public void save(FeedBack feedBack) {
        feedBackRepository.save(feedBack);
    }

    @Override
    public FeedBack findById(UUID id) {
   return feedBackRepository.findById(id).orElse(null);
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackRepository.findAll();
    }
    
}