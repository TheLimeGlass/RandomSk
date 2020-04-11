package com.mirre.random.elements.expressions;

import com.mirre.random.lang.annonations.Pattern;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[the] (direction|vector) of %location%", "{S}%location%'s (direction|vector)"})
public class ExprDirectionOfLocation extends RandomPropertyExpression<Location, Vector> {

	@Override
	public Vector[] get(Location from) {
		return new Vector[] {from.getDirection()};
	}

}
