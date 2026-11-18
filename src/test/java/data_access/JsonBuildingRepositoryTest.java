package data_access;

import entity.Building;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBuildingRepositoryTest {
  JsonBuildingRepository repository = new JsonBuildingRepository();
  @Test
  public void getBuildingsTest() {
    List<Building> buildings = repository.getAllBuildings();

    // assert the list has correct length
    assertEquals(123, buildings.size());

    // assert the list is correctly populated
    Building firstBuilding = buildings.get(0);
    assertEquals("utsg:facility:506", firstBuilding.getBuildingId());
    assertEquals("Annesley Hall", firstBuilding.getBuildingName());
    assertEquals(43.6678211, firstBuilding.getLatitude());
    assertEquals(-79.3929553, firstBuilding.getLongitude());
  }
}
