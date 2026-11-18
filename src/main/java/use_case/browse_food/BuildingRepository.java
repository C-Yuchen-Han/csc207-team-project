package use_case.browse_food;

import entity.Building;
import java.util.List;

/**
 * Provides access to building information.
 */
public interface BuildingRepository {

  /**
   * Retrieves all available buildings.
   *
   * @return a list of all buildings
   */
  List<Building> getAllBuildings();
}