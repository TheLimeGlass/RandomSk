package com.mirre.random.lang;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.lang.data.EventFeature;
import com.mirre.random.lang.data.lists.LiteralList;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public abstract class RandomEvent extends SkriptEvent implements Register {

	protected LiteralList literals;
	private EventFeature feature;
	protected int patternMark;
	protected int parseMark;

	public RandomEvent() {
		this.feature = new EventFeature(getClass());
		this.literals = null;
	}

	public void register() {
		if (isSupported())
			feature.register();
	}

	public String toString(@Nullable Event event, boolean flag) {
		return getClass().getSimpleName();
	}

	public boolean init(Literal<?>[] literals, int patternMark, ParseResult parseResult) {
		this.feature.checkWarning();
		this.literals = new LiteralList(literals, feature.getExtractedPatterns()[patternMark]);
		this.patternMark = patternMark;
		this.parseMark = parseResult.mark;
		return true;
	}

}
