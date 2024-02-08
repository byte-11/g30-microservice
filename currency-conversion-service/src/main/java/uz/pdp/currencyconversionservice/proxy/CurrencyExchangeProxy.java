package uz.pdp.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.currencyconversionservice.model.CurrencyConversion;

//@FeignClient(value = "currency-exchange-rates", url = "http://localhost:8001")
@FeignClient(value = "currency-exchange-rates")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency/from/{from}/to/{to}")
    CurrencyConversion getCurrency(
            @PathVariable String from,
            @PathVariable String to
    );
}
