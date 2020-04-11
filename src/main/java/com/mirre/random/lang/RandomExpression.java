package com.mirre.random.lang;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.lang.data.ExpressionFeature;
import com.mirre.random.lang.data.lists.ExpressionList;
import com.mirre.random.lang.data.results.ExpressionResult;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public abstract class RandomExpression<T> extends SimpleExpression<T> implements Register {

	protected ExpressionList expressions;
	private ExpressionFeature<T> feature;
	private ExpressionResult result;
	protected int patternMark;
	protected int parseMark;

	@SuppressWarnings("unchecked")
	public RandomExpression() {
		this.feature = new ExpressionFeature<T>((Class<? extends Expression<T>>) getClass());
		this.expressions = null;
		this.result = null;
	}

	public void register() {
		if (isSupported())
			feature.register();
	}

	public Class<? extends T> getReturnType() {
		return feature.getReturnType();
	}

	public boolean isSingle() {
		return result.isSingle();
	}

	public boolean init(Expression<?>[] expressions, int patternMark, Kleenean isDelayed, ParseResult parseResult) {
		this.feature.checkWarning();
		if (!feature.eventIsSupported())
			return false;
		this.result = new ExpressionResult(feature.getRawPatterns()[patternMark]);
		this.expressions = new ExpressionList(expressions, feature.getExtractedPatterns()[0]);
		this.patternMark = patternMark;
		this.parseMark = parseResult.mark;
		return true;
	}

	public String toString(Event event, boolean b) {
		return getClass().getSimpleName();
	}

	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (expressions.catchAnyNull(event))
			return;
		if (nullCheck(delta) && mode != Changer.ChangeMode.DELETE && mode != Changer.ChangeMode.RESET)
			return;
		if (this instanceof com.mirre.random.lang.abstracts.Changer)
			((com.mirre.random.lang.abstracts.Changer)this).changer(event, delta, mode);
	}

	public Class<?>[] acceptChange(ChangeMode mode) {
		ChangeMode[] modes = feature.getChangeModes();
		if (modes == null)
			return null;
		for (ChangeMode m : modes) {
			if (mode == m)
				return CollectionUtils.array(feature.getAcceptModes());
		}
		return null;
	}

	private boolean nullCheck(final Object... objects) {
		if (objects == null || objects.length == 0) {
			return true;
		}
		for (final Object o : objects) {
			if (o == null) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	protected T[] get(Event event) {
		return !expressions.catchAnyNull(event) ? obtain(event) : null;
	}

	public abstract T[] obtain(Event event);

}
