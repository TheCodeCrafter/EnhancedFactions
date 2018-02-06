package net.fathomtech.plugins.EnhancedFactions;

public class Main extends JavaPlugin {
  
  FocusFile focus;
  HashMap<Player, ArrayList<Player>> cache = new HashMap<Player, ArrayList<Player>>();
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    focus = new FocusFile(this);
    
    ArrayList<String> focusedPlayers = (ArrayList<String>) focus.data.getKeys(false);
    for(String playername : focusedPlayers) {
      focusPlayer
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
    this.focusPlayer();
  }
  
  public void addFocusedPlayer(FPlayer target, Faction faction, FPlayer sender, String message) {
    Set<FPlayer> members = faction.getFPlayersWhereOnline(true);
    faction.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + target.getName() + " has been focused!");
    faction.addAnnouncement(sender, message);
    focus.data.setList(target.getName() + ".recipients", members);
    this.focusPlayer();
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
