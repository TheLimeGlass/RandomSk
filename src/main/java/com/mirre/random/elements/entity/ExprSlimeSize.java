package com.mirre.random.elements.entity;

import org.bukkit.event.Event;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;
import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

import ch.njol.skript.classes.Changer.ChangeMode;

@Pattern(patterns = {"{S}[the] slime size of %entity%", "{S}%entity%'s slime size"})
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprSlimeSize extends RandomPropertyExpression<Entity, Number> implements Changer {

	@Override
	public Number[] get(Entity from) {
		if (from instanceof Slime || from instanceof MagmaCube)
			return new Number[] { ((Slime)from).getSize()};
		return null;
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Number number = (Number)delta[0];
		Entity ent = expressions.getSingle(event, Entity.class, 0);
		if (ent instanceof Slime || ent instanceof MagmaCube)
			((Slime)ent).setSize(number.intValue());
	}

}
