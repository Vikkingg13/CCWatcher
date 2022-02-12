package by.grigoriev.ccwatcher.scheduler;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;
import by.grigoriev.ccwatcher.publisher.CoinEventPublisher;
import by.grigoriev.ccwatcher.service.CoinProviderService;
import by.grigoriev.ccwatcher.service.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class Scheduler {

    @Autowired
    private CoinService coinService;

    @Autowired
    private CoinProviderService coinProviderService;

    @Autowired
    CoinEventPublisher publisher;

    @Scheduled(cron = "${scheduler.task.coin.update}")
    public void scheduleTask() {
        log.info("Scheduled task is running");
        List<Coin> coins = coinProviderService.loadCoins();
        publisher.publishEvent(coins);
        coinService.saveAll(coins);
    }
}
