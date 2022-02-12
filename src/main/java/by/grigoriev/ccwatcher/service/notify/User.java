package by.grigoriev.ccwatcher.service.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final String name;
    private final String symbol;
    private final double priceUsd;
}
