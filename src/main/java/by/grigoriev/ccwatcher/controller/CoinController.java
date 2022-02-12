package by.grigoriev.ccwatcher.controller;

import by.grigoriev.ccwatcher.model.CoinModel;
import by.grigoriev.ccwatcher.service.CoinService;
import by.grigoriev.ccwatcher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/coins")
@RestController
public class CoinController {

    @Autowired
    private CoinService service;

    @Autowired
    private UserService notifyService;

    @GetMapping
    public List<CoinModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{symbol}")
    public CoinModel findBySymbol(@PathVariable String symbol) {
        return service.findBySymbol(symbol);
    }

    @GetMapping("/notify")
    public CoinModel notify(@RequestParam String name, @RequestParam String symbol) {
        CoinModel coin = service.findBySymbol(symbol);
        notifyService.addUser(name, symbol, coin.getPriceUSD());
        return coin;
    }
}
