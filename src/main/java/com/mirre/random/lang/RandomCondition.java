package com.mirre.random.lang;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.lang.data.ConditionFeature;
import com.mirre.random.lang.data.lists.ExpressionList;
import com.mirre.random.lang.data.results.ConditionResult;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class RandomCondition extends Condition implements Register {

	protected ExpressionList expressions;
	private ConditionFeature feature;
	private ConditionResult result;
	protected int patternMark;
	protected int parseMark;
	
	public RandomCondition() {
		this.feature = new ConditionFeature(getClass());
		this.expressions = null;
	}

	public void register() {
		if (isSupported())
			feature.register();
	}

	public boolean init(Expression<?>[] expressions, int patternMark, Kleenean isDelayed, ParseResult parseResult) {
		feature.checkWarning();
		if (!feature.eventIsSupported())
			return false;
		this.result = new ConditionResult(feature.getRawPatterns()[patternMark]);
		this.expressions = new ExpressionList(expressions, feature.getExtractedPatterns()[patternMark]);
		this.patternMark = patternMark;
		this.parseMark = parseResult.mark;
		return true;
	}

	public String toString(@Nullable Event event, boolean flag) {
		return getClass().getSimpleName();
	}

	public boolean check(Event event) {
		return !expressions.catchAnyNull(event) && (result.isInverted() ? (!inspect(event)) : inspect(event));
	}

	public abstract boolean inspect(Event event);

}
