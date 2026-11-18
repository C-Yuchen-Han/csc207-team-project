package entity;

/**
 * Represents a location within a building on the UTSG campus.
 */
public class Location {
  private final Building building;
  private final String roomNumber;

  /**
   * Constructs a Location object.
   *
   * @param building   the building containing the location
   * @param roomNumber the room number within the building
   */
  public Location(Building building, String roomNumber) {
    this.building = building;
    this.roomNumber = roomNumber;
  }

  /**
   * Returns the building associated with this location.
   *
   * @return the building
   */
  public Building getBuilding() {
    return building;
  }

  /**
   * Returns the room number of this location.
   *
   * @return the room number
   */
  public String getRoomNumber() {
    return roomNumber;
  }
}