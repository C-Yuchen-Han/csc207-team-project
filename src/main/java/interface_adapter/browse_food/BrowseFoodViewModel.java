package interface_adapter.browse_food;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * View model for displaying browse food search results.
 */
public class BrowseFoodViewModel {
  private List<FoodEventDisplayData> events = List.of();
  private final PropertyChangeSupport support =
      new PropertyChangeSupport(this);

  /**
   * Returns the current food event display data.
   *
   * @return the list of food events to display
   */
  public List<FoodEventDisplayData> getEvents() {
    return events;
  }

  /**
   * Updates the food events and notifies registered listeners.
   *
   * @param events the new food event display data
   */
  public void setEvents(List<FoodEventDisplayData> events) {
    List<FoodEventDisplayData> oldEvents = this.events;
    this.events = List.copyOf(events);
    support.firePropertyChange("events", oldEvents, this.events);
  }

  /**
   * Registers a listener for changes to the view model.
   *
   * @param listener the listener to register
   */
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}