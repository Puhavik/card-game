package vehicle;

import java.util.*;

public class Player implements Comparable<Player>{
  private String name;
  private Queue<VehicleCard> deck = new ArrayDeque<>();

  public Player(final String name) {
    if(name == null || name.length() == 0){
      throw new IllegalArgumentException ("Player's Name Error");
    }
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getScore(){
    int score = 0;
    for(VehicleCard card : deck){
      score += card.totalBonus();
    }
    return score;
  }

  public void addCards(final Collection <VehicleCard > cards) {
    for(VehicleCard card : cards){
      deck.add(card);
    }
  }

  public void addCard(final VehicleCard card) {
    deck.add(card);
  }

  public void clearDeck () {
    deck.clear();
  }

  public List<VehicleCard> getDeck() {
    List<VehicleCard> list = new ArrayList<>(deck);
    return list;
  }

  protected VehicleCard peekNextCard() {
    return deck.peek();
  }

  public VehicleCard playNextCard() {
    return deck.poll();
  }

  public int compareTo(final Player other) {
    if (other.getName().toLowerCase() == this.getName().toLowerCase()){
      return 1;
    }
    return 0;
  }

  @Override
  public int hashCode() {
    return name.toLowerCase().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return ((Player)obj).getName().toLowerCase().equals(this.name.toLowerCase());
  }

  @Override
  public String toString() {
    String endResult = "";
    endResult += name + "(" + getScore() + "):";
    for(VehicleCard card : deck){
      endResult += "\n" + card.toString();
    }
    return endResult;
  }

  public boolean challengePlayer(Player p) {
    if (p == null || p.equals(this)){
      throw new IllegalArgumentException ("Challenge Player Error");
    }
    Queue<VehicleCard> restP1 = new ArrayDeque<>();
    Queue<VehicleCard> restP2 = new ArrayDeque<>();

    VehicleCard firstPCard;
    VehicleCard secondPCard;

    do {
      firstPCard = this.playNextCard();
      secondPCard = p.playNextCard();
      if (firstPCard != null) {restP1.add(firstPCard);}
      if (secondPCard != null) {restP2.add(secondPCard);}
      if(firstPCard == null || secondPCard == null){
        this.addCards(restP1);
        p.addCards(restP2);
        return false;
      }
      if(firstPCard.totalBonus() > secondPCard.totalBonus()){
        this.addCards(restP1);
        this.addCards(restP2);
        return true;
      } else if(secondPCard.totalBonus() > firstPCard.totalBonus()) {
        p.addCards(restP1);
        p.addCards(restP2);
        return false;
      }
    } while (firstPCard.totalBonus() == secondPCard.totalBonus());

    return false;
  }

  public static Comparator<Player> compareByScore() {
    return new Comparator<Player>(){
      @Override
      public int compare(Player a, Player b) {
        return a.getScore() - b.getScore();
      }
    };
  }

  public static Comparator<Player> compareByDeckSize() {
    return new Comparator<Player>(){
      @Override
      public int compare(Player a, Player b){
        return a.getDeck().size() - b.getDeck().size();
      }
    };
  }
}
