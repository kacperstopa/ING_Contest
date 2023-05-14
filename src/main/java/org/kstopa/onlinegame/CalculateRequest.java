package org.kstopa.onlinegame;

import java.util.List;

public record CalculateRequest(int groupCount, List<Clan> clans) {

    public boolean isValid() {
        return groupCount > 0 && clans.stream().allMatch(Clan::isValid);
    }

}
