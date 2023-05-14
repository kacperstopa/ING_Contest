package org.kstopa.atm;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmServiceTest {

    AtmService atmService = new AtmService();

    @Test
    void shouldCalculateEmptyRequest() {
        final var result = atmService.calculate(Collections.emptyList());
        final var expectedResult = Collections.emptyList();
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldCalculateNonEmptyRequest() {
        final var result = atmService.calculate(List.of(
                new AtmRequest(2, RequestType.STANDARD, 8),
                new AtmRequest(2, RequestType.SIGNAL_LOW, 7),
                new AtmRequest(2, RequestType.PRIORITY, 6),
                new AtmRequest(2, RequestType.FAILURE_RESTART, 5),
                new AtmRequest(1, RequestType.STANDARD, 4),
                new AtmRequest(1, RequestType.SIGNAL_LOW, 3),
                new AtmRequest(1, RequestType.FAILURE_RESTART, 3),
                new AtmRequest(1, RequestType.PRIORITY, 2),
                new AtmRequest(1, RequestType.FAILURE_RESTART, 1)
        ));
        final var expectedResult = List.of(
                new AtmResponse(1, 3),
                new AtmResponse(1, 1),
                new AtmResponse(1, 2),
                new AtmResponse(1, 4),
                new AtmResponse(2, 5),
                new AtmResponse(2, 6),
                new AtmResponse(2, 7),
                new AtmResponse(2, 8)
        );

        assertEquals(expectedResult, result);
    }
}
