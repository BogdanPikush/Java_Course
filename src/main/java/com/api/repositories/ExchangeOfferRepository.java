package com.api.repositories;

import com.api.models.ExchangeOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeOfferRepository extends MongoRepository<ExchangeOffer, String> {
    List<ExchangeOffer> findByToUserId(String toUserId);
}
