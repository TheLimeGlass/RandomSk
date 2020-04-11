package com.mirre.random.lang.abstracts;

import org.bukkit.event.Event;

public interface Feature<F> {

	String[] getRawPatterns();

	Class<? extends Event>[] forEvents();

	void register();

	boolean isSupported();

	boolean eventIsSupported();

	void checkWarning();

}
