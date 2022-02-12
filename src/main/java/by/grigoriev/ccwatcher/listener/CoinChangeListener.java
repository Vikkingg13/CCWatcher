package by.grigoriev.ccwatcher.listener;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.event.CoinChangedEvent;
import by.grigoriev.ccwatcher.service.UserService;
import by.grigoriev.ccwatcher.service.notify.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class CoinChangeListener {

    @Value("${marginal.percent.value}")
    private double marginPercent;

    @Autowired
    private UserService userService;

    @EventListener
    public void onCoinEvent(CoinChangedEvent event) {
        List<Coin> coins = event.getCoins();
        List<User> users = userService.getUsers();
        List<User> removeList = new ArrayList<>();
        for (User user : users) {
            double percent = calcPercent(user, coins);
            if (percent > marginPercent) {
                log(user, percent);
                removeList.add(user);
            }
        }
        removeList.forEach(user -> userService.removeUser(user));
    }

    private void log(User user, double percent) {
        log.warn("{} {} {}", user.getSymbol(), user.getName(), percent);
    }

    private double calcPercent(User user, List<Coin> coins) {
        double fixedPrice = user.getPriceUsd();
        double currentPrice = coins.stream()
                .filter(coin -> coin.getSymbol().equals(user.getSymbol()))
                .findFirst().orElseThrow(() -> new RuntimeException("Coin not found"))
                .getPriceUsd();
        return Math.abs((currentPrice - fixedPrice)/fixedPrice * 100);
    }

}
