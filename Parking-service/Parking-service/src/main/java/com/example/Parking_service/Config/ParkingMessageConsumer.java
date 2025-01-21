package com.example.Parking_service.Config;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ParkingMessageConsumer {

    @JmsListener(destination = "parking-queue")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
