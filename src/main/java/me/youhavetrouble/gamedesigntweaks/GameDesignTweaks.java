package me.youhavetrouble.gamedesigntweaks;

import org.bukkit.plugin.java.JavaPlugin;

public final class GameDesignTweaks extends JavaPlugin {

    public static GameDesignTweaks instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new SecondWindListener(), this);
    }
}
