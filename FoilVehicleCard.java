package vehicle;

import java.util.*;

public class FoilVehicleCard extends VehicleCard {
  private Set<Category> specials;

  public FoilVehicleCard(final String name, final Map<Category, Double> categories,final Set<Category> specials){
    super(name, categories);
    if(specials == null || specials.size() > 3 || specials.isEmpty()){
      throw new IllegalArgumentException ("Speciels Error");
    }
    this.specials = new HashSet<>(specials);
  }

  public Set<Category> getSpecials(){
    return new HashSet<>(specials);
  }

  @Override
  public int totalBonus(){
    int bonus = super.totalBonus();
    for(Map.Entry<Category, Double> entry : getCategories().entrySet()){
      for(Category i : specials){
        if(i == entry.getKey()){
          bonus += Math.abs(entry.getValue());
        }
      }
    }
    return bonus;
  }

  public String toString(){
    String endResult = "";
    endResult += "- " + getName() + "(" + totalBonus() + ") -> {";
    Map<Category, Double> myMap = new HashMap<>();
    myMap.putAll(getCategories());
    boolean first = true;
    for(Map.Entry<Category, Double> entry : myMap.entrySet()){
      for(Category i : specials){
        if(i == entry.getKey()){
          if(first){
            endResult += "*" + entry.getKey() + "*=" + entry.getValue();
            first = false;
          } else {
            endResult += ", *" + entry.getKey() + "*=" + entry.getValue();
          }
        } else{
          if(first){
            endResult += entry.getKey() + "=" + entry.getValue();
            first = false;
          } else {
            endResult += ", " + entry.getKey() + "=" + entry.getValue();
          }
        }
      }
    }
    endResult += "}";
    return endResult;
  }
}
