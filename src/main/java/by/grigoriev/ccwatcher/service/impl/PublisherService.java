package by.grigoriev.ccwatcher.service.impl;

import by.grigoriev.ccwatcher.publisher.CoinEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    CoinServiceImpl service;

    @Autowired
    CoinEventPublisher publisher;

    public void publish(String message) {
        publisher.publishEvent(message);
    }
}
