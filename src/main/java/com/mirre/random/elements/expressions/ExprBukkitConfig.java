package com.mirre.random.elements.expressions;

import org.bukkit.event.Event;

import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;
import org.bukkit.World;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

import ch.njol.skript.classes.Changer.ChangeMode;

@Pattern(patterns = {"{S}[the] (1妃onster|2地nimal|3安ater[(-| )]animals|4地mbient) spawn limit[s] of %world%", "{S}%world%'s (1妃onster|2地nimal|3安ater[(-| )]animals|4地mbient) spawn limit[s]"})
@ChangeModes(changeModes = {ChangeMode.SET, ChangeMode.ADD, ChangeMode.REMOVE})
public class ExprBukkitConfig extends RandomPropertyExpression<World, Number> implements Changer {

	@Override
	public Number[] get(World from) {
		switch (parseMark) {
			case 1:
				return new Number[] {from.getMonsterSpawnLimit()};
			case 2:
				return new Number[] {from.getAnimalSpawnLimit()};
			case 3:
				return new Number[] {from.getWaterAnimalSpawnLimit()};
			case 4:
				return new Number[] {from.getAmbientSpawnLimit()};
			default:
				return null;
		}
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		World from = expressions.getSingle(event, World.class, 0);
		Number number = (Number)delta[0];
		switch (mode) {
			case ADD:
				number = this.get(from)[0].intValue() + number.intValue();
				break;
			case REMOVE:
				number = this.get(from)[0].intValue() - number.intValue();
				break;
			default:
				break;
		}
		switch (parseMark) {
			case 1:
				from.setMonsterSpawnLimit(number.intValue());
				break;
			case 2:
				from.setAnimalSpawnLimit(number.intValue());
				break;
			case 3:
				from.setWaterAnimalSpawnLimit(number.intValue());
				break;
			case 4:
				from.setMonsterSpawnLimit(number.intValue());
				break;
		}
	}

}
