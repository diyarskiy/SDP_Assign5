import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PartyMember> party = List.of(
                new PartyMember("Alinur", 50),
                new PartyMember("Madi", 30),
                new PartyMember("Robin", 20)
        );
        int gold = 101;
        LootDistributor distributor = new LootDistributor(new EqualShareStrategy());

        System.out.println("=== RPG LOOT DISTRIBUTION ===");
        System.out.println("Gold: " + gold);
        showShares("Equal shares", party, distributor.distributeLoot(gold, party));

        distributor.setStrategy(new ContributionShareStrategy());
        showShares("Contribution shares", party, distributor.distributeLoot(gold, party));

        distributor.setStrategy(new TopContributorStrategy());
        showShares("Top contributor", party, distributor.distributeLoot(gold, party));
    }

    private static void showShares(String title, List<PartyMember> party, int[] shares) {
        System.out.println("\n" + title + ":");
        for (int i = 0; i < party.size(); i++) {
            System.out.println(party.get(i).getName() + ": " + shares[i] + " gold");
        }
    }
}
