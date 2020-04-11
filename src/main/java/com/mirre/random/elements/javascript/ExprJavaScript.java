package com.mirre.random.elements.javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.RandomExpression;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.utils.JavaScriptEngine;

import ch.njol.skript.lang.ExpressionType;

@Pattern(patterns = {"{S}execute [java]script %string% [(1¦)[with] arguments %-~objects%]", "{M}extended execute [java]script %string% [(1¦)[with] arguments %-~objects%]"})
@ExpressionsType(type = ExpressionType.COMBINED)
public class ExprJavaScript extends RandomExpression<Object> {

	@Override
	public Object[] obtain(Event event) {
		ScriptEngine engine = JavaScriptEngine.NASHORN.getEngine();
		String script = expressions.getSingle(event, String.class, 0);
		Object[] arguments = new Object[0];
		Object returnObject = null;
		if (parseMark == 1)
			arguments = expressions.getAll(event, Object.class, 0);
		try {
			if (parseMark != 1) {
				returnObject = engine.eval(script);
			} else {
				returnObject = ((Invocable)engine).invokeFunction(script, arguments);
			}
		} catch (ScriptException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (returnObject == null)
			return null;
		return (Object[])(returnObject.getClass().isArray() ? returnObject : new Object[] {returnObject});
	}

	public boolean isSupported() {
		return RandomSk.getInstance().JAVA_SCRIPT_ENABLED;
	}

}
