package com.mirre.random.lang.data;

import java.util.Arrays;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.abstracts.AbstractFeature;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;

public class EffectFeature extends AbstractFeature<Effect> {

	public EffectFeature(Class<? extends Effect> feature) {
		super(feature);
	}

	@Override
	public void register() {
		if (isSupported()) {
			if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
				System.out.println("Registering: " + clazz.getSimpleName() + " Patterns: " + Arrays.toString(getExtractedPatterns()));
			Skript.registerEffect(clazz, getExtractedPatterns());
		}
	}

}
