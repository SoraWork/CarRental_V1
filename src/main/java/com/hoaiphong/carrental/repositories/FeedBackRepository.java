package com.hoaiphong.carrental.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hoaiphong.carrental.entities.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, UUID>, JpaSpecificationExecutor<FeedBack> {
FeedBack findByRating(int rating);
}
