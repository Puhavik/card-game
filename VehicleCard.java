package vehicle;
import java.util.*;

public class VehicleCard implements Comparable <VehicleCard > {
  public enum Category {
    ECONOMY_MPG ("Miles/Galon"),
    CYLINDERS_CNT ("Zylinder"),
    DISPLACEMENT_CCM ("Hubraum[cc]"),
    POWER_HP ("Leistung[hp]"),
    WEIGHT_LBS ("Gewicht[lbs]"),
    ACCELERATION ("Beschleunigung"),
    YEAR ("Baujahr[19xx]");

    private final String categoryName;
    private Category(final String categoryName){
      if(categoryName == null || categoryName.length() == 0){
        throw new IllegalArgumentException ("CatName Error");
      }
      this.categoryName = categoryName;
    }

    public boolean isInverted() {
      if(categoryName.equals("Gewicht[lbs]") || categoryName.equals("Beschleunigung")){
        return true;
      }
      return false;
    }

    public int bonus(final Double value){
      if(this.isInverted()){
        return value.intValue();
      }
      return value.intValue();
    }

    @Override
    public String toString(){
      return categoryName;
    }
  }

  private String name;
  private Map<Category, Double> categories;

  public VehicleCard(final String name, final Map<Category, Double> categories){
    if(name.length() == 0) {
      throw new IllegalArgumentException ("No Name");
    }
    if(categories == null || categories.size() < 7){
      throw new IllegalArgumentException ("Categories Error");
    }
    for(Map.Entry<Category, Double> entry : categories.entrySet()){
      if(entry.getValue() == null || entry.getValue() < 0){
        throw new IllegalArgumentException ("Values Error");
      }
    }
    this.name = name;
    this.categories = new HashMap<>(categories);
  }

  public String getName() {
    return name;
  }

  public Map<Category, Double> getCategories (){
    return new HashMap<>(categories);
  }

  public static Map<Category, Double> newMap(double economy, double cylinders, double displacement, double power, double weight, double acceleration, double year){
    Map<Category, Double> myMap = new HashMap<>();
    myMap.put(Category.ECONOMY_MPG, economy);
    myMap.put(Category.CYLINDERS_CNT, cylinders);
    myMap.put(Category.DISPLACEMENT_CCM, displacement);
    myMap.put(Category.POWER_HP, power);
    myMap.put(Category.WEIGHT_LBS, weight);
    myMap.put(Category.ACCELERATION, acceleration);
    myMap.put(Category.YEAR, year);
    return myMap;
  }

  @Override
  public int compareTo(final VehicleCard other){
    return ((Integer)this.totalBonus()).compareTo(((Integer)other.totalBonus()));
  }

  public int totalBonus(){
    int bonusInt = 0;
    for(Map.Entry<Category, Double> entry : categories.entrySet()){
      if(entry.getKey().isInverted()){
        bonusInt -= entry.getKey().bonus(entry.getValue());
      } else {
        bonusInt += entry.getKey().bonus(entry.getValue());
      }
    }
    return bonusInt;
  }

  @Override
  public int hashCode(){
    return name.hashCode() + Integer.valueOf(totalBonus()).hashCode();
  }

  @Override
  public boolean equals(Object obj){
    if(obj instanceof VehicleCard  && ((VehicleCard)obj).name == this.name && ((VehicleCard)obj).totalBonus() == this.totalBonus()) {
      return true;
    }
    return false;
  }

  @Override
  public String toString(){
    String endResult = "";
    endResult += "- " + name + "(" + totalBonus() + ") -> {";
    boolean first = true;
    for(Map.Entry<Category, Double> entry : categories.entrySet()){
      if (first){
        endResult += entry.getKey() + "=" + entry.getValue();
        first = false;
      } else {
        endResult += ", " + entry.getKey() + "=" + entry.getValue();
      }
    }
    endResult += "}";
    return endResult;
  }

}
