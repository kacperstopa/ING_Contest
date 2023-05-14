package org.kstopa.transactions;

import java.math.BigDecimal;

public record Account(String account, int creditCount, int debitCount, BigDecimal balance) {

    Account debit(BigDecimal amount) {
        return new Account(account, creditCount, debitCount + 1, balance.subtract(amount));
    }

    Account credit(BigDecimal amount) {
        return new Account(account, creditCount + 1, debitCount, balance.add(amount));
    }

    static Account empty(String account) {
        return new Account(account, 0, 0, BigDecimal.ZERO);
    }
}