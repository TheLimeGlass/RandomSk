package com.mirre.random.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.mirre.random.RandomSk;
import com.mirre.random.packets.wrappers.WrapperPlayServerAnimation;
import com.mirre.random.packets.wrappers.WrapperPlayServerBed;

public class SleepAnimationManager {

	private final static Map<Player, Location> sleeping = new HashMap<>();

	public static void playSleepAnimation(Player player) {
		playSleepAnimation(player, player.getLocation());
	}

	public static void playSleepAnimation(Player player, Location location) {
		WrapperPlayServerBed packet = new WrapperPlayServerBed();
		packet.setEntityID(player.getEntityId());
		packet.setLocation(new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
		ProtocolManager protocolManager = RandomSk.getInstance().getProtocolManager();
		for (Player receiver : protocolManager.getEntityTrackers(player))
			packet.sendPacket(receiver);
		sleeping.put(player, location);
	}

	public static void stopSleepAnimation(Player player) {
		if (!sleeping.containsKey(player))
			return;
		WrapperPlayServerAnimation packet = new WrapperPlayServerAnimation();
		packet.setEntityID(player.getEntityId());
		packet.setAnimation(2);
		ProtocolManager protocolManager = RandomSk.getInstance().getProtocolManager();
		for (Player receiver : protocolManager.getEntityTrackers(player))
			packet.sendPacket(receiver);
		sleeping.remove(player);
	}

	public static boolean hasSleepAnimation(Player player) {
		return sleeping.containsKey(player);
	}

	public static Optional<Entry<Player, Location>> getEntry(Player player) {
		return sleeping.entrySet().stream()
				.filter(entry -> entry.getKey().equals(player))
				.findFirst();
	}

	public static Optional<Location> getLocation(Player player) {
		return sleeping.entrySet().stream()
				.filter(entry -> entry.getKey().equals(player))
				.map(entry -> entry.getValue())
				.findFirst();
	}

}
