package com.mirre.random.lang.expressions;

import org.bukkit.event.Event;
import com.mirre.random.lang.RandomExpression;

public abstract class RandomPropertyExpression<F, T> extends RandomExpression<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] obtain(Event event) {
		if (expressions.size() == 1)
			return get((F) expressions.getExpressions()[0].getSingle(event));
		return null;
	}

	public abstract T[] get(F from);

}
