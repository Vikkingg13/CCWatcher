package by.grigoriev.ccwatcher.service.impl;

import by.grigoriev.ccwatcher.converter.CoinConverter;
import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;
import by.grigoriev.ccwatcher.repository.CoinRepository;
import by.grigoriev.ccwatcher.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {
    @Autowired
    CoinRepository coinRepository;

    @Override
    public List<CoinModel> findAll() {
        return (List<CoinModel>) coinRepository.findAll();
    }

    @Override
    public CoinModel findBySymbol(String symbol) {
        return coinRepository.findCoinBySymbol(symbol);
    }

    @Override
    public List<CoinModel> saveAll(List<Coin> coins) {
        List<CoinModel> models = CoinConverter.toCoinList(coins);
        return (List<CoinModel>) coinRepository.saveAll(models);
    }
}
