import java.util.List;

// Extension example: the first party member is the leader.
public class LeaderTakesAllStrategy implements LootStrategy {
    @Override
    public int[] distribute(int gold, List<PartyMember> members) {
        int[] shares = new int[members.size()];
        shares[0] = gold;
        return shares;
    }
}
