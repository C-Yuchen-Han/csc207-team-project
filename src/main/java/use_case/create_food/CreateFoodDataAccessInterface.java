package use_case.create_food;

import entity.FoodEvent;

public interface CreateFoodDataAccessInterface {

    void save(FoodEvent event);

}
