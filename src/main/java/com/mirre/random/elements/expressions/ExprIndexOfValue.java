package com.mirre.random.elements.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.event.Event;

import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import ch.njol.util.Pair;

@Pattern(patterns = {"{M}indexes of value %object% in %~objects%"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprIndexOfValue extends RandomExpression<String> {

	@Override
	public String[] obtain(Event event) {
		Object object = expressions.getSingle(event, Object.class, 0);
		Expression<?> expression = expressions.get("Object1");
		if (expression instanceof Variable) {
			List<String> matches = new ArrayList<>();
			Iterator<Pair<String, Object>> iterator = (Iterator<Pair<String, Object>>)((Variable<?>)expression).variablesIterator(event);
			while (iterator.hasNext()) {
				Pair<String, Object> pair = iterator.next();
				if (pair.getValue().equals(object))
					matches.add((String)pair.getKey());
			}
			return matches.toArray(new String[matches.size()]);
		}
		return null;
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parsedResult) {
		try {
			Variable<?> array = (Variable<?>)expr[0];
			if (array.isList())
				return super.init(expr, matchedPattern, isDelayed, parsedResult);
		} catch (ClassCastException e) {
			Skript.warning("This is not a list variable");
		}
		Skript.warning("This is not a list variable");
		return false;
	}

}
