package com.mirre.random.elements.effects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.comphenix.protocol.ProtocolManager;
import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.packets.wrappers.WrapperPlayServerEntityLook;

@Pattern(patterns = {"change yaw of %entities% to %number%", "change pitch of %entities% to %number%"})
public class EffEntityYawPitch extends RandomEffect {

	@Override
	public void perform(Event event) {
		Entity[] entities = expressions.getAll(event, Entity.class, 0);
		Number value = expressions.getSingle(event, Number.class, 0);
		ProtocolManager protocolManager = RandomSk.getInstance().getProtocolManager();
		for (Entity entity : entities) {
			WrapperPlayServerEntityLook packet = new WrapperPlayServerEntityLook();
			packet.setPitch(patternMark == 0 ? entity.getLocation().getPitch() : value.floatValue());
			packet.setYaw(patternMark == 1 ? entity.getLocation().getYaw() : value.floatValue());
			packet.setEntityID(entity.getEntityId());
			packet.setOnGround(entity.isOnGround());
			for (Player receiver : protocolManager.getEntityTrackers(entity))
				packet.sendPacket(receiver);
		}
	}

}
