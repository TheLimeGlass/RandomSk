package com.mirre.random.elements.entity;

import org.bukkit.event.Event;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.util.Timespan;
import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}%entity%'s time lived", "{S}[the] time lived of %entity%"})
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprTimeLived extends RandomPropertyExpression<Entity, Timespan> implements Changer {

	@Override
	public Timespan[] get(final Entity from) {
		int lived = from.getTicksLived();
		if (lived <= 0)
			return null;
		return new Timespan[] {Timespan.fromTicks_i(lived)};
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Entity from = expressions.getSingle(event, Entity.class, 0);
		Timespan time = (Timespan)delta[0];
		from.setTicksLived((int) time.getTicks_i());
	}

}
