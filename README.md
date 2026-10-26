# Assignment 5 — Strategy Pattern

## RPG loot distribution

After a dungeon, an RPG party distributes a gold reward. The party can change the distribution rule at runtime. This continues the RPG domain used in Assignments 1–4, with an independent, runnable example of Strategy.

### Pattern components

| Role | Class | Responsibility |
| --- | --- | --- |
| Strategy | `LootStrategy` | Declares `distribute(int gold, List<PartyMember> members)` |
| Concrete strategy | `EqualShareStrategy` | Splits gold equally; extra coins go to the first members |
| Concrete strategy | `ContributionShareStrategy` | Splits gold in proportion to positive contribution points |
| Concrete strategy | `TopContributorStrategy` | Gives all gold to the member with the highest contribution |
| Context | `LootDistributor` | Stores a strategy, validates input and delegates execution |
| Client | `Main` | Creates the party, selects strategies and displays results |
| Domain data | `PartyMember` | Stores an immutable name and contribution score |

`Main -> LootDistributor -> LootStrategy <- concrete strategies`

The same `LootDistributor` object executes all three strategies. `setStrategy()` changes its behavior while the program is running. There is no strategy-type `if`, `switch` or `instanceof` in the context or client. Validation checks and comparisons inside algorithms are not strategy selection.

## Run

Requires JDK 11 or newer. No external libraries or build tools.
From this repository's root:

```sh
mkdir -p out
javac --release 11 -d out Assignment5_SDP/src/*.java
java -cp out Main
```

In IntelliJ IDEA, open this folder, set the project JDK to 11 or newer, mark `Assignment5_SDP/src` as Sources Root and run `Main`.

### Expected output

```text
=== RPG LOOT DISTRIBUTION ===
Gold: 101

Equal shares:
Alinur: 34 gold
Madi: 34 gold
Robin: 33 gold

Contribution shares:
Alinur: 50 gold
Madi: 30 gold
Robin: 21 gold

Top contributor:
Alinur: 101 gold
Madi: 0 gold
Robin: 0 gold
```

## Algorithm rules and edge cases

- Gold is an integer greater than or equal to zero. Zero gold produces zero shares.
- A party must contain at least one non-null member. Names must be nonblank and contributions positive.
- The returned array follows party order. Shares are nonnegative and always sum to the original reward.
- Equal distribution gives each member `gold / partySize`, then gives one extra coin to each of the first `gold % partySize` members.
- Contribution distribution uses integer arithmetic with a carried remainder. For each member: `weightedGold = gold * contribution + previousRemainder`, `share = weightedGold / totalContribution`, and `remainder = weightedGold % totalContribution`. Party order determines how fractional coins are rounded. With 101 gold and scores 50, 30, 20, the result is 50, 30, 21. The final remainder is zero because all contributions have been processed. `long` arithmetic prevents overflow in intermediate calculations.
- If the highest contribution is tied, the first tied member receives the reward.
- Strategies calculate shares without modifying party data or printing. The client handles presentation. Calling the example again calculates a new distribution; it does not credit a persistent inventory.
- `LootDistributor` checks common preconditions. Direct callers of `LootStrategy.distribute()` must respect the documented interface contract.

## Clean Code principles

1. **Meaningful names.** `EqualShareStrategy`, `getContribution()` and `distributeLoot()` communicate their intent without unexplained abbreviations.
2. **Single responsibility and small classes.** Each strategy implements one distribution algorithm. `PartyMember` stores data; `LootDistributor` validates and delegates; `Main` configures the demonstration and prints its results. Changing a reward formula does not change presentation code.
3. **Encapsulation.** The strategy reference is private and can only be replaced through a null-checked setter. Member fields are private and final. The context passes an immutable snapshot of the party to strategies.
4. **Polymorphism instead of type branching.** The context invokes `strategy.distribute(...)`; Java chooses the implementation. Adding a strategy does not require another strategy-type condition.
5. **Depend on abstractions.** `LootDistributor` refers only to `LootStrategy`. It does not import or construct concrete strategies or know their formulas.
6. **Open for extension, closed for modification.** `examples/LeaderTakesAllStrategy.java` adds a fourth algorithm. `examples/ExtensionDemo.java` uses it without changing any existing source class, including `Main`.
7. **Small, focused methods and separation of concerns.** Calculation returns data, while `showShares()` prints it. This makes calculations independently verifiable and reusable.

These choices follow the Strategy structure in Lecture 7 and the class organization, encapsulation, cohesion and organizing-for-change discussion in its Clean Code Chapter 10 section. The domain differs from the lecture's sorting, navigation, payment and duck behavior examples.

## Extension demonstration

The optional fourth strategy rewards the leader (first member), independently of contribution scores:

```sh
javac --release 11 -d out Assignment5_SDP/src/*.java examples/*.java
java -cp out ExtensionDemo
```

Expected result: `[101, 0, 0]`. The extension consists entirely of new files; existing classes remain unchanged.

## Submission

This folder contains the source and documentation for a standalone GitHub repository. Upload its contents to your assignment repository and submit that repository's URL. Confirm that the topic is unique within your group, as required by the assignment.

For a Russian explanation and paper-based defense practice, see `DEFENSE_RU.md`.
