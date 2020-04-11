package com.mirre.random.utils.enums;

public enum Animation {

	PUNCH("PUNCH", 0, Integer.valueOf(0)), 
	WAKEUP("WAKEUP", 1, Integer.valueOf(2)), 
	EAT("EAT", 2, Integer.valueOf(3)), 
	DAMAGE("DAMAGE", 3, Integer.valueOf(1)), 
	CRITICAL("CRITICAL", 4, Integer.valueOf(4)), 
	CRITICAL2("CRITICAL2", 5, Integer.valueOf(5)), 
	CROUCH("CROUCH", 6, Integer.valueOf(104)), 
	UNCROUCH("UNCROUCH", 7, Integer.valueOf(105));

	private Integer integer;

	private Animation(String name, int ordinal, Integer i) {
		this.integer = i;
	}

	public Byte toByte() {
		return integer.byteValue();
	}

}
