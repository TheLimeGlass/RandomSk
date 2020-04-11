package com.mirre.random.packets.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.mirre.random.RandomSk;
import com.mirre.random.packets.wrappers.WrapperPlayServerNamedSoundEffect;
import com.mirre.random.utils.events.NamedSoundEvent;

public class SimplePacketListener {

	public SimplePacketListener(RandomSk instance) {
		instance.getProtocolManager().addPacketListener(new PacketAdapter(instance, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				WrapperPlayServerNamedSoundEffect packet = new WrapperPlayServerNamedSoundEffect(event.getPacket());
				int x = packet.getEffectPositionX() / 8;
				int y = packet.getEffectPositionY() / 8;
				int z = packet.getEffectPositionZ() / 8;
				Player player = event.getPlayer();
				Location location = new Location(player.getWorld(), x, y, z);
				SoundCategory category = SoundCategory.MASTER;
				try {
					category = SoundCategory.valueOf(packet.getSoundCategory().name());
				} catch (Exception e) {}
				NamedSoundEvent soundEvent = new NamedSoundEvent(player, packet.getSoundEffect(), category, location, packet.getVolume(), packet.getPitch());
				Bukkit.getPluginManager().callEvent(soundEvent);
				if (soundEvent.isCancelled()) {
					event.setCancelled(true);
					return;
				}
				packet.setPitch(soundEvent.getPitch());
				packet.setVolume(soundEvent.getVolume());
				packet.setSoundCategory(com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory.valueOf(soundEvent.getSoundCategory().name()));
			}
		});
	}

}
