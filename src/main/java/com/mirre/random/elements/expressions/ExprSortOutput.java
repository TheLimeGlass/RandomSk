package com.mirre.random.elements.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.event.Event;

import com.google.common.collect.Lists;
import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.random.ObjectArg;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import ch.njol.util.Pair;

@Pattern(patterns = {"{M}sort[ed] %numbers% (1¦from highest to lowest|from lowest to highest) with (output|format) %string%", "{M}%numbers% sorted (1¦from highest to lowest|from lowest to highest) with (output|format) %string%"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprSortOutput extends RandomExpression<String> {

	@Override
	public String[] obtain(Event event) {
		String string = expressions.getSingle(event, String.class, 0);
		@SuppressWarnings("unchecked")
		Variable<Number> array = (Variable<Number>)expressions.get("Number0");
		Iterator<Pair<String, Object>> iterator = (Iterator<Pair<String, Object>>)array.variablesIterator(event);
		List<ObjectArg> objects = new ArrayList<>();
		while (iterator.hasNext()) {
			Pair<String, Object> pair = iterator.next();
			objects.add(new ObjectArg(pair.getKey(), (Number)pair.getValue()));
		}
		List<ObjectArg> list = ObjectArg.sort(objects);
		List<String> strings = new ArrayList<>();
		for (ObjectArg obArg : list) {
			final String s2 = string.replaceAll("@value", obArg.getIndex().toString());
			final String output = String.valueOf(obArg.getObject().toString().substring(0, 1).toUpperCase()) + obArg.getObject().toString().substring(1);
			final String s3 = s2.replaceAll("@index", output);
			strings.add(s3);
		}
		if (this.parseMark == 1)
			return Lists.reverse(strings).toArray(new String[strings.size()]);
		return strings.toArray(new String[strings.size()]);
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult parsedResult) {
		if (!(expr[0] instanceof Variable))
			Skript.warning("The given numbers is not a List Variable.");
		return super.init(expr, matchedPattern, isDelayed, parsedResult);
	}

}
