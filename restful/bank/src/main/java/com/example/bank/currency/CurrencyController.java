package com.example.bank.currency;

import com.example.bank.currency.currency_projections.CodeRateProjection;
import com.example.bank.currency.currency_projections.CurrencyInfo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/currency/")
public class CurrencyController {


    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of currencies."),
            @ApiResponse(responseCode = "204", description = "Successful request, but no currency data to retrieve"),
    })
    @GetMapping("code_rate")
    Iterable<CodeRateProjection> codeRateDTOs() {
        return currencyService.findCodeRateDTOs();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of currencies."),
            @ApiResponse(responseCode = "204", description = "Successful request, but no currency data to retrieve"),
    })
    @GetMapping("currencies")
    Iterable<CurrencyInfo> currencies() {
        return currencyService.findAllDTOs();
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of currencies."),
            @ApiResponse(responseCode = "204", description = "Successful request, but no currency data to retrieve"),
            @ApiResponse(responseCode = "404", description = "Code is more than 3 letters or empty."),
    })
    @GetMapping("code/{code}")
    Iterable<CodeRateProjection> currenciesByCode(@PathVariable String code) {
        return currencyService.findCodeRateDTOs(code);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data fetched Successfully"),
            @ApiResponse(responseCode = "204", description = "Successful request, but no currency found"),
            @ApiResponse(responseCode = "400", description = "invalid id"),
            @ApiResponse(responseCode = "406", description = "not excepted data make sure (Rate) is positive and not (0).")
    })

    @PutMapping("rate/{id}")
    Boolean rate(@PathVariable("id") Long id, @RequestBody float newRate) {
        return currencyService.updateRate(id, newRate);
    }
}
