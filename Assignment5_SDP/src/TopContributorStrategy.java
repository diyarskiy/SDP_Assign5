import java.util.List;

public class TopContributorStrategy implements LootStrategy {
    @Override
    public int[] distribute(int gold, List<PartyMember> members) {
        int winnerIndex = 0;
        for (int i = 1; i < members.size(); i++) {
            if (members.get(i).getContribution() > members.get(winnerIndex).getContribution()) {
                winnerIndex = i;
            }
        }

        int[] shares = new int[members.size()];
        shares[winnerIndex] = gold;
        return shares;
    }
}
