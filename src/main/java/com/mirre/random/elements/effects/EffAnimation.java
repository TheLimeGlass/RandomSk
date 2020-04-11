package com.mirre.random.elements.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.managers.SleepAnimationManager;
import com.mirre.random.utils.enums.Animation;

@Pattern(patterns = { "(create|show) %animation% [animation] (on|to) %players% for %players%" })
public class EffAnimation extends RandomEffect {

	@Override
	public void perform(Event event) {
		Animation animation = expressions.getSingle(event, Animation.class, 0);
		Player[] targets = expressions.getAll(event, Player.class, 0);
		//Player[] watchers = expressions.getAll(event, Player.class, 1);
		//TODO
		switch (animation) {
			case CRITICAL:
				break;
			case CRITICAL2:
				break;
			case CROUCH:
				break;
			case DAMAGE:
				break;
			case EAT:
				break;
			case PUNCH:
				break;
			case UNCROUCH:
				break;
			case WAKEUP:
				for (Player player : targets)
					SleepAnimationManager.stopSleepAnimation(player);
				break;
			default:
				break;
		}
	}

}
