package use_case.browse_food;

/**
 * Input boundary for the browse food use case.
 */
public interface BrowseFoodInputBoundary {

  /**
   * Executes the browse food use case.
   *
   * @param inputData the criteria for browsing food events
   */
  void execute(BrowseFoodInputData inputData);
}