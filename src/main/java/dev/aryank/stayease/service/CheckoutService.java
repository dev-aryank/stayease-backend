package dev.aryank.stayease.service;

import dev.aryank.stayease.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
