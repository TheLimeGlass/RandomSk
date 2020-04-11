package com.mirre.random.elements.statistics;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import com.mirre.random.lang.annonations.Events;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomExpression;

@Pattern(patterns = {"{S}(previous|old) [statistic] value"})
@Events(forEvents = {PlayerStatisticIncrementEvent.class})
public class ExprOldValueStatistic extends RandomExpression<Number> {

	@Override
	public Number[] obtain(Event event) {
		return new Number[] {((PlayerStatisticIncrementEvent)event).getPreviousValue()};
	}

}
