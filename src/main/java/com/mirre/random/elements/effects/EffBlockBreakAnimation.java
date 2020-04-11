package com.mirre.random.elements.effects;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.metadata.FixedMetadataValue;

import com.comphenix.protocol.wrappers.BlockPosition;
import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.packets.wrappers.WrapperPlayServerBlockBreakAnimation;

@Pattern(patterns = {"show block break [animation] stage %number% at %block% for %players%",
		"show block break [animation] stage %number% at %block%",
		"show stacking block break [animation] stage %number% at %block%",
		"show stacking block break [animation] stage %number% at %block% for %players%"})
public class EffBlockBreakAnimation extends RandomEffect {

	private Random r = new Random();

	@Override
	public void perform(final Event event) {
		Block block = expressions.getSingle(event, Block.class, 0);
		Number stage = expressions.getSingle(event, Number.class, 0);
		Player[] players;
		if (patternMark == 0 || patternMark == 3) {
			players = expressions.getAll(event, Player.class, 0);
		} else {
			players = block.getWorld().getNearbyEntities(block.getLocation(), 100, 100, 100).stream()
					.filter(entity -> entity.getType() == EntityType.PLAYER).map(entity -> (Player)entity)
					.toArray(Player[]::new);
		}
		int random = (patternMark <= 1 && block.hasMetadata("BlockBreakAnimation")) ? block.getMetadata("BlockBreakAnimation").iterator().next().asInt() : r.nextInt();
		if (patternMark <= 1 && !block.hasMetadata("BlockBreakAnimation")) {
			block.setMetadata("BlockBreakAnimation", new FixedMetadataValue(RandomSk.getInstance(), random));
		}
		WrapperPlayServerBlockBreakAnimation packet = new WrapperPlayServerBlockBreakAnimation();
		packet.setEntityID(random);
		packet.setDestroyStage(stage.intValue());
		packet.setLocation(new BlockPosition(block.getX(), block.getY(), block.getZ()));
		for (Player player : players)
			packet.sendPacket(player);
	}

}
