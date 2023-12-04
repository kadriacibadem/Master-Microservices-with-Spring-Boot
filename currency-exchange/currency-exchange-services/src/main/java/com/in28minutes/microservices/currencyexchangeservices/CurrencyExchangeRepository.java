package com.in28minutes.microservices.currencyexchangeservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

        CurrencyExchange findByFromAndTo(String from, String to);
}
