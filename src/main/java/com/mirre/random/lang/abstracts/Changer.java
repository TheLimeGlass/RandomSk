package com.mirre.random.lang.abstracts;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;

public interface Changer {

	void changer(Event event, Object[] delta, ChangeMode p2);

}
