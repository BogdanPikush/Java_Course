package com.api.services;

import com.api.models.ExchangeOffer;
import com.api.models.History;
import com.api.repositories.ExchangeOfferRepository;
import com.api.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeOfferService {

    @Autowired
    private ExchangeOfferRepository exchangeOfferRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public ExchangeOffer createExchangeOffer(ExchangeOffer exchangeOffer) {
        return exchangeOfferRepository.save(exchangeOffer);
    }

    public void approveExchangeOffer(String offerId) {
        Optional<ExchangeOffer> optionalOffer = exchangeOfferRepository.findById(offerId);
        if (optionalOffer.isPresent()) {
            ExchangeOffer offer = optionalOffer.get();

            History history = new History(offer.getThingId(), offer.getFromUserId(), offer.getToUserId());
            historyRepository.save(history);

            exchangeOfferRepository.deleteById(offerId);
        } else {
            throw new IllegalArgumentException("Exchange offer not found");
        }
    }

    public void rejectExchangeOffer(String offerId) {
        exchangeOfferRepository.deleteById(offerId);
    }

}
