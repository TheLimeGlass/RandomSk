package com.mirre.random.elements.entity;

import org.bukkit.event.Event;
import org.bukkit.entity.LivingEntity;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.util.Timespan;
import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[the] [maximum] damage delay of %entity%", "{S}%entity%'s [maximum] damage delay"})
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprMaxDamageTicks extends RandomPropertyExpression<Entity, Timespan> implements Changer {

	@Override
	public Timespan[] get(Entity from) {
		if (from instanceof LivingEntity) {
			return new Timespan[] { Timespan.fromTicks_i(((LivingEntity)from).getMaximumNoDamageTicks())};
		}
		return null;
	}

	@Override
	public void changer(final Event event, final Object[] delta, ChangeMode mode) {
		Timespan timespan = (Timespan)delta[0];
		Entity entity = expressions.getSingle(event, Entity.class, 0);
		if (entity instanceof LivingEntity) {
			((LivingEntity)entity).setMaximumNoDamageTicks((int) timespan.getTicks_i());
		}
	}

}
