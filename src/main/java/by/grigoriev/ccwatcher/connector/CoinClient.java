package by.grigoriev.ccwatcher.connector;

import by.grigoriev.ccwatcher.dto.Coin;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.List;

public interface CoinClient {

    @RequestLine("GET/?id={id}")
    List<Coin> getById(@Param("id") String id);

    static CoinClient connect(String url) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CoinClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CoinClient.class, url);
    }
}
