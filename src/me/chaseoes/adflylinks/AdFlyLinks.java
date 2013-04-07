package me.chaseoes.adflylinks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AdFlyLinks extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		reloadConfig();
		saveConfig();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		String[] words = event.getMessage().split(" ");
		String message = event.getMessage();
		for (String word : words) {
			if (word.startsWith("http://") || word.startsWith("https://") || word.startsWith("www.") || word.endsWith(".com") || word.endsWith(".net") || word.endsWith(".org")){
				String shortenedURL = new AdFlyShortener(getConfig().getString("API_URL") + "&url=" + word).shorten();
				if (shortenedURL != null) {
					message = message.replace(word, shortenedURL);
				}
			}
			event.setMessage(message);
		}
	}

}
