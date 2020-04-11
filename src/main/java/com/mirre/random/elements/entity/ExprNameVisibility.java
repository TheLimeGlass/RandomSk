package com.mirre.random.elements.entity;

import org.bukkit.event.Event;
import org.bukkit.entity.LivingEntity;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;
import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

import ch.njol.skript.classes.Changer.ChangeMode;

@Pattern(patterns = { "{S}[the] name visibility of %entity%", "{S}%entity%'s name visibility" })
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprNameVisibility extends RandomPropertyExpression<Entity, Boolean> implements Changer {

	@Override
	public Boolean[] get(Entity from) {
		if (from instanceof LivingEntity)
			return new Boolean[] { ((LivingEntity)from).isCustomNameVisible()};
		return null;
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Boolean b = (Boolean)delta[0];
		Entity entity = expressions.getSingle(event, Entity.class, 0);
		if (entity instanceof LivingEntity)
			((LivingEntity)entity).setCustomNameVisible(b);
	}

}
