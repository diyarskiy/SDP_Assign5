import java.util.List;

public class ContributionShareStrategy implements LootStrategy {
    @Override
    public int[] distribute(int gold, List<PartyMember> members) {
        long totalContribution = 0;
        for (PartyMember member : members) {
            totalContribution += member.getContribution();
        }

        int[] shares = new int[members.size()];
        long remainder = 0;

        for (int i = 0; i < shares.length; i++) {
            // Carry fractional coins forward so no gold is lost to rounding.
            long weightedGold = (long) gold * members.get(i).getContribution() + remainder;
            shares[i] = (int) (weightedGold / totalContribution);
            remainder = weightedGold % totalContribution;
        }
        return shares;
    }
}
