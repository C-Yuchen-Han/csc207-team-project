package use_case.create_food;

import entity.FoodEvent;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Interactor responsible for creating food events.
 */
public class CreateFoodInteractor implements CreateFoodInputBoundary {

    private final CreateFoodDataAccessInterface dataAccess;
    private final CreateFoodOutputBoundary presenter;

    /**
     * Construct a CreateFoodInteractor object.
     *
     * @param dataAccess the interface used to save food events
     * @param presenter the output boundary for handling success or failure
     */
    public CreateFoodInteractor(
            CreateFoodDataAccessInterface dataAccess,
            CreateFoodOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    /**
     * Validates the input information, creates and saves a new food event.
     *
     * @param inputData the information needed to create a food event
     */
    @Override
    public void execute(CreateFoodInputData inputData) {

        // Check title
        if (inputData.getTitle() == null
                || inputData.getTitle().isBlank()) {
            presenter.prepareFailView("Title cannot be empty");
            return;
        }

        // Check location
        if (inputData.getLocation() == null
                || inputData.getLocation().getBuilding() == null) {
            presenter.prepareFailView("Location is required");
            return;
        }

        // Check expiration time
        LocalDateTime createdAt = LocalDateTime.now();

        if (inputData.getExpiresAt() == null
                || !inputData.getExpiresAt().isAfter(createdAt)) {
            presenter.prepareFailView(
                    "Expiration time must be in the future");
            return;
        }

        // Generate an ID
        String eventId = UUID.randomUUID().toString();

        // Create FoodEvent
        FoodEvent event = new FoodEvent(
                eventId,
                inputData.getTitle(),
                inputData.getDescription(),
                inputData.getLocation(),
                inputData.getFoodTypes(),
                inputData.getRelatedSubjects(),
                inputData.isRegistrationRequired(),
                createdAt,
                inputData.getExpiresAt()
        );

        // Save the event
        dataAccess.save(event);

        // Return success result
        CreateFoodOutputData outputData =
                new CreateFoodOutputData(
                        eventId,
                        inputData.getTitle()
                );

        presenter.prepareSuccessView(outputData);
    }
}

