package com.babalola.smartparkingapplication.events.listeners;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EntityTransactionLogListener {
    @Getter
    @AllArgsConstructor
    public static class TransactionEvent {
        private final TransactionType transactionType;
        private final String entityName;
        private final String entityId;

        public enum TransactionType {
            CREATE, UPDATE, DELETE
        }
    }
}
