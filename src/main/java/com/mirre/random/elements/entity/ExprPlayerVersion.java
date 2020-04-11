package com.mirre.random.elements.entity;

import org.bukkit.entity.Player;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[the] [protocol] version of %player%", "{S}%player%'s [protocol] version"})
public class ExprPlayerVersion extends RandomPropertyExpression<Player, Number> {

	@Override
	public Number[] get(Player from) {
		return new Number[] {RandomSk.getInstance().getProtocolManager().getProtocolVersion(from)};
	}

}
