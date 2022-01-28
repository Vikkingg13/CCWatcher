package by.grigoriev.ccwatcher.repository;

import by.grigoriev.ccwatcher.model.CoinModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends CrudRepository<CoinModel, Long> {
    CoinModel findCoinBySymbol(String symbol);
}
