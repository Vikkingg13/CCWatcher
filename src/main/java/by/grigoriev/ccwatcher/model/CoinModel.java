package by.grigoriev.ccwatcher.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CoinModel {

    @Id
    private String id;

    private String symbol;

    private String name;

    private double priceUSD;
}
