package com.mirre.random.elements.expressions;

import org.bukkit.event.Event;
import com.mirre.random.utils.enums.BlockFeature;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.abstracts.Changer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.util.Timespan;
import org.bukkit.block.Block;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}brew[ing] time of %block%", "{S}burn[ing] time of %block%", "{S}cook[ing] time of %block%", "{S}[mob] spawn delay of %block%"})
@ChangeModes(changeModes = {ChangeMode.SET, ChangeMode.ADD, ChangeMode.REMOVE})
public class ExprBlockFeatures extends RandomPropertyExpression<Block, Timespan> implements Changer {

	@Override
	public Timespan[] get(Block from) {
		BlockFeature feature = BlockFeature.getBlockFeature(patternMark);
		try {
			return new Timespan[] { Timespan.fromTicks_i(feature.get(from).intValue())};
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		Block block = expressions.getSingle(event, Block.class, 0);
		Timespan time = (Timespan)delta[0];
		BlockFeature feature = BlockFeature.getBlockFeature(parseMark);
		Number number = feature.get(block);
		if (number == null && mode != ChangeMode.SET)
			return;
		Number duration = null;
		switch (mode) {
			case ADD:
				duration = number.intValue() + time.getTicks_i();
				break;
			case REMOVE:
				duration = number.intValue() - time.getTicks_i();
				break;
			case SET:
				duration = time.getTicks_i();
				break;
			case DELETE:
			case REMOVE_ALL:
			case RESET:
			default:
				break;
		}
		feature.set(block, duration);
	}

}
