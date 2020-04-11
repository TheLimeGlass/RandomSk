package com.mirre.random.lang.abstracts;

public interface Register {

	void register();

	default boolean isSupported() {
		return true;
	}

}
