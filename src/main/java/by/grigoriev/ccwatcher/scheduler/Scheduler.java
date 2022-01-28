package by.grigoriev.ccwatcher.scheduler;

import by.grigoriev.ccwatcher.model.CoinModel;
import by.grigoriev.ccwatcher.service.CoinService;
import by.grigoriev.ccwatcher.service.impl.CoinProviderService;
import by.grigoriev.ccwatcher.service.impl.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Scheduler {
    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private CoinService coinService;

    @Autowired
    private CoinProviderService coinProviderService;

    @Autowired
    PublisherService publisher;

    @Scheduled(cron = "${scheduler.task.coin.update}")
    public void scheduleTask() {
        logger.info("Scheduled task is running");
        List<CoinModel> list = coinProviderService.loadCoins();
        coinService.saveAll(list);
        publisher.publish("");
    }
}
