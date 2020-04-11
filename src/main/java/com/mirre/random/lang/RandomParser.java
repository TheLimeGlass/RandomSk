package com.mirre.random.lang;

import ch.njol.skript.classes.Parser;

public abstract class RandomParser<T> extends Parser<T> {

	private String variableNamePattern;
	
	public RandomParser(String variableNamePattern) {
		this.variableNamePattern = variableNamePattern;
	}

	public String getVariableNamePattern() {
		return variableNamePattern + ":.+";
	}

	public String toString(T object, int i) {
		return object.toString().toLowerCase().replace("_", " ");
	}

	public String toVariableNameString(T object) {
		return variableNamePattern + ":" + " " + object.toString().toLowerCase();
	}

}
