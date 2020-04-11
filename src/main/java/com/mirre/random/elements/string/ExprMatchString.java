package com.mirre.random.elements.string;

import org.bukkit.event.Event;

import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;

import ch.njol.skript.lang.ExpressionType;

@Pattern(patterns = {"{S}%string% (match(es|ing)|compared to) %string%", "{S}%string% (match(es|ing)|compared to) %string% case[-]sensitive"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprMatchString extends RandomExpression<Number> {

	@Override
	public Number[] obtain(final Event event) {
		String a = expressions.getSingle(event, String.class, 0);
		String b = expressions.getSingle(event, String.class, 1);
		int distance = distance(a, b, patternMark == 0);
		return new Number[] {distance};
	}

	private int distance(String a, String b, boolean ignoreCase) {
		if (ignoreCase) {
			a = a.toLowerCase();
			b = b.toLowerCase();
		}
		int[] costs = new int[b.length() + 1];
		for (int j = 0; j < costs.length; ++j) {
			costs[j] = j;
		}
		for (int i = 1; i <= a.length(); ++i) {
			costs[0] = i;
			int nw = i - 1;
			for (int k = 1; k <= b.length(); ++k) {
				int cj = Math.min(1 + Math.min(costs[k], costs[k - 1]), (a.charAt(i - 1) == b.charAt(k - 1)) ? nw : (nw + 1));
				nw = costs[k];
				costs[k] = cj;
			}
		}
		return costs[b.length()];
	}

}
