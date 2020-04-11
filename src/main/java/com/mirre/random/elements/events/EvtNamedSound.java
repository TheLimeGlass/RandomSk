package com.mirre.random.elements.events;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.RandomEvent;
import com.mirre.random.lang.annonations.Events;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.events.NamedSoundEvent;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.util.Checker;

@Pattern(patterns = {"[named] sound [%sounds%] [(trigger|play)]", "player hear[ing] sound [%sounds%]"})
@Events(forEvents = {NamedSoundEvent.class})
public class EvtNamedSound extends RandomEvent {

	private Literal<Sound> sounds;

	@Override
	public void register() {
		super.register();
		EventValues.registerEventValue(NamedSoundEvent.class, Player.class, new Getter<Player, NamedSoundEvent>() {
			@Nullable
			public Player get(NamedSoundEvent event) {
				return event.getPlayer();
			}
		}, 0);
		EventValues.registerEventValue(NamedSoundEvent.class, Location.class, new Getter<Location, NamedSoundEvent>() {
			@Nullable
			public Location get(NamedSoundEvent event) {
				return event.getLocation();
			}
		}, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] literals, int patternMark, ParseResult parseResult) {
		if (literals[0] != null)
			sounds = (Literal<Sound>) literals[0];
		return super.init(literals, patternMark, parseResult);
	}

	public boolean check(Event event) {
		if (sounds != null) {
			Sound eventSound = ((NamedSoundEvent) event).getSound();
			return sounds.check(event, new Checker<Sound>() {
				@Override
				public boolean check(Sound sound) {
					return eventSound == sound;
				}
			});
		}
		return true;
	}

}
