package com.hoaiphong.carrental.services;

import java.util.List;
import java.util.UUID;

import com.hoaiphong.carrental.entities.FeedBack;

public interface FeedBackService {

    void save(FeedBack feedBack);

    FeedBack findById(UUID id);

    List<FeedBack> findAll();
    
}
