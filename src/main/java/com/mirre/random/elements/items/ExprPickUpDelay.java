package com.mirre.random.elements.items;

import org.bukkit.event.Event;
import org.bukkit.entity.Item;
import org.bukkit.entity.EntityType;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.util.Timespan;
import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[the] pick up delay of %entity%", "{S}%entity%'s pick up delay"})
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprPickUpDelay extends RandomPropertyExpression<Entity, Timespan> implements Changer {

	@Override
	public Timespan[] get(final Entity from) {
		if (from.getType() != EntityType.DROPPED_ITEM)
			return null;
		Item i = (Item)from;
		if (i.getPickupDelay() <= 0)
			return null;
		return new Timespan[] {Timespan.fromTicks_i(i.getPickupDelay())};
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Timespan timespan = (Timespan)delta[0];
		Entity ent = expressions.getSingle(event, Entity.class, 0);
		if (ent.getType() != EntityType.DROPPED_ITEM)
			return;
		Item item = (Item)ent;
		item.setPickupDelay((int) timespan.getTicks_i());
	}

}
