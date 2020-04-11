package com.mirre.random.utils;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public enum JavaScriptEngine {

	NASHORN("NASHORN", 0);

	private ScriptEngine engine;

	private JavaScriptEngine(String name, int ordinal) {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
	}

	public ScriptEngine getEngine() {
		return engine;
	}

	public void renewEngine() {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
	}

}
