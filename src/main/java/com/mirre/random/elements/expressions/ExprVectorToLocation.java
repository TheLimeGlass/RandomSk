package com.mirre.random.elements.expressions;

import org.bukkit.World;
import org.bukkit.util.Vector;
import org.bukkit.event.Event;
import ch.njol.skript.lang.ExpressionType;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;
import org.bukkit.Location;
import com.mirre.random.lang.RandomExpression;

@Pattern(patterns = {"{S}%vector% to location of %world%", "{S}%vector% to location of %world% with yaw %number% [(and|&)] pitch %number%"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprVectorToLocation extends RandomExpression<Location> {

	@Override
	public Location[] obtain(Event event) {
		Vector v = expressions.getSingle(event, Vector.class, 0);
		World world = this.expressions.getSingle(event, World.class, 0);
		if (patternMark == 0)
			return new Location[] {v.toLocation(world)};
		Number yaw = expressions.getSingle(event, Number.class, 0);
		Number pitch = expressions.getSingle(event, Number.class, 1);
		return new Location[] {v.toLocation(world, yaw.floatValue(), pitch.floatValue())};
	}

}
