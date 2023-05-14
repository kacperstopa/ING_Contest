package org.kstopa.onlinegame;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlinegameServiceTest {

    OnlinegameService onlinegameService = new OnlinegameService();

    @Test
    void shouldCalculateEmptyRequest() {
        final var result = onlinegameService.calculate(5, Collections.emptyList());
        final var expectedResult = Collections.emptyList();
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldCalculateNonEmptyRequest() {
        final var result = onlinegameService.calculate(
                5,
                List.of(
                        new Clan(3, 10),
                        new Clan(4, 4),
                        new Clan(2, 8),
                        new Clan(3, 8),
                        new Clan(2, 8)
                ));
        final var expectedResult = List.of(
                List.of(
                        new Clan(3, 10),
                        new Clan(2, 8)
                ),
                List.of(
                        new Clan(2, 8),
                        new Clan(3, 8)
                ),
                List.of(
                        new Clan(4, 4)
                )
        );

        assertEquals(expectedResult, result);
    }
}
