package com.mirre.random.elements.effects;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.packets.wrappers.WrapperPlayServerRespawn;

@Pattern(patterns = {"change (sky|environment) of %players% to %environment%"})
public class EffEnvironment extends RandomEffect {

	@SuppressWarnings("deprecation")
	@Override
	public void perform(final Event event) {
		Player[] players = expressions.getAll(event, Player.class, 0);
		Environment environment = expressions.getSingle(event, Environment.class, 0);
		for (Player player : players) {
			WrapperPlayServerRespawn packet = new WrapperPlayServerRespawn();
			World world = player.getWorld();
			packet.setLevelType(world.getWorldType());
			packet.setDifficulty(EnumWrappers.Difficulty.valueOf(world.getDifficulty().name()));
			packet.setGamemode(NativeGameMode.NOT_SET);
			packet.setDimension(environment.getId());
			packet.sendPacket(player);
		}
	}

}
