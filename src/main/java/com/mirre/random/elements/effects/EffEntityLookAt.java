package com.mirre.random.elements.effects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.packets.wrappers.WrapperPlayServerLookAt;
import com.mirre.random.packets.wrappers.WrapperPlayServerLookAt.Anchor;

@Pattern(patterns = {"make %players% look at %location%"})
public class EffEntityLookAt extends RandomEffect {

	@Override
	public void perform(Event event) {
		Player[] players = expressions.getAll(event, Player.class, 0);
		Location location = expressions.getSingle(event, Location.class, 0);
		for (Player player : players) {
			WrapperPlayServerLookAt packet = new WrapperPlayServerLookAt();
			packet.setIsEntity(false);
			packet.setAnchor(Anchor.EYES);
			packet.setTargetX(location.getX());
			packet.setTargetY(location.getY());
			packet.setTargetZ(location.getZ());
			packet.sendPacket(player);
		}
	}

}
