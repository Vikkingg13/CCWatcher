package by.grigoriev.ccwatcher.publisher;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.event.CoinChangedEvent;
import by.grigoriev.ccwatcher.model.CoinModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(List<Coin> coins) {
        CoinChangedEvent event = new CoinChangedEvent(this, coins);
        applicationEventPublisher.publishEvent(event);
    }
}
