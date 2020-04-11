package com.mirre.random.packets.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerAction;
import com.mirre.random.RandomSk;
import com.mirre.random.managers.SleepAnimationManager;
import com.mirre.random.packets.wrappers.WrapperPlayClientEntityAction;

public class SleepListener implements Listener {

	public SleepListener(RandomSk instance) {
		instance.getProtocolManager().addPacketListener(new PacketAdapter(instance, PacketType.Play.Client.ENTITY_ACTION) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				WrapperPlayClientEntityAction packet = new WrapperPlayClientEntityAction(event.getPacket());
				if (packet.getAction() != PlayerAction.STOP_SLEEPING)
					return;
				Entity entity = packet.getEntity(event);
				if (entity.getType() != EntityType.PLAYER)
					return;
				Player player = (Player) entity;
				SleepAnimationManager.stopSleepAnimation(player);
				PlayerBedLeaveEvent bedEvent = new PlayerBedLeaveEvent(player, SleepAnimationManager.getLocation(player).orElse(player.getLocation()).getBlock(), false);
				Bukkit.getPluginManager().callEvent(bedEvent);
			}
		});
	}

	@EventHandler
	public void onWakeUp(PlayerBedLeaveEvent event) {
		SleepAnimationManager.stopSleepAnimation(event.getPlayer());
	}

}
