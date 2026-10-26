import java.util.List;

public class EqualShareStrategy implements LootStrategy {
    @Override
    public int[] distribute(int gold, List<PartyMember> members) {
        int[] shares = new int[members.size()];
        int baseShare = gold / members.size();
        int remainingGold = gold % members.size();

        for (int i = 0; i < shares.length; i++) {
            shares[i] = baseShare;
            if (i < remainingGold) {
                shares[i]++;
            }
        }
        return shares;
    }
}
