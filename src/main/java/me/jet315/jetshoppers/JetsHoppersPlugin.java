package me.jet315.jetshoppers;

import me.jet315.jetshoppers.commands.CommandHandler;
import me.jet315.jetshoppers.listeners.player.*;
import me.jet315.jetshoppers.manager.GUIManager;
import me.jet315.jetshoppers.manager.InventoryLinkManager;
import me.jet315.jetshoppers.manager.JetsHopperManager;
import me.jet315.jetshoppers.properties.GUI;
import me.jet315.jetshoppers.properties.Messages;
import me.jet315.jetshoppers.properties.PlayerData;
import me.jet315.jetshoppers.properties.Properties;
import me.jet315.jetshoppers.tasks.HopperTask;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class JetsHoppersPlugin extends JavaPlugin implements me.jet315.jetshoppers.api.JetsHoppersPlugin {


    // Singleton Instance
    private static me.jet315.jetshoppers.api.JetsHoppersPlugin instance;

    private Economy econ = null;

    public static me.jet315.jetshoppers.api.JetsHoppersPlugin getInstance() {
        return instance;
    }

    /**
     * Properties / Data files
     */
    private Properties properties;
    private Messages messages;
    private PlayerData playerData;
    private GUI guiData;

    /**
     * Managers
     */
    private JetsHopperManager hopperManager;
    private GUIManager guiManager;
    private InventoryLinkManager linkManager;

    /*
    TODO:
    STATS - make use of
    Filter inventory - incorrect setting carried forward
    something to do with block-breaking not working, hopper still registered
     */


    @Override
    public void onEnable() {
        instance = this;
        long startTime = System.currentTimeMillis();
        System.out.println(" ");
        System.out.println("[JetsHoppers] Initialising Plugin.. ");

        if (!setupEconomy() ) {
            System.out.println("Vault was not found. Unable to use Currency.");
        }

        this.hopperManager = new JetsHopperManager();
        this.guiManager = new GUIManager();
        this.linkManager = new InventoryLinkManager();


        this.properties = new Properties("config.yml",this);
        this.messages = new Messages("messages.yml",this);
        this.playerData = new PlayerData("players.yml",this);
        this.guiData = new GUI("gui.yml",this);

        //register command
        getCommand("jetshoppers").setExecutor(new CommandHandler());

        //events
        Bukkit.getPluginManager().registerEvents(new PlayerBlockBreak(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerBlockPlace(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerInventoryClick(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerInventoryClose(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerSneak(),this);

        //tasks
        new HopperTask(this);

        System.out.println("[JetsHoppers] Initialising Complete in " + String.valueOf(System.currentTimeMillis() - startTime) + "ms");
        System.out.println(" ");
    }

    @Override
    public void onDisable() {
        playerData.saveDataToDisk();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    @Override
    public JetsHopperManager getJetsHopperManager() {
        return this.hopperManager;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public PlayerData getPlayerData() {
        return this.playerData;
    }
    @Override
    public Messages getMessages() {
        return messages;
    }

    @Override
    public GUI getGUI() {
        return guiData;
    }
    @Override
    public Plugin getPlugin() {
        return this;
    }

    @Override
    public GUIManager getGUIManager() {
        return guiManager;
    }
    @Override
    public Economy getEcon() {
        return econ;
    }

    @Override
    public InventoryLinkManager getInventoryLinkManager() {
        return linkManager;
    }

    @Override
    public void reloadConfiguraiton() {
        for(Player p : this.guiManager.getActiveGUIItems().keySet()){
            p.closeInventory();
            p.sendMessage(properties.getPluginPrefix() + ChatColor.RED + "JetsHoppers has been reloaded.");
        }
        this.properties.reload();
        this.messages.reload();
        this.guiData.reload();
    }
}
