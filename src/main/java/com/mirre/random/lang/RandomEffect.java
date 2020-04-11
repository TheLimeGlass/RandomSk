package com.mirre.random.lang;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.lang.data.EffectFeature;
import com.mirre.random.lang.data.lists.ExpressionList;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class RandomEffect extends Effect implements Register {

	protected ExpressionList expressions;
	private EffectFeature feature;
	protected int patternMark;
	protected int parseMark;

	public RandomEffect() {
		this.feature = new EffectFeature(getClass());
		this.expressions = null;
	}

	public void register() {
		if (isSupported())
			feature.register();
	}
	
	public boolean init(Expression<?>[] expressions, int patternMark, Kleenean isDelayed, ParseResult parseResult) {
		feature.checkWarning();
		if (!this.feature.eventIsSupported())
			return false;
		this.expressions = new ExpressionList(expressions, feature.getExtractedPatterns()[patternMark]);
		this.patternMark = patternMark;
		this.parseMark = parseResult.mark;
		return true;
	}

	public String toString(@Nullable Event event, boolean flag) {
		return getClass().getSimpleName();
	}

	protected void execute(final Event event) {
		if (expressions.catchAnyNull(event))
			return;
		this.perform(event);
	}

	public abstract void perform(Event event);

}
