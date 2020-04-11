package com.mirre.random.elements.expressions;

import com.mirre.random.lang.annonations.Pattern;
import ch.njol.skript.util.Direction;
import org.bukkit.util.Vector;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}%vector% as direction"})
public class ExprVectorToDirection extends RandomPropertyExpression<Vector, Direction> {

	@Override
	public Direction[] get(Vector from) {
		return new Direction[] {new Direction(from)};
	}

}
