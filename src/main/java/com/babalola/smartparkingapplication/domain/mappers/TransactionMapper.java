package com.babalola.smartparkingapplication.domain.mappers;


import com.babalola.smartparkingapplication.domain.entities.Transaction;
import com.babalola.smartparkingapplication.dtos.TransactionDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "booking.id", target = "bookingId")
    TransactionDto transactionToTransactionDto(Transaction transaction);

    @Mapping(source = "bookingId", target = "booking.id")
    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    @Mapping(source = "bookingId", target = "booking.id")
    void updateTransactionFromDto(TransactionDto transactionDto, @MappingTarget Transaction transaction);
}
