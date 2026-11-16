package use_case.browse_food;

/**
 * Output boundary for the browse food use case.
 */
public interface BrowseFoodOutputBoundary {

  /**
   * Prepares the view for successfully browsing food events.
   *
   * @param outputData the resulting food events
   */
  void prepareSuccessView(BrowseFoodOutputData outputData);
}