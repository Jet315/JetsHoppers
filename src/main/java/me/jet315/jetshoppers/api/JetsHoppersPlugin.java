package me.jet315.jetshoppers.api;

import me.jet315.jetshoppers.manager.GUIManager;
import me.jet315.jetshoppers.manager.InventoryLinkManager;
import me.jet315.jetshoppers.manager.JetsHopperManager;
import me.jet315.jetshoppers.properties.GUI;
import me.jet315.jetshoppers.properties.Messages;
import me.jet315.jetshoppers.properties.PlayerData;
import me.jet315.jetshoppers.properties.Properties;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;

public interface JetsHoppersPlugin {


    /**
     * TODO add various managers
     */


    JetsHopperManager getJetsHopperManager();

    Plugin getPlugin();

    Properties getProperties();

    PlayerData getPlayerData();

    Messages getMessages();

    GUI getGUI();

    GUIManager getGUIManager();

    Economy getEcon();

    InventoryLinkManager getInventoryLinkManager();

    void reloadConfiguraiton();

}
