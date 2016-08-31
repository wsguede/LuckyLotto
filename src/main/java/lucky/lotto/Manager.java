package lucky.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Manager {

    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

    public Map<Integer, String> pickWinners(Map<String, Integer> names, int numWinners,
            boolean cleanTickets, boolean multipleWins) {
        List<String> tickets = createTickets(names);
        shuffleTickets(tickets);

        Map<Integer, String> winners = new HashMap<>();

        String winner = null;
        int winnerIndex = 1;
        while (winners.size() < numWinners && !tickets.isEmpty()) {
            winner = pickAndRemoveWinner(tickets, cleanTickets);
            shuffleTickets(tickets);
            if (!multipleWins && winners.containsValue(winner)) {
                continue;
            }
            winners.put(winnerIndex++, winner);
            LOG.info("{} won. Tickets{}", winner, tickets);

        }

        return winners;
    }

    private List<String> createTickets(Map<String, Integer> names) {
        List<String> tickets = new ArrayList<>();

        if (names != null && !names.isEmpty()) {
            for (Map.Entry<String, Integer> player : names.entrySet()) {
                for (int i = 0; i < player.getValue(); i++) {
                    tickets.add(player.getKey());
                }
            }
            LOG.info("tickets were created! : {}", tickets);
        }

        return tickets;
    }

    private void shuffleTickets(List<String> tickets) {
        if (tickets != null && !tickets.isEmpty()) {
            String[] shuffledTickets = new String[tickets.size()];

            while (!tickets.isEmpty()) {
                // pick a random ticket
                String player = tickets.remove(new Random().nextInt(tickets.size()));
                // put it into random location in the new list (double random!)
                int randomIndex = new Random().nextInt(shuffledTickets.length);
                // if location is full, move to next location (this is an easy solution to waiting
                // for the random location to be empty)
                while (shuffledTickets[randomIndex] != null) {
                    randomIndex = ++randomIndex % shuffledTickets.length;
                }
                shuffledTickets[randomIndex] = player;
            }

            tickets.addAll(Arrays.asList(shuffledTickets));

            LOG.info("tickets were shuffled! : {}", tickets);
        }
    }

    private String pickAndRemoveWinner(List<String> tickets, boolean removeAll) {
        String player = null;

        if (tickets != null && !tickets.isEmpty()) {
            int winnerIndex = new Random().nextInt(tickets.size());
            player = tickets.remove(winnerIndex);
            LOG.info("Picked out ticket {} : {}", winnerIndex, player);
            if (removeAll) {
                while (tickets.contains(player)) {
                    tickets.remove(player);
                }
            }
        }

        return player;
    }

}
