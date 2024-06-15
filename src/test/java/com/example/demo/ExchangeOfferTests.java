package com.example.demo;

import com.api.controllers.ExchangeOfferController;
import com.api.models.ExchangeOffer;
import com.api.services.ExchangeOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeOfferTests {
    @Mock
    private ExchangeOfferService exchangeOfferService;

    @InjectMocks
    private ExchangeOfferController exchangeOfferController;

    private ExchangeOffer exchangeOffer;

    @BeforeEach
    void setUp() {
        exchangeOffer = new ExchangeOffer("thing1", "user1", "user2");
    }

    @Test
    void createExchangeOffer_ReturnsCreatedExchangeOffer() {
        when(exchangeOfferService.createExchangeOffer(any(ExchangeOffer.class))).thenReturn(exchangeOffer);

        ResponseEntity<ExchangeOffer> response = exchangeOfferController.createExchangeOffer(exchangeOffer);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(exchangeOffer, response.getBody());
        verify(exchangeOfferService, times(1)).createExchangeOffer(any(ExchangeOffer.class));
    }

    @Test
    void approveExchangeOffer_ReturnsSuccessMessage() {
        doNothing().when(exchangeOfferService).approveExchangeOffer(anyString());

        ResponseEntity<String> response = exchangeOfferController.approveExchangeOffer("offer1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Exchange offer approved successfully", response.getBody());
        verify(exchangeOfferService, times(1)).approveExchangeOffer(anyString());
    }

    @Test
    void approveExchangeOffer_InvalidId_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Offer not found")).when(exchangeOfferService).approveExchangeOffer(anyString());

        ResponseEntity<String> response = exchangeOfferController.approveExchangeOffer("invalid_offer");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Offer not found", response.getBody());
        verify(exchangeOfferService, times(1)).approveExchangeOffer(anyString());
    }

    @Test
    void rejectExchangeOffer_ReturnsSuccessMessage() {
        doNothing().when(exchangeOfferService).rejectExchangeOffer(anyString());

        ResponseEntity<String> response = exchangeOfferController.rejectExchangeOffer("offer1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Exchange offer rejected successfully", response.getBody());
        verify(exchangeOfferService, times(1)).rejectExchangeOffer(anyString());
    }

    @Test
    void rejectExchangeOffer_InvalidId_ReturnsBadRequest() {
        doThrow(new IllegalArgumentException("Offer not found")).when(exchangeOfferService).rejectExchangeOffer(anyString());

        ResponseEntity<String> response = exchangeOfferController.rejectExchangeOffer("invalid_offer");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Offer not found", response.getBody());
        verify(exchangeOfferService, times(1)).rejectExchangeOffer(anyString());
    }
}
