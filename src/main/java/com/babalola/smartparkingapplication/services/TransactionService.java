package com.babalola.smartparkingapplication.services;


import com.babalola.smartparkingapplication.dtos.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto);
    TransactionDto getTransactionById(Long transactionId);
    List<TransactionDto> getAllTransactions();
}
