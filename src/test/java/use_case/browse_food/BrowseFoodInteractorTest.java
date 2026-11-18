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

  private static class FakeDistanceCalculator implements DistanceCalculator {

    @Override
    public double calculateDistance(
        double startLatitude,
        double startLongitude,
        double endLatitude,
        double endLongitude) {
      return 500.0;
    }
  }

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

    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter, new FakeDistanceCalculator());

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

    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter, new FakeDistanceCalculator());
    BrowseFoodInputData inputData = new BrowseFoodInputData(
        "", "", new HashSet<>(), new HashSet<>(), false, null, null, null
    );
    interactor.execute(inputData);

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(event1), presenter.receivedData.getFoodEvents());
  }

  @Test
  void keywordFilter() {
    FoodEvent pizzaEvent = new FoodEvent(
        "001", "CSSU social", "There's a social event at the CSSU w| boardgames and pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());
    FoodEvent snacksEvent = new FoodEvent(
        "002", "CSSU social2", "There's a social event at the CSSU w| snacks!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.COMPUTER_SCIENCE, Subject.ENGINEERING), false,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository = new FakeFoodEventRepository(List.of(pizzaEvent, snacksEvent));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter, new FakeDistanceCalculator());
    interactor.execute(
        new BrowseFoodInputData( "PIZZA", "", new HashSet<>(), new HashSet<>(), false, null, null, null
    ));

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(pizzaEvent), presenter.receivedData.getFoodEvents());

  }

  @Test
  void buildingFilter() {
    FoodEvent baEvent = new FoodEvent(
        "001", "CSSU social", "There's a social event at the CSSU w| boardgames and pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());
    FoodEvent ssEvent = new FoodEvent(
        "002", "CSSU social2", "There's a social event at the ASSU w| snacks!",
        new Location(new Building("ss", "Sydney Smith", 42, -80), "1000"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.COMPUTER_SCIENCE, Subject.ENGINEERING), false,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository = new FakeFoodEventRepository(List.of(baEvent, ssEvent));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(repository, presenter, new FakeDistanceCalculator());
    interactor.execute(
        new BrowseFoodInputData( "", "Bahen Centre", new HashSet<>(), new HashSet<>(), false, null, null, null
        ));

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(baEvent), presenter.receivedData.getFoodEvents());

  }

  @Test
  void foodTypeFilter() {
    FoodEvent mealEvent = new FoodEvent(
        "001", "CSSU social", "Free pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());

    FoodEvent snackEvent = new FoodEvent(
        "002", "Study Break", "Free snacks!",
        new Location(new Building("ss", "Sydney Smith", 43.6620, -79.3980), "1000"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.ENGINEERING), false,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository =
        new FakeFoodEventRepository(List.of(mealEvent, snackEvent));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(
        repository, presenter, new FakeDistanceCalculator());

    // Filter for SNACK events only.
    interactor.execute(
        new BrowseFoodInputData(
            "", "", Set.of(FoodType.SNACK), Set.of(),
            false, null, null, null
        )
    );

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(snackEvent), presenter.receivedData.getFoodEvents());
  }

  @Test
  void relatedSubjectsFilter() {
    FoodEvent csEvent = new FoodEvent(
        "001", "CS Social", "Free pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());

    FoodEvent engineeringEvent = new FoodEvent(
        "002", "Engineering Social", "Free snacks!",
        new Location(new Building("ss", "Sydney Smith", 43.6620, -79.3980), "1000"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.ENGINEERING), false,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository =
        new FakeFoodEventRepository(List.of(csEvent, engineeringEvent));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(
        repository, presenter, new FakeDistanceCalculator());

    // Select both subjects; either one should match.
    interactor.execute(
        new BrowseFoodInputData(
            "", "", Set.of(),
            Set.of(Subject.COMPUTER_SCIENCE, Subject.ENGINEERING),
            false, null, null, null
        )
    );

    assertNotNull(presenter.receivedData);
    assertEquals(
        List.of(csEvent, engineeringEvent),
        presenter.receivedData.getFoodEvents()
    );
  }

  @Test
  void registrationFilter() {
    FoodEvent openEvent = new FoodEvent(
        "001", "Free Food", "Everyone welcome!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());

    FoodEvent registeredEvent = new FoodEvent(
        "002", "Registered Event", "Registration required!",
        new Location(new Building("ss", "Sydney Smith", 43.6620, -79.3980), "1000"),
        Set.of(FoodType.SNACK),
        Set.of(Subject.ENGINEERING), true,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository =
        new FakeFoodEventRepository(List.of(openEvent, registeredEvent));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(
        repository, presenter, new FakeDistanceCalculator());

    // Only show events that do not require registration.
    interactor.execute(
        new BrowseFoodInputData(
            "", "", Set.of(), Set.of(),
            true, null, null, null
        )
    );

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(openEvent), presenter.receivedData.getFoodEvents());
  }

  @Test
  void distanceFilter() {
    FoodEvent event = new FoodEvent(
        "001", "CSSU Social", "Free pizza!",
        new Location(new Building("ba", "Bahen Centre", 43.6596, -79.3977), "2250"),
        Set.of(FoodType.MEAL),
        Set.of(Subject.COMPUTER_SCIENCE), false,
        LocalDateTime.now(), LocalDateTime.now());

    FakeFoodEventRepository repository =
        new FakeFoodEventRepository(List.of(event));
    FakeBrowseFoodPresenter presenter = new FakeBrowseFoodPresenter();

    // FakeDistanceCalculator always returns 500 meters.
    BrowseFoodInteractor interactor = new BrowseFoodInteractor(
        repository, presenter, new FakeDistanceCalculator());

    // Case 1: 500m <= 1000m, so the event should be included.
    interactor.execute(
        new BrowseFoodInputData(
            "", "", Set.of(), Set.of(),
            false, 1000.0, 43.6600, -79.4000
        )
    );

    assertNotNull(presenter.receivedData);
    assertEquals(List.of(event), presenter.receivedData.getFoodEvents());

    // Case 2: 500m > 300m, so the event should be excluded.
    interactor.execute(
        new BrowseFoodInputData(
            "", "", Set.of(), Set.of(),
            false, 300.0, 43.6600, -79.4000
        )
    );

    assertEquals(List.of(), presenter.receivedData.getFoodEvents());
  }
}
