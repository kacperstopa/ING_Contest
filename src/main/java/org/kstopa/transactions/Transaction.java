package org.kstopa.transactions;

import java.math.BigDecimal;

public record Transaction(
        String debitAccount,
        String creditAccount,
        BigDecimal amount
) {

    public boolean isValid() {
        return debitAccount.matches("[0-9]+") && creditAccount.matches("[0-9]+");
    }

}
