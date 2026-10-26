import java.util.List;

public interface LootStrategy {
    // Input: nonnegative gold and a nonempty list of non-null members.
    // Output: one nonnegative share per member, in list order, summing to gold.
    int[] distribute(int gold, List<PartyMember> members);
}
