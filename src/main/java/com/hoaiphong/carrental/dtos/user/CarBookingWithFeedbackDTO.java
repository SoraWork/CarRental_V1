package com.hoaiphong.carrental.dtos.user;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.FeedBack;

public class CarBookingWithFeedbackDTO {
    
    private CarBooking carBooking;
    private FeedBack feedback;

    public CarBookingWithFeedbackDTO(CarBooking carBooking, FeedBack feedback) {
        this.carBooking = carBooking;
        this.feedback = feedback;
    }

    public CarBooking getCarBooking() {
        return carBooking;
    }

    public FeedBack getFeedback() {
        return feedback;
    }
}