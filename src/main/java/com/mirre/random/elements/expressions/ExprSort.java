package com.mirre.random.elements.expressions;

import java.util.Arrays;
import org.bukkit.event.Event;
import ch.njol.skript.lang.ExpressionType;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomExpression;

@Pattern(patterns = {"{M}sort[ed] %numbers% [(1¦from highest to lowest|from lowest to highest)]", "{M}%numbers% sorted [(1¦from highest to lowest|from lowest to highest)]"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprSort extends RandomExpression<Number> {

	@Override
	public Number[] obtain(final Event event) {
		Number[] array = expressions.getAll(event, Number.class, 0);
		Arrays.sort(array);
		if (parseMark == 1) {
			Number[] reversed = new Number[array.length];
			for (int i = 0; i < array.length; ++i) {
				reversed[i] = array[array.length - 1 - i];
			}
			return reversed;
		}
		return array;
	}

}
