package org.kstopa.onlinegame;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OnlinegameService {

    public List<List<Clan>> calculate(int groupCount, List<Clan> clans) {
        final Comparator<Clan> clanComparator = Comparator.comparing(Clan::points).reversed().thenComparing(Clan::numberOfPlayers);
        final List<Clan> sortedClans = clans.stream().sorted(clanComparator).collect(Collectors.toList());
        final List<List<Clan>> results = new LinkedList<>();

        while (!sortedClans.isEmpty()) {
            final List<Clan> group = new java.util.LinkedList<>(Collections.emptyList());
            int leftSpace = groupCount;
            for (Clan clan : List.copyOf(sortedClans)) {
                if (leftSpace == 0) break;
                if (clan.numberOfPlayers() <= leftSpace) {
                    group.add(clan);
                    sortedClans.remove(clan);
                    leftSpace -= clan.numberOfPlayers();
                }
            }
            results.add(group);
        }

        return results;
    }

}