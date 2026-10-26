import java.util.List;
import java.util.Objects;

public class LootDistributor {
    private LootStrategy strategy;

    public LootDistributor(LootStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy cannot be null");
    }

    public void setStrategy(LootStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy cannot be null");
    }

    public int[] distributeLoot(int gold, List<PartyMember> members) {
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be negative");
        }
        List<PartyMember> party = List.copyOf(members);
        if (party.isEmpty()) {
            throw new IllegalArgumentException("Party cannot be empty");
        }
        return strategy.distribute(gold, party);
    }
}
