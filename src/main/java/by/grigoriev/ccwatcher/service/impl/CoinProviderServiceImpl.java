package by.grigoriev.ccwatcher.service.impl;

import by.grigoriev.ccwatcher.connector.CoinClient;
import by.grigoriev.ccwatcher.converter.CoinConverter;
import by.grigoriev.ccwatcher.dto.Coin;
import by.grigoriev.ccwatcher.model.CoinModel;
import by.grigoriev.ccwatcher.service.CoinProviderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CoinProviderServiceImpl implements CoinProviderService {

    @Value("${connector.api.url}")
    private String url;

    List<String> list = null;

    public List<Coin> loadCoins() {
        CoinClient client = CoinClient.connect(url);
        return getParams().stream()
                .map(client::getById)
                .filter(Predicate.not(List::isEmpty))
                .map(coins -> coins.get(0))
                .collect(Collectors.toList());
    }

    private List<String> getParams() {
        if (list == null) {
            try {
                List<Coin> coins = new ObjectMapper().readValue(new ClassPathResource("coins.json").getFile(),
                        new TypeReference<>() {
                        });
                list = coins.stream()
                        .map(Coin::getId)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
