package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Building;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import use_case.browse_food.BuildingRepository;

/**
 * Loads building information from a JSON resource file.
 */
public class JsonBuildingRepository implements BuildingRepository {

  @Override
  public List<Building> getAllBuildings() {
    // Get building information from the JSON file.
    InputStream inputStream = getClass().getResourceAsStream("/buildings.json");

    if (inputStream == null) {
      throw new IllegalStateException("Cannot find buildings.json");
    }

    InputStreamReader reader = new InputStreamReader(inputStream);
    JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

    // Iterate through the JSON array and create Building objects.
    List<Building> buildings = new ArrayList<>();

    for (JsonElement element : jsonArray) {
      JsonObject obj = element.getAsJsonObject();
      String id = obj.get("buildingId").getAsString();
      String name = obj.get("buildingName").getAsString();
      double latitude = obj.get("latitude").getAsDouble();
      double longitude = obj.get("longitude").getAsDouble();

      Building building = new Building(id, name, latitude, longitude);
      buildings.add(building);
    }

    return buildings;
  }
}