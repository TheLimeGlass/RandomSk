package com.mirre.random.elements.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.managers.SleepAnimationManager;

@Pattern(patterns = {"wake up %players%"})
public class EffWakeUp extends RandomEffect {

	@Override
	public void perform(Event event) {
		Player[] players = expressions.getAll(event, Player.class, 0);
		for (Player player : players) {
			if (!SleepAnimationManager.hasSleepAnimation(player)) {
				player.wakeup(false);
				continue;
			}
			PlayerBedLeaveEvent bedEvent = new PlayerBedLeaveEvent(player, SleepAnimationManager.getLocation(player).orElse(player.getLocation()).getBlock(), false);
			Bukkit.getPluginManager().callEvent(bedEvent);
			SleepAnimationManager.stopSleepAnimation(player);
		}
	}

}
