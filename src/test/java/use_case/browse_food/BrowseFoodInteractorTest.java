package use_case.browse_food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import entity.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class BrowseFoodInteractorTest {

  private static class FakeFoodEventRepository implements FoodEventRepository {
    private final List<FoodEvent> events;

    public FakeFoodEventRepository(List<FoodEvent> events) {
      this.events = events;
    }

    @Override
    public List<FoodEvent> getAllEvents() {
      return events;
    }
  }

  private static class FakeBrowseFoodPresenter implements BrowseFoodOutputBoundary {
    private BrowseFoodOutputData receivedData;

    @Override
    public void prepareSuccessView(BrowseFoodOutputData receivedData) {
      this.receivedData = receivedData;
    }
  }

  @Test
  void testBrowseNoEvents() {
    // Arrange
    FakeFoodEventRepository repository = new FakeFoodEventRepository(List.of());
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();

    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter);

    BrowseFoodInputData inputData = new BrowseFoodInputData(
        "", "", new HashSet<>(), new HashSet<>(), false, null, null, null
    );

    // Act
    interactor.execute(inputData);

    // Assert
    assertNotNull(presenter.receivedData);
    assertEquals(0, presenter.receivedData.getFoodEvents().size());
  }

  @Test
  void testBrowse1Event() {
    FoodEvent event1 = new FoodEvent(
        "001", "CSSU pizza social", "There's a social event at the CSSU w| boardgames and pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());
    FoodEventRepository repository = new FakeFoodEventRepository(List.of(event1));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();

    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter);
    BrowseFoodInputData inputData = new BrowseFoodInputData(
        "", "", new HashSet<>(), new HashSet<>(), false, null, null, null
    );
    interactor.execute(inputData);

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(event1), presenter.receivedData.getFoodEvents());
  }
}
