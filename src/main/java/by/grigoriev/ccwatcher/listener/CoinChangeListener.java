package by.grigoriev.ccwatcher.listener;

import by.grigoriev.ccwatcher.event.CoinChangedEvent;
import by.grigoriev.ccwatcher.service.impl.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CoinChangeListener {

    @Autowired
    private UserNotifyService userNotifyService;

    @EventListener
    public void onCoinEvent(CoinChangedEvent event) {
        userNotifyService.notifyLog();
    }

}
