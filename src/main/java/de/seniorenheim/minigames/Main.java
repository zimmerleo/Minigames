package de.seniorenheim.minigames;

import de.seniorenheim.minigames.Challenges.Commands.*;
import de.seniorenheim.minigames.Challenges.Listeners.ChallengeInvListener;
import de.seniorenheim.minigames.Challenges.Listeners.InverseDamageListener;
import de.seniorenheim.minigames.Challenges.Listeners.NoCraftingTableListener;
import de.seniorenheim.minigames.Challenges.Listeners.SharedDamageListener;
import de.seniorenheim.minigames.Utils.Managers.ChallengeManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Plugin plugin;
    private Main instance;
    private final ChallengeManager challengeManager = new ChallengeManager();

    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        challengeManager.setup();

        loadEvents(Bukkit.getPluginManager());
        loadCommands();
    }

    private void loadCommands() {
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("join").setExecutor(new JoinCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("pause").setExecutor(new PauseCommand());
        getCommand("resume").setExecutor(new ResumeCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("stop").setExecutor(new StopCommand());
    }

    private void loadEvents(PluginManager pm) {
        pm.registerEvents(new ChallengeInvListener(), this);
        pm.registerEvents(new InverseDamageListener(), this);
        pm.registerEvents(new SharedDamageListener(), this);
        pm.registerEvents(new NoCraftingTableListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Main getInstance() {
        return instance;
    }

    public ChallengeManager getChallengeManager() {
        return challengeManager;
    }
}
