package org.kstopa.onlinegame;

public record Clan(int numberOfPlayers, int points) {

    boolean isValid() {
        return numberOfPlayers > 0 && points > 0;
    }

}
