package com.mirre.random.elements.expressions;

import org.bukkit.event.Event;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomExpression;

@Pattern(patterns = {"{S}unix[ ]time"})
public class ExprUnixTime extends RandomExpression<Number> {

	@Override
	public Number[] obtain(Event event) {
		return new Number[] {System.currentTimeMillis() / 1000L};
	}

}
