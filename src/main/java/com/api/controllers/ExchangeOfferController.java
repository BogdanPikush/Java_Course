package com.api.controllers;

import com.api.models.ExchangeOffer;
import com.api.services.ExchangeOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange-offers")
public class ExchangeOfferController {

    @Autowired
    private ExchangeOfferService exchangeOfferService;

    @PostMapping("/create")
    public ResponseEntity<ExchangeOffer> createExchangeOffer(@RequestBody ExchangeOffer exchangeOffer) {
        ExchangeOffer createdOffer = exchangeOfferService.createExchangeOffer(exchangeOffer);
        return ResponseEntity.ok(createdOffer);
    }

    @PostMapping("/approve/{offerId}")
    public ResponseEntity<String> approveExchangeOffer(@PathVariable String offerId) {
        try {
            exchangeOfferService.approveExchangeOffer(offerId);
            return ResponseEntity.ok("Exchange offer approved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reject/{offerId}")
    public ResponseEntity<String> rejectExchangeOffer(@PathVariable String offerId) {
        try {
            exchangeOfferService.rejectExchangeOffer(offerId);
            return ResponseEntity.ok("Exchange offer rejected successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
