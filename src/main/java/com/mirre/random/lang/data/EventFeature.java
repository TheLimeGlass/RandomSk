package com.mirre.random.lang.data;

import java.util.Arrays;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.abstracts.AbstractFeature;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.SkriptEvent;

public class EventFeature extends AbstractFeature<SkriptEvent> {

	public EventFeature(Class<? extends SkriptEvent> feature) {
		super(feature);
	}

	@Override
	public void register() {
		if (isSupported()) {
			if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
				System.out.println("Registering: " + clazz.getSimpleName() + " Patterns: " + Arrays.toString(getExtractedPatterns()));
			Skript.registerEvent(clazz.getSimpleName(), clazz, forEvents(), getExtractedPatterns());
		}
	}

}
