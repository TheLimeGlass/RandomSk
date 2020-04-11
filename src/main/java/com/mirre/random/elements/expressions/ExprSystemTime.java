package com.mirre.random.elements.expressions;

import org.bukkit.event.Event;
import ch.njol.skript.lang.ExpressionType;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomExpression;

@Pattern(patterns = {"[current] system millis", "[current] system nanos"})
@ExpressionsType(type = ExpressionType.SIMPLE)
public class ExprSystemTime extends RandomExpression<Long> {

	@Override
	public Long[] obtain(Event event) {
		return new Long[] {patternMark == 0 ? System.currentTimeMillis() : System.nanoTime()};
	}

}
