package com.babalola.smartparkingapplication.infrastructure.services.payments;

import com.babalola.smartparkingapplication.domain.entities.Payment;
import com.babalola.smartparkingapplication.domain.entities.Transaction;

public interface PaymentService {
    Payment processPayment(Transaction transaction);
}
