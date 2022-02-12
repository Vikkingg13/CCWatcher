package by.grigoriev.ccwatcher.event;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class CoinChangedEvent extends ApplicationEvent {

    List<Coin> coins;

    public CoinChangedEvent(Object source, List<Coin> coins) {
        super(source);
        this.coins = coins;
    }

    public List<Coin> getCoins() {
        return coins;
    }
}
