package com.babalola.smartparkingapplication.services;

import com.babalola.smartparkingapplication.domain.enums.TransactionStatus;
import com.babalola.smartparkingapplication.domain.mappers.TransactionMapper;
import com.babalola.smartparkingapplication.dtos.TransactionDto;
import java.util.List;
import com.babalola.smartparkingapplication.domain.entities.Transaction;
import com.babalola.smartparkingapplication.repositories.TransactionRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.transactionDtoToTransaction(transactionDto);
        transaction.setStatus(TransactionStatus.INITIATED);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transactionMapper.updateTransactionFromDto(transactionDto, transaction);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        return transactionMapper.transactionToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::transactionToTransactionDto)
                .collect(Collectors.toList());
    }
}
