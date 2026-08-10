import java.util.HashMap;
import java.util.Map;

public class FoodRatings {

    Map<String, Integer> cuisineToRating;
    Map<String, String> cuisineToFood;
    int[] ratings;
    String[] foods;
    String[] cuisines;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.foods = foods;
        this.cuisines = cuisines;
        this.ratings = ratings;
        cuisineToRating = new HashMap<>();
        cuisineToFood = new HashMap<>();
        for (int i = 0; i < ratings.length; i++) {
            changeRating(foods[i], ratings[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        int index = -1;
        for (int i = 0; i < foods.length; i++) {
            if (foods[i].equals(food)) {
                index = i;
                break;
            }
        }
        ratings[index] = newRating;
        String cuisine = cuisines[index];
        if (food.equals(cuisineToFood.get(cuisine))) {
            int maxIndex = index;
            for (int i = 0; i < foods.length; i++) {
                if (cuisines[i].equals(cuisine)) {
                    if (ratings[maxIndex] < ratings[i]
                    || (ratings[maxIndex] == ratings[i] && foods[i].compareTo(foods[index]) < 0)) {
                        maxIndex = i;
                    }
                }
            }
            cuisineToFood.put(cuisine, foods[maxIndex]);
            cuisineToRating.put(cuisine, ratings[maxIndex]);
            return;
        }
        if (cuisineToRating.getOrDefault(cuisine, 0) < newRating ||
                (cuisineToRating.getOrDefault(cuisine, 0) == newRating && food.compareTo(cuisineToFood.get(cuisine)) < 0)) {
            cuisineToRating.put(cuisine, newRating);
            cuisineToFood.put(cuisine, food);
        }
    }

    public String highestRated(String cuisine) {
        return cuisineToFood.get(cuisine);
    }

    public static void main(String[] args) {
        String[] foods = {"a","b","c"};
        String[] cuisines = {"x","x","x",};
        int[] ratings = {11,2,15};
        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);

        foodRatings.changeRating("a", 12);
        System.out.println(foodRatings.highestRated("x"));
        foodRatings.changeRating("c", 8);
        //foodRatings.changeRating("b", 5);
        System.out.println(foodRatings.highestRated("x"));
    }
}
