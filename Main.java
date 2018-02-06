package net.fathomtech.plugins.EnhancedFactions;

public class Main extends JavaPlugin {
  FocusFile focus;
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    focus = new FocusFile(this);
  }
  
  @Override
  public void onDisable() {
    saveConfig();
    focus.save();
  }
}
