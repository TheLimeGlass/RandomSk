package com.mirre.random.elements.effects;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.managers.SleepAnimationManager;

@Pattern(patterns = {"make %player% sleep at %location%"})
public class EffSleep extends RandomEffect {

	@Override
	public void perform(Event event) {
		Player player = expressions.getSingle(event, Player.class, 0);
		Block block = expressions.getSingle(event, Block.class, 0);
		if (block.getBlockData() instanceof Bed)
			return;
		SleepAnimationManager.playSleepAnimation(player, block.getLocation());
	}

}
