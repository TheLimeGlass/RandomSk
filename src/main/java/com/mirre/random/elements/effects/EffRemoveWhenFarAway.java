package com.mirre.random.elements.effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomEffect;

@Pattern(patterns = {"(despawn|remove) %entities% when far away", "(do not|don't) (despawn|remove) %entities% when far away"})
public class EffRemoveWhenFarAway extends RandomEffect {

	@Override
	public void perform(Event event) {
		for (Entity entity : expressions.getAll(event, Entity.class, 0)) {
			if (entity instanceof LivingEntity)
				((LivingEntity)entity).setRemoveWhenFarAway(patternMark == 0);
		}
	}

}
