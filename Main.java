package net.fathomtech.plugins.EnhancedFactions;

public class Main extends JavaPlugin {
  
  FocusFile focus;
  HashMap<String, ArrayList<String>> cache = new HashMap<String, ArrayList<String>>();
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    focus = new FocusFile(this);
    
    for(String key : focus.data.getSection("data").getKeys(false)) {
      ArrayList<String> recipients = focus.data.getList("data." + key);
      cache.put(key, recipients);
    }
  }
  
  @Override
  public void onDisable() {
    saveConfig();
    focus.save();
  }
  
  public void addFocusedPlayer(FPlayer target, Faction faction) {
    Set<FPlayer> members = faction.getFPlayersWhereOnline(true);
    faction.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + target.getName() + " has been focused!");
    focus.data.setList(target.getName() + ".recipients", members);
    this.focusPlayer(target, members);
  }
  
  public void addFocusedPlayer(FPlayer target, Faction faction, FPlayer sender, String message) {
    Set<FPlayer> members = faction.getFPlayersWhereOnline(true);
    faction.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + target.getName() + " has been focused!");
    faction.addAnnouncement(sender, message);
    focus.data.setList(target.getName() + ".recipients", members);
    this.focusPlayer(target, members);
  }
  
  private void focusPlayer(FPlayer target, Set<FPlayer> recipients) {
    for(FPlayer player : recipients) {
      changeNameTag(player.getPlayer(), target.getPlayer(), getConfig().getString("focusedPrefix"), getConfig().getString("focusedSuffix"), TeamAction.CREATE);
    }
  }
  
  private void unfocusPlayer(FPlayer target, Set<FPlayer> recipients) {
    for(FPlayer player : recipients) {
      changeNameTag(player.getPlayer(), target.getPlayer(), "", "", TeamAction.DESTROY);
    }
  }
}
