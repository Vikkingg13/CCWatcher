package by.grigoriev.ccwatcher.publisher;

import by.grigoriev.ccwatcher.event.CoinChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CoinEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final String message) {
        CoinChangedEvent event = new CoinChangedEvent(this, message);
        applicationEventPublisher.publishEvent(event);
    }
}
