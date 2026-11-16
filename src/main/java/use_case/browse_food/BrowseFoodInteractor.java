package use_case.browse_food;

import entity.FoodEvent;
import java.util.List;

/**
 * Interactor for browsing and filtering food events.
 */
public class BrowseFoodInteractor implements BrowseFoodInputBoundary {

  private final FoodEventRepository repository;
  private final BrowseFoodOutputBoundary presenter;

  /**
   * Constructs a BrowseFoodInteractor.
   *
   * @param repository the data access interface for food events
   * @param presenter the output boundary for browse results
   */
  public BrowseFoodInteractor(
      FoodEventRepository repository,
      BrowseFoodOutputBoundary presenter) {
    this.repository = repository;
    this.presenter = presenter;
  }

  @Override
  public void execute(BrowseFoodInputData inputData) {
    List<FoodEvent> events = repository.getAllEvents();


    BrowseFoodOutputData outputData = new BrowseFoodOutputData(events);
    presenter.prepareSuccessView(outputData);
  }
}