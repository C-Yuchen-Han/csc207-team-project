package data_access;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenRouteServiceDistanceCalculatorTest {

  @Test
  public void testWalkingDistance() {
    OpenRouteServiceDistanceCalculator calculator =
        new OpenRouteServiceDistanceCalculator();

    double distance = calculator.calculateDistance(
        43.6596, -79.3977,
        43.6644, -79.3995
    );

    assertTrue(distance >= 500 && distance <= 900);  }
}