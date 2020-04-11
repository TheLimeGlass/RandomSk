package com.mirre.random.lang;

import org.bukkit.event.Event;

public abstract class RandomPropertyCondition<F> extends RandomCondition {

	@Override
	public boolean inspect(Event event) {
		if (expressions.size() == 1) {
			@SuppressWarnings("unchecked")
			F o = (F) expressions.getExpressions()[0].getSingle(event);
			return inspect(o);
		}
		return false;
	}

	public abstract boolean inspect(F p0);

}
