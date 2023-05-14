package org.kstopa.transactions;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsServiceTest {

    TransactionsService transactionsService = new TransactionsService();

    @Test
    void shouldCalculateEmptyRequest() {
        final var result = transactionsService.calculate(Collections.emptyList());
        final var expectedResult = Collections.emptyList();
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldCalculateNonEmptyRequest() {
        final var result = transactionsService.calculate(
                List.of(
                        new Transaction("1", "2", BigDecimal.valueOf(1)),
                        new Transaction("3", "4", BigDecimal.valueOf(2)),
                        new Transaction("5", "1", BigDecimal.valueOf(3)),
                        new Transaction("2", "3", BigDecimal.valueOf(4)),
                        new Transaction("4", "5", BigDecimal.valueOf(5)),
                        new Transaction("1", "3", BigDecimal.valueOf(6))
                )
        );
        final var expectedResult = List.of(
                new Account("1", 1, 2, BigDecimal.valueOf(-4)),
                new Account("2", 1, 1, BigDecimal.valueOf(-3)),
                new Account("3", 2, 1, BigDecimal.valueOf(8)),
                new Account("4", 1, 1, BigDecimal.valueOf(-3)),
                new Account("5", 1, 1, BigDecimal.valueOf(2))
        );

        assertEquals(expectedResult, result);
    }
}
