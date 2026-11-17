package use_case.browse_food;

/**
 * Calculates the walking distance between two coordinates.
 */
public interface DistanceCalculator {

  /**
   * Returns walking distance in meters.
   */
  double calculateDistance(
      double startLatitude,
      double startLongitude,
      double endLatitude,
      double endLongitude
  );
}