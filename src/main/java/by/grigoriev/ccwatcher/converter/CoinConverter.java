package by.grigoriev.ccwatcher.converter;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;

import java.util.List;
import java.util.stream.Collectors;

public class CoinConverter {

    public static List<CoinModel> toCoinList(List<Coin> listDto) {
        return listDto.stream()
                .map(coin ->
                     new CoinModel(coin.getId(), coin.getSymbol(), coin.getName(), coin.getPriceUsd()))
                .collect(Collectors.toList());

    }

    public static CoinModel toCoinModel(Coin coin) {
        return new CoinModel(coin.getId(), coin.getSymbol(),
                coin.getName(), coin.getPriceUsd());
    }
}
