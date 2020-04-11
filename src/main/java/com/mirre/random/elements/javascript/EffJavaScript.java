package com.mirre.random.elements.javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.JavaScriptEngine;

@Pattern(patterns = {"evaluate [java]script %string% [(1¦)[with] arguments %-~objects%]"})
public class EffJavaScript extends RandomEffect {

	@Override
	public void perform(Event event) {
		ScriptEngine engine = JavaScriptEngine.NASHORN.getEngine();
		String script = expressions.getSingle(event, String.class, 0);
		Object[] arguments = new Object[0];
		if (parseMark == 1)
			arguments = expressions.getAll(event, Object.class, 0);
		try {
			if (parseMark != 1) {
				engine.eval(script);
			} else {
				((Invocable)engine).invokeFunction(script, arguments);
			}
		} catch (ScriptException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public boolean isSupported() {
		return RandomSk.getInstance().JAVA_SCRIPT_ENABLED;
	}

}
