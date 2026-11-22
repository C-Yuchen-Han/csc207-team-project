package interface_adapter.browse_food;

import entity.FoodEvent;
import entity.FoodType;
import entity.Subject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import use_case.browse_food.BrowseFoodOutputBoundary;
import use_case.browse_food.BrowseFoodOutputData;

/**
 * Presenter for the browse food use case.
 * Converts food events into display data and updates the view model.
 */
public class BrowseFoodPresenter implements BrowseFoodOutputBoundary {

  private final BrowseFoodViewModel viewModel;

  /**
   * Constructs a browse food presenter.
   *
   * @param viewModel the view model to update
   */
  public BrowseFoodPresenter(BrowseFoodViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @Override
  public void prepareSuccessView(BrowseFoodOutputData outputData) {
    List<FoodEventDisplayData> displayData = new ArrayList<>();

    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");

    for (FoodEvent event : outputData.getFoodEvents()) {
      FoodEventDisplayData eventDisplayed = new FoodEventDisplayData(
          event.getEventId(),
          event.getTitle(),
          event.getDescription(),
          event.getLocation().getBuilding().getBuildingName(),
          event.getLocation().getRoomNumber(),
          event.getFoodTypes().stream()
              .map(FoodType::toString)
              .sorted()
              .collect(Collectors.joining(", ")),
          event.getRelatedSubjects().stream()
              .map(Subject::toString)
              .sorted()
              .collect(Collectors.joining(", ")),
          event.isRegistrationRequired()
              ? "Registration required"
              : "No registration required",
          event.getCreatedAt().format(formatter),
          event.getExpiresAt().format(formatter)
      );

      displayData.add(eventDisplayed);
    }

    viewModel.setEvents(displayData);
  }
}