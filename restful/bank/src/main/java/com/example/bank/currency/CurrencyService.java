package com.example.bank.currency;

import com.example.bank.currency.currency_projections.CodeRateProjection;
import com.example.bank.currency.currency_projections.CurrencyInfo;
import com.example.bank.exceptions.BadRequestException;
import com.example.bank.exceptions.NoContentException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Iterable<CodeRateProjection> findCodeRateDTOs() {

        isFound();
        return currencyRepository.findBy(CodeRateProjection.class);
    }

    public Iterable<CurrencyInfo> findAllDTOs() {

        isFound();
        return currencyRepository.findBy(CurrencyInfo.class);
    }

    public Iterable<CodeRateProjection> findCodeRateDTOs(String code) {

        if (code == null || code.trim().isEmpty() || code.trim().length() > 3)
            throw new BadRequestException("Make sure code maximum of 3 letters and not empty.");

        if (!currencyRepository.existsCurrenciesByCode(code))
            throw new NoContentException("Sorry, currency with code: (" + code + ") Dose not exists.");

        return currencyRepository.findByCode(CodeRateProjection.class, code);
    }

    private void isFound() {

        if (currencyRepository.existsAny() < 1)
            throw new NoContentException("Sorry, no currencies data to provide with.");
    }

    Boolean updateRate(Long id, float newRate) {

        if (id <= 0)
            throw new BadRequestException("Not excepted id (" + id + ").");

        if (newRate <= 0)
            throw new NotAcceptableStatusException("Not excepted data make sure (Rate) is positive and not (0).");

        if (currencyRepository.findById(id).isEmpty())
            throw new NoContentException("No Currency Found.");

        Currency currency = currencyRepository.findById(id).get();
        currency.setRate(newRate);
        currencyRepository.save(currency);

        return true;
    }

}
