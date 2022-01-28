package by.grigoriev.ccwatcher.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNotifyService {
    private static Logger logger = LoggerFactory.getLogger(UserNotifyService.class);

    @Value("${coin.percent.warn}")
    private double percentWarn;

    @Autowired
    CoinServiceImpl service;

    List<User> users = new ArrayList<>();

    public void notifyLog() {
        users.stream()
                .filter(this::check)
                .forEach(this::info);
    }

    private void info(User user) {
        logger.warn("{} {} {}", user.getSymbol(), user.getName(), percentIncrease(user));
    }

    private boolean check(User user) {
        double percent = percentIncrease(user);
        return percent > percentWarn;
    }

    private double percentIncrease(User user) {
        double fixedPrice = user.priceUsd;
        double currentPrice = service.findBySymbol(user.getSymbol()).getPriceUSD();
        return (currentPrice - fixedPrice)/fixedPrice * 100;
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
