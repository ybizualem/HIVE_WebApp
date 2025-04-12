package com.example.messageservice.configuration;
import com.pusher.rest.Pusher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PusherService {
    private static final Logger logger = LoggerFactory.getLogger(PusherService.class);

    private final Pusher pusher;

    @Autowired
    public PusherService(Pusher pusher) {
        this.pusher = pusher;
    }

    public void trigger(String channel, String event, Object data) {
        try {
            pusher.trigger(channel, event, data);
        } catch (Exception e) {
            logger.error("Error triggering Pusher event", e);
            System.err.println("Error triggering Pusher event: " + e.getMessage());
        }
    }
}
