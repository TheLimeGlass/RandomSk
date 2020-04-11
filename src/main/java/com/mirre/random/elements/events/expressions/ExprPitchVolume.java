package com.mirre.random.elements.events.expressions;

import org.bukkit.event.Event;

import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.abstracts.Changer;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.Events;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.events.NamedSoundEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

@Pattern(patterns = {"{S}(1¦pitch|2¦volume) [value]"})
@ChangeModes(changeModes = {ChangeMode.SET})
@Events(forEvents = {NamedSoundEvent.class})
public class ExprPitchVolume extends RandomExpression<Number> implements Changer {

	@Override
	public Number[] obtain(Event event) {
		NamedSoundEvent soundEvent = (NamedSoundEvent)event;
		return new Number[] {parseMark == 1 ? soundEvent.getPitch() : soundEvent.getVolume()};
	}

	@Override
	public void changer(Event event, Object[] delta, ChangeMode mode) {
		NamedSoundEvent soundEvent = (NamedSoundEvent)event;
		if (parseMark == 1)
			soundEvent.setPitch(((Number)delta[0]).floatValue());
		else
			soundEvent.setVolume(((Number)delta[0]).floatValue());
	}

	@Override
	public boolean init(Expression<?>[] expressions, int patternMark, Kleenean isDelayed, ParseResult parseResult) {
		if (isDelayed.isTrue()) {
			Skript.error("This expression cannot be delayed.");
			return false;
		}
		return super.init(expressions, patternMark, isDelayed, parseResult);
	}

}
