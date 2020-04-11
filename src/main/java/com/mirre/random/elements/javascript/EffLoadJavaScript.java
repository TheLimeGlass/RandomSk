package com.mirre.random.elements.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.JavaScriptEngine;

@Pattern(patterns = {"load javascript from %string%"})
public class EffLoadJavaScript extends RandomEffect {

	@Override
	public void perform(Event event) {
		ScriptEngine engine = JavaScriptEngine.NASHORN.getEngine();
		String location = expressions.getSingle(event, String.class, 0);
		try {
			engine.eval(new FileReader(RandomSk.getInstance().getDataFolder().getAbsolutePath() + "/javascripts/" + location));
		} catch (FileNotFoundException | ScriptException e) {
			e.printStackTrace();
		}
	}

	public boolean isSupported() {
		return RandomSk.getInstance().JAVA_SCRIPT_ENABLED;
	}

}
