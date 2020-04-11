package com.mirre.random.elements.events;

import org.bukkit.Statistic;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.eclipse.jdt.annotation.Nullable;

import com.mirre.random.lang.RandomEvent;
import com.mirre.random.lang.annonations.Events;
import com.mirre.random.lang.annonations.Pattern;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

@Pattern(patterns = {"statistic[s] (increment|increase)", "statistic[s] (increment|increase) of %statistic%"})
@Events(forEvents = {PlayerStatisticIncrementEvent.class})
public class EvtStatistics extends RandomEvent {

	public boolean check(Event event) {
		if (patternMark == 1) {
			Statistic statistic = literals.getSingle(Statistic.class, 0);
			return statistic.equals(((PlayerStatisticIncrementEvent)event).getStatistic());
		}
		return event instanceof PlayerStatisticIncrementEvent;
	}

	@Override
	public void register() {
		super.register();
		EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Statistic.class, new Getter<Statistic, PlayerStatisticIncrementEvent>() {
			@Nullable
			public Statistic get(PlayerStatisticIncrementEvent event) {
				return event.getStatistic();
			}
		}, 0);
	}

}
