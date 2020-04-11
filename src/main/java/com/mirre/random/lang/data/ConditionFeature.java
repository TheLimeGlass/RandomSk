package com.mirre.random.lang.data;

import java.util.Arrays;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.abstracts.AbstractFeature;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;

public class ConditionFeature extends AbstractFeature<Condition> {

	public ConditionFeature(Class<? extends Condition> feature) {
		super(feature);
	}

	@Override
	public void register() {
		if (isSupported()) {
			if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
				System.out.println("Registering: " + clazz.getSimpleName() + " Patterns: " + Arrays.toString(getExtractedPatterns()));
			Skript.registerCondition(clazz, getExtractedPatterns());
		}
	}

}
