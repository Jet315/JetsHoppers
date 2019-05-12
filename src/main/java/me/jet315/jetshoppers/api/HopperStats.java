package me.jet315.jetshoppers.api;

public interface HopperStats {


    /**
     * The number of items transferred
     *
     * @return items
     */
    int getItemsTransferred();

    /**
     * Sets the amount of items transferred
     *
     * @param amount
     */
    void setItemsTransferred(int amount);

    /**
     * The timestamp (epoch) of when the
     * hopper was placed
     *
     * @return timestamp
     */
    long getPlacementDate();


    /**
     * Sets the timestamp (epoch) of when the hopper was placed
     *
     * @param timestamp
     */
    void setPlacementData(long timestamp);

}
