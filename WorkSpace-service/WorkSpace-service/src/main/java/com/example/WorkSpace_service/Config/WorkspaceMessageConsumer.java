package com.example.WorkSpace_service.Config;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceMessageConsumer {

    @JmsListener(destination = "workspace-queue")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
