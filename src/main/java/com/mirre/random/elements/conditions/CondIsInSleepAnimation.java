package com.mirre.random.elements.conditions;

import org.bukkit.entity.Player;

import com.mirre.random.lang.RandomPropertyCondition;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.managers.SleepAnimationManager;

@Pattern(patterns = { "%player% (is|are) in sleep[ing] animation", "{!}%player% (is|are)(n't| not) in sleep[ing] animation" })
public class CondIsInSleepAnimation extends RandomPropertyCondition<Player> {

	@Override
	public boolean inspect(Player player) {
		return SleepAnimationManager.hasSleepAnimation(player);
	}

}
