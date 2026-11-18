package use_case.create_food;

public interface CreateFoodOutputBoundary {

    void prepareSuccessView(CreateFoodOutputData outputData);

    void prepareFailView(String errorMessage);

}

