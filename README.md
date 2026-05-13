# Vehicle Card Game (Java)

A compact Java project that models a **Top Trumps-style vehicle card game** with support for:

- regular vehicle cards,
- foil/special cards with boosted categories,
- player decks and challenge rounds,
- utility methods for card filtering and category-based max lookup.

The code is intentionally small and easy to read, which makes it a good project for learning OOP basics in Java (inheritance, enums, comparators, collections, encapsulation).

---

## Project Structure

- `VehicleCard.java` – base card model with 7 fixed categories and score calculation.
- `FoilVehicleCard.java` – subclass of `VehicleCard` that boosts selected categories.
- `Player.java` – player/deck logic, score calculation, and duel (`challengePlayer`).
- `Main.java` – demo playground with sample objects and helper routines.

---

## Core Concepts

### 1) VehicleCard

Each `VehicleCard` contains:

- a `name`,
- a category map (`Map<VehicleCard.Category, Double>`),
- computed `totalBonus()` score.

`VehicleCard.Category` includes 7 attributes:

1. `ECONOMY_MPG`
2. `CYLINDERS_CNT`
3. `DISPLACEMENT_CCM`
4. `POWER_HP`
5. `WEIGHT_LBS` *(inverted in scoring)*
6. `ACCELERATION` *(inverted in scoring)*
7. `YEAR`

Scoring rules:

- most categories add their integer value to the score,
- inverted categories (`WEIGHT_LBS`, `ACCELERATION`) are subtracted,
- resulting total is the card bonus used in comparisons/challenges.

---

### 2) FoilVehicleCard

`FoilVehicleCard` extends `VehicleCard` and adds a `specials` set (up to 3 categories).

For each special category, it adds an extra positive amount (`abs(value)`) on top of the base score. This makes foil cards potentially much stronger than normal ones in selected stats.

---

### 3) Player

A `Player` contains:

- `name`,
- `deck` (`Queue<VehicleCard>`).

Main operations:

- add one or many cards,
- play/peek next card,
- clear deck,
- compute total score (sum of all card bonuses),
- challenge another player via `challengePlayer(Player p)`.

`challengePlayer` mechanics:

- both players reveal cards from the top of their decks,
- if one card has higher bonus, that player takes all currently revealed cards,
- ties continue revealing until someone wins or one deck runs out,
- if deck runs out during tie chain, method restores revealed cards and returns `false`.

---

## Build & Run

> Package name is `vehicle`, so compile with `-d` to generate package directories.

```bash
javac -d . Main.java VehicleCard.java FoilVehicleCard.java Player.java
java vehicle.Main
```

---

## Example Output

`Main` prints:

- sample `VehicleCard` and `FoilVehicleCard` instances,
- duel result between two players,
- deck states after challenge,
- results of helper methods:
  - `removeBelow(...)`
  - `getMaxVehiclePerCategory(...)`

(Exact order of map/deck printing may vary.)

---

## Validation & Error Handling

The code uses `IllegalArgumentException` checks for invalid input, for example:

- empty player/card names,
- null/invalid category maps,
- invalid special category sets,
- illegal challenge targets.

---

## Notes for Improvement

If you want to evolve this into production-quality code, consider:

- adding JUnit tests for each class and edge case,
- using `equalsIgnoreCase` / `.equals(...)` instead of `==` for string comparison,
- refining `compareTo` implementations to satisfy comparator contracts,
- improving deterministic output order by using ordered maps where needed,
- separating demo code from reusable game engine logic.

---

## Requirements

- Java 8+ (works best on modern LTS JDK such as 17 or 21).

