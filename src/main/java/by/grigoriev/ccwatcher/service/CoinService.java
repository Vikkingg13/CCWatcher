package by.grigoriev.ccwatcher.service;

import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;

import java.util.List;

public interface CoinService {
    List<CoinModel> findAll();
    CoinModel findBySymbol(String symbol);
    List<CoinModel> saveAll(List<Coin> coin);
}
