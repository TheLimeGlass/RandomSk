package com.mirre.random.elements.entity;

import org.bukkit.event.Event;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Ageable;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;
import com.mirre.random.utils.enums.Age;

import ch.njol.skript.classes.Changer.ChangeMode;

import org.bukkit.entity.Entity;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[the] age of %entity%", "{S}%entity%'s age"})
@ChangeModes(changeModes = {ChangeMode.SET})
public class ExprAge extends RandomPropertyExpression<Entity, Age> implements Changer {

	@Override
	public Age[] get(Entity from) {
		if (from instanceof Ageable) {
			return new Age[] { ((Ageable)from).isAdult() ? Age.ADULT : Age.BABY };
		}
		if (from instanceof Zombie) {
			return new Age[] { ((Zombie)from).isBaby() ? Age.BABY : Age.ADULT };
		}
		if (from instanceof PigZombie) {
			return new Age[] { ((PigZombie)from).isBaby() ? Age.BABY : Age.ADULT };
		}
		return null;
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Entity entity = expressions.getSingle(event, Entity.class, 0);
		Age age = (Age)delta[0];
		if (entity instanceof Ageable) {
			if (age == Age.ADULT) {
				((Ageable)entity).setAdult();
			}
			else {
				((Ageable)entity).setBaby();
			}
		} else if (entity instanceof Zombie) {
			if (age == Age.ADULT) {
				((Zombie)entity).setBaby(false);
			}
			else {
				((Zombie)entity).setBaby(true);
			}
		} else if (entity instanceof PigZombie) {
			if (age == Age.ADULT) {
				((PigZombie)entity).setBaby(false);
			}
			else {
				((PigZombie)entity).setBaby(true);
			}
		}
	}

}
