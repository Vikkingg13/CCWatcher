package by.grigoriev.ccwatcher.service.impl;

import by.grigoriev.ccwatcher.connector.CoinClient;
import by.grigoriev.ccwatcher.converter.CoinConverter;
import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinProviderService {

    @Value("${connector.api.url}")
    private String url;

    public List<CoinModel> loadCoins() {
        CoinClient client = CoinClient.connect(url);
        List<Coin> coins =  getParams().stream()
                .map(id -> client.getById(id).get(0))
                .collect(Collectors.toList());
        return CoinConverter.toCoinList(coins);
    }

    private List<String> getParams() {
        List<String> list = null;
        try {
            List<Coin> coins = new ObjectMapper().readValue(new ClassPathResource("coins.json").getFile(),
                    new TypeReference<List<Coin>>(){});
            list = coins.stream()
                    .map(Coin::getId)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
