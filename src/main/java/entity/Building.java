package entity;

/**
 * A Building class representing the buildings of the UTSG campus.
 * Relevant building data extracted from the GapWise HQ repository,
 * and the original building can be viewed under src/main/resources/buildings.json,
 * while the code loading in the above data is in main/java/data_access/JsonBuildingRepository.java.
 */
public class Building {
  private final String buildingId;
  private final String buildingName;
  private final double latitude;
  private final double longitude;

  /**
   * Constructs a Building object.
   *
   * @param buildingId the building identifier
   * @param buildingName the building name
   * @param latitude the building latitude
   * @param longitude the building longitude
   */
  public Building(String buildingId,
                  String buildingName,
                  double latitude,
                  double longitude) {
    this.buildingId = buildingId;
    this.buildingName = buildingName;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  //    Getter methods for the four private instance variables.
  public String getBuildingId() {
    return buildingId;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

}
