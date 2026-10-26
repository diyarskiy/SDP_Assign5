import java.util.Arrays;
import java.util.List;

public class ExtensionDemo {
    public static void main(String[] args) {
        List<PartyMember> party = List.of(
                new PartyMember("Alinur", 50),
                new PartyMember("Madi", 30),
                new PartyMember("Robin", 20)
        );
        LootDistributor distributor = new LootDistributor(new LeaderTakesAllStrategy());
        System.out.println(Arrays.toString(distributor.distributeLoot(101, party)));
    }
}
