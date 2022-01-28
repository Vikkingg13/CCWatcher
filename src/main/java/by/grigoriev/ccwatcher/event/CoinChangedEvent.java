package by.grigoriev.ccwatcher.event;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CoinChangedEvent extends ApplicationEvent {

    String message;

    public CoinChangedEvent(Object source, String message) {
        super(source);
    }

    public String getMessage() {
        return message;
    }
}
