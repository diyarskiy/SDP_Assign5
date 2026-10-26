public class PartyMember {
    private final String name;
    private final int contribution;

    public PartyMember(String name, int contribution) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (contribution <= 0) {
            throw new IllegalArgumentException("Contribution must be positive");
        }
        this.name = name;
        this.contribution = contribution;
    }

    public String getName() {
        return name;
    }

    public int getContribution() {
        return contribution;
    }
}
