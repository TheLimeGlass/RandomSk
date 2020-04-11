package com.mirre.random.elements.javascript;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomEffect;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.JavaScriptEngine;

@Pattern(patterns = {"(renew|restart) javascript engine"})
public class EffRenewEngine extends RandomEffect {

	@Override
	public void perform(Event event) {
		JavaScriptEngine.NASHORN.renewEngine();
	}

	public boolean isSupported() {
		return RandomSk.getInstance().JAVA_SCRIPT_ENABLED;
	}

}
