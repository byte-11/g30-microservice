package uz.pdp.currencyconversionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.pdp.currencyconversionservice.model.CurrencyConversion;
import uz.pdp.currencyconversionservice.proxy.CurrencyExchangeProxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/amount/{amount}")
    public CurrencyConversion currencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal amount
    ) {
        ResponseEntity<CurrencyConversion> entity = new RestTemplate().getForEntity(
                "http://localhost:8001/currency/from/{from}/to/{to}",
                CurrencyConversion.class,
                Map.of("from", from, "to", to)
        );
        CurrencyConversion currency = entity.getBody();
        if (currency != null) {
            currency.setTotalExchangeValue(amount.multiply(currency.getExchangeValue()));
            currency.setTotalSalesValue(amount.multiply(currency.getSalesValue()));
            currency.setDate(new Date());
        }
        return currency;
    }


    @GetMapping("/feign/currency-conversion/from/{from}/to/{to}/amount/{amount}")
    public CurrencyConversion currencyConversion2(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal amount
    ) {
        CurrencyConversion currency = currencyExchangeProxy.getCurrency(from, to);
        if (currency != null) {
            currency.setTotalExchangeValue(amount.multiply(currency.getExchangeValue()));
            currency.setTotalSalesValue(amount.multiply(currency.getSalesValue()));
            currency.setDate(new Date());
        }
        return currency;
    }
}
