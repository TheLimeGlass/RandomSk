package com.mirre.random.elements.items;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomEffect;

@Pattern(patterns = {"reset [all] recipes"})
public class EffResetRecipes extends RandomEffect {

	@Override
	public void perform(Event event) {
		Bukkit.resetRecipes();
	}

}
