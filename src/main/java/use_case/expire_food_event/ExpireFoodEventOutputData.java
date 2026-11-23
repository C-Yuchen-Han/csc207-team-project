package use_case.expire_food_event;

/**
 * Stores the output data for the expire food event use case.
 */
public class ExpireFoodEventOutputData {

    private final int expiredCount;

    public ExpireFoodEventOutputData(int expiredCount) {
        this.expiredCount = expiredCount;
    }

    public int getExpiredCount() {
        return expiredCount;
    }
}
