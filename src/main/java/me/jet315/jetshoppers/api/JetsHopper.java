package me.jet315.jetshoppers.api;

import me.jet315.jetshoppers.utils.FilterType;
import me.jet315.jetshoppers.utils.HopperConfiguration;
import me.jet315.jetshoppers.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a JetsHopper Ingame
 */
public interface JetsHopper {


    /**
     * The owner of the hopper
     *
     * @return owner
     */
     UUID getOwner();

    /**
     * The world that the Hopper is located in
     *
     * @return world
     */
    World getWorld();


    /**
     * The Location of the hopper
     *
     * @return location
     */

     Location getLocation();

    /**
     * Location of the linked block
     *
     * @return null if linked block does not exist
     */
    Location getLinkedBlock();

    /**
     * Sets the location of the linked block
     *
     * @param loc location with the block (Must be Hopper or Chest)
     */
    void setLinkedBlock(Location loc);


    /**
     * Items that the hopper will only pickup
     *
     * @return list A list of whitelisted items
     */
     ArrayList<XMaterial> whitelistedItems();


    /**
     * Adds an item to the whitelist
     *
     * @param mat the Material
     */
    void addWhitelistItem(XMaterial mat);

    /**
     * Items that the hopper will not pickup
     *
     * @return list A list of blacklisted items
     */
     ArrayList<XMaterial> blacklistedItems();

    /**
     * Adds an item to the blacklist
     *
     * @param mat the Material
     */
    void addBlacklistItem(XMaterial mat);

    /**
     * This method is called per second, if the hopper is loaded
     */
    void tick();

    /**
     * Performs the Hopper Pickup task (Picks up items nearby the hopper based on level / range!)
     */
    void performHopperPickupTask();

    /**
     * Returns the current Level of the Hopper, the level is the configuration value name for the hopper.
     *
     * @return level
     */
    String getLevel();

    /**
     * Sets the level you want, from the configuration section
     *
     */
    void setLevel(String level);



    /**
     * Will upgrade the level of the hopper
     *
     * @return upgraded
     */
    boolean upgradeLevel();

    /**
     * Gets the hopper Configuration, that contains information about the hopper..
     * Use the setLevel() method to change this
     *
     * @return hopperConfiguration
     */
    HopperConfiguration getHopperConfiguration();

    /**
     * Used to gather various stats of the hopper
     *
     * @return stats
     */
     HopperStats getHopperStats();


    /**
     * The amount, in seconds, since the last time the hopper pulled in items around
     *
     * @return lastCheck
     */
     int lastSuctionCheck();


    /**
     * Sets the amount of seconds since the last check above was
     *
     * @param seconds
     */
    void setLastSuctionCheck(int seconds);


    /**
     * Whether the Hopper can be used for teleportation
     *
     * @return true or false for whether hopper can be used for teleportation
     */
    boolean canTeleport();

    /**
     * Sets whether the hopper can be used for teleportation
     *
     * @Param canTeleport - whether the user can teleport or not
     */
    void setTeleport(boolean canTeleport);

    /**
     * Returns true if the player is online and chunk is loaded
     *
     * @return
     */
     boolean isOwnerOnline();


     boolean isLoaded();

     FilterType getFilterType();

     void setFilterType(FilterType type);







}
