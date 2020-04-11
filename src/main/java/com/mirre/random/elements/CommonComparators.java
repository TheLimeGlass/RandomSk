package com.mirre.random.elements;

import ch.njol.skript.registrations.Comparators;
import ch.njol.skript.classes.Comparator;
import org.bukkit.util.Vector;
import com.mirre.random.lang.abstracts.Register;

public class CommonComparators implements Register {

	@Override
	public void register() {
		if (Comparators.getComparator(Vector.class, Vector.class) != null)
			return;
		Comparators.registerComparator(Vector.class, Vector.class, new Comparator<Vector, Vector>() {

			public Comparator.Relation compare(Vector v1, Vector v2) {
				return Comparator.Relation.get(v1.equals(v2));
			}

			public boolean supportsOrdering() {
				return false;
			}

		});
	}

}
