package com.example.bank.currency;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Query(value = "SELECT 1 FROM Currency LIMIT 1", nativeQuery = true)
    Integer existsAny();

    Boolean existsCurrenciesByCode(String code);

    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByCode(Class<T> type, String code);
}