package com.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "echangeOffers")
public class ExchangeOffer {
    @Id
    private String id;
    private String thingId;
    private String fromUserId;
    private String toUserId;

}
