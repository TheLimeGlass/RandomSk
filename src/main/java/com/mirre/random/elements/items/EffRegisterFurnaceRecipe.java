package com.mirre.random.elements.items;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.util.Timespan;

@Pattern(patterns = {"register [a] furnace recipe with [result] %itemtype% for [source] %itemtype% (with|earning) %number% (xp|experience) with cook time %timespan%"})
public class EffRegisterFurnaceRecipe extends RandomEffect {

	@Override
	public void perform(Event event) {
		ItemType result = expressions.getSingle(event, ItemType.class, 0);
		ItemType material = expressions.getSingle(event, ItemType.class, 1);
		Number experience = expressions.getSingle(event, Number.class, 0);
		Timespan timespan = expressions.getSingle(event, Timespan.class, 0);
		FurnaceRecipe fRecipe = new FurnaceRecipe(new NamespacedKey(RandomSk.getInstance(), "furnace-" + UUID.randomUUID()), result.getRandom(), material.getMaterial(), experience.floatValue(), (int) timespan.getTicks_i());
		Bukkit.getServer().addRecipe((Recipe)fRecipe);
	}

}
