package by.grigoriev.ccwatcher.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Coin {
    private String id;

    private String symbol;

    private String name;

    @SerializedName("price_usd")
    private double priceUsd;

}