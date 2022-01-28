package by.grigoriev.ccwatcher.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserNotifyService {
    private static Logger logger = LoggerFactory.getLogger(UserNotifyService.class);

    @Value("${marginal.percent.value}")
    private double marginPercent;

    @Autowired
    CoinServiceImpl service;

    List<User> users = new ArrayList<>();

    public void notifyLog() {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (check(user)) {
                log(user);
                iterator.remove();
            }
        }
    }

    private void log(User user) {
        logger.warn("{} {} {}", user.getSymbol(), user.getName(), percentIncrease(user));
    }

    private boolean check(User user) {
        double percent = percentIncrease(user);
        return percent > marginPercent;
    }

    private double percentIncrease(User user) {
        double fixedPrice = user.priceUsd;
        double currentPrice = service.findBySymbol(user.getSymbol()).getPriceUSD();
        return Math.abs((currentPrice - fixedPrice)/fixedPrice * 100);
    }

    public void addUser(String name, String symbol, double price) {
        users.add(new User(name, symbol, price));
        logger.info("User {} has been added, fixed price: {}", name, price);
    }

    private static class User {
        String name;
        String symbol;
        double priceUsd;

        public User(String name, String symbol, double priceUsd) {
            this.name = name;
            this.symbol = symbol;
            this.priceUsd = priceUsd;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public double getPriceUsd() {
            return priceUsd;
        }
    }
}
