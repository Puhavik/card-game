package vehicle;

import vehicle.VehicleCard;
import vehicle.VehicleCard.Category;
import vehicle.FoilVehicleCard;
import vehicle.Player;
import java.util.*;
import static java.util.Arrays.asList;

public class Main {
  public static void main(String[] args) {
    // System.out.println("main...");
    // Map<Category, Double> myMap = new HashMap<>();
    // FoilVehicleCard card1 = new FoilVehicleCard("BMW" , VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49),Set.of(VehicleCard.Category.DISPLACEMENT_CCM, VehicleCard.Category.WEIGHT_LBS));
    // VehicleCard card2 = new VehicleCard("BMW" , VehicleCard.newMap(20.4, 5., 375., 9., 1234., 21., 49));
    // System.out.println(card1);
    // System.out.println(card1.hashCode());
    // System.out.println(card2.hashCode());
    // System.out.println(card1.equals(card2));
    // System.out.println(card1.compareTo(card2));
    // System.out.println(card1.getCategories());
    /**
         * NOTE: THIS ACTUALLY GUARANTEES NOTHING - WRITE YOUR OWN TESTS!
         */
        final VehicleCard ente = new VehicleCard("Ente", VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49));
        final FoilVehicleCard amphicar = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.), Set.of(VehicleCard.Category.DISPLACEMENT_CCM, VehicleCard.Category.WEIGHT_LBS));

        System.out.println(ente);
        System.out.println(amphicar);
        /**
         * Expected Output:
         - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
         - Amphicar 770(2404) -> {*Gewicht[lbs]*=2314.0, *Hubraum[cc]*=1147.0, Leistung[hp]=38.0, Zylinder=4.0, Beschleunigung=14.0, Miles/Galon=21.0, Baujahr[19xx]=61.0}
         */

        Player otto = new Player("Otto");
        Player anna = new Player("Anna");

        anna.addCards(asList(ente, ente, amphicar));
        otto.addCards(asList(ente, ente, ente));

        System.out.println(otto.challengePlayer(anna));
        System.out.println(anna);
        System.out.println(otto);

        /**
         * Expected Output (order of Anna's cards may vary):
           false
           Anna(-1586):
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Amphicar 770(2404) -> {*Gewicht[lbs]*=2314.0, *Hubraum[cc]*=1147.0, Leistung[hp]=38.0, Zylinder=4.0, Beschleunigung=14.0, Miles/Galon=21.0, Baujahr[19xx]=61.0}
           Otto(0):
         */

         final VehicleCard bmw = new VehicleCard("BMW", VehicleCard.newMap(10., 10., 10., 10., 10., 10., 10));
         final VehicleCard audi = new VehicleCard("Audi", VehicleCard.newMap(1., 1., 1., 1., 1., 1., 1));
         final VehicleCard vw = new VehicleCard("VW", VehicleCard.newMap(1., 1., 1., 1., 1., 1., 1));
         Collection<VehicleCard> collect = new ArrayList<>();

         collect.add(ente);
         collect.add(bmw);
         collect.add(audi);
         Collection<VehicleCard> removable = removeBelow(collect, 9.);
         System.out.println("\n Remove Below \n");
         for(VehicleCard card : removable){
           System.out.println(card);
         }
         System.out.println("\n rest \n");
         for(VehicleCard card : collect){
           System.out.println(card);
         }

         Player vik = new Player("Vik");
         vik.addCards(asList(vw, bmw, audi));

         Map<Category, VehicleCard> getMax = getMaxVehiclePerCategory(vik);
         for(Map.Entry<Category, VehicleCard> entry : getMax.entrySet()) {
           System.out.println(entry.getKey() + " " + entry.getValue() + "\n");
         }
       }

       private static Collection<VehicleCard> removeBelow(Collection<VehicleCard> cards, Double val){
         if(val == null || cards == null || val <= 0 || cards.size() == 0){
           throw new IllegalArgumentException ("Error removeBelow");
         }
         Collection<VehicleCard> myCards = new ArrayList<>();
         // adding cards < val to myCards
         for(VehicleCard card : cards){
           Map <Category, Double> myMap = card.getCategories();
           for(Map.Entry<Category, Double> entry : myMap.entrySet()){
             if(entry.getValue() < val){
               myCards.add(card);
               break;
             }
           }
         }
         List<String> myList = new ArrayList<>();
         //adding Card's name to List
         for(VehicleCard card : myCards){
           myList.add(card.getName());
         }
         //sorting List with names
         Collections.sort(myList);
         //adding sorted names from list to Map
         Collection<VehicleCard> returnCards = new ArrayList<>();
         for(String cardName : myList){
           for(VehicleCard card : myCards){
             if(cardName == card.getName()){
               returnCards.add(card);
             }
           }
         }
         Collection<VehicleCard> all = cards;
         for(VehicleCard card : all){
           for(VehicleCard card2 : returnCards){
             if(all.contains(card2)){
               cards.remove(card2);
             }
           }
         }
         return returnCards;
       }
       private static Map<Category, VehicleCard> getMaxVehiclePerCategory(Player p){
         if(p == null || p.getDeck().size() == 0){
           throw new IllegalArgumentException ("GetMaxa... Error");
         }
         //its a buffer
         Map<Category, Double> res = new HashMap<>();
         //return this
         Map<Category, VehicleCard> myMap = new HashMap<>();
         //get all cards
         List<VehicleCard> myCards = p.getDeck();
         //adding something to res
         for(VehicleCard card : myCards){
           Map<Category, Double> myCard = card.getCategories();
           for(Map.Entry<Category, Double> entry3 : myCard.entrySet()){
             res.put(entry3.getKey(), 0.0);
           }
         }
         //finding card with the biggest value per category
         for(VehicleCard card : myCards){
           Map<Category, Double> myCard = card.getCategories();
           //going through buffer
           for(Map.Entry<Category, Double> entry : res.entrySet()){
             //going through categories in card
             for(Map.Entry<Category, Double> entry2 : myCard.entrySet()){
               if(entry2.getKey() == entry.getKey() && entry2.getValue() > entry.getValue()){
                 res.replace(entry.getKey(), entry2.getValue());
                 myMap.put(entry.getKey(), card);
               }
             }
           }
         }
         return myMap;
       }
     }
