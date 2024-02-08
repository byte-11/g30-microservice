package uz.pdp.currencyexchangeratesservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.currencyexchangeratesservice.model.Currency;

import java.math.BigDecimal;

@RestController
public class CurrencyController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @GetMapping("/currency/from/{from}/to/{to}")
    public Currency getCurrency(
            @PathVariable String from,
            @PathVariable String to
    ){
        return Currency.builder()
                .title("US dollar")
                .code("USD")
                .exchangeValue(BigDecimal.valueOf(12400))
                .salesValue(BigDecimal.valueOf(12460))
                .environment("%s - %s".formatted(appName, port))
                .build();
    }
}
