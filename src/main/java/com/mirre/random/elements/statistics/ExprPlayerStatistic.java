package com.mirre.random.elements.statistics;

import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.ExpressionType;

@Pattern(patterns = { "{S}value of %statistic% of %player% [with (1¦|2¦material) %-entitytype/itemtype%]", "{S}%player%'s value of %statistic% [with (1¦|2¦material) %-entitytype/itemtype%]" })
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprPlayerStatistic extends RandomExpression<Number> {

	@Override
	public Number[] obtain(Event event) {
		Player player = expressions.getSingle(event, Player.class, 0);
		Statistic statistic = expressions.getSingle(event, Statistic.class, 0);
		if (parseMark == 1) {
			EntityType type = (EntityType) expressions.getSingle(event, Object.class, 0);
			return new Number[] {player.getStatistic(statistic, type)};
		} else if (parseMark == 2) {
			ItemType item = (ItemType) expressions.getSingle(event, Object.class, 0);
			return new Number[] {player.getStatistic(statistic, item.getMaterial()) };
		}
		return new Number[] {player.getStatistic(statistic)};
	}

}
