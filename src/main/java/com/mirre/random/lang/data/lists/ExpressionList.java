package com.mirre.random.lang.data.lists;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;

import ch.njol.skript.lang.Expression;

public class ExpressionList {

	private final static Pattern MATCH_EXPRESSION = Pattern.compile("\\%([^\\%]+)\\%");
	private final Map<String, Integer> expressionNames = new HashMap<>();
	private Expression<?>[] expressions;

	public ExpressionList(Expression<?>[] expressions, String pattern) {
		Matcher matcher = ExpressionList.MATCH_EXPRESSION.matcher(pattern);
		int i = 0;
		while (matcher.find()) {
			String expression;
			for (expression = matcher.group(1); expression.startsWith("-") || expression.startsWith("~") || expression.startsWith("*"); expression = expression.substring(1, expression.length())) {}
			if (expression.contains("/")) {
				expression = "object";
			}
			if (expression.endsWith("s")) {
				expression = expression.substring(0, expression.length() - 1);
			}
			expression = expression + "0";
			if (expressionNames.containsKey(expression)) {
				while (expressionNames.containsKey(expression)) {
					String lastChar = new StringBuilder().append(expression.charAt(expression.length() - 1)).toString();
					if (lastChar.matches("[0-9]")) {
						int integer = Integer.parseInt(lastChar);
						expression = expression.replace(lastChar, "");
						expression = String.valueOf(expression) + (integer + 1);
					}
				}
			}
			expressionNames.put(expression, i);
			++i;
		}
		this.expressions = expressions;
	}

	public int size() {
		return expressionNames.size();
	}

	public boolean catchAnyNull(Event event) {
		for (Expression<?> e : expressions) {
			if (e != null && e.isSingle()) {
				if (e.getSingle(event) == null) {
					if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
						System.out.println("[RandomSK] One or more expressions/variables are null.");
					return true;
				}
			} else if (e != null && (e.getAll(event).length == 0 || e.getAll(event) == null)) {
				if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
					System.out.println("[RandomSK] One or more expressions/variables are null.");
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> T getSingle(Event event, Class<T> clazz, int index) {
		if (expressionNames.containsKey(clazz.getSimpleName().toLowerCase() + index)) {
			int i = this.expressionNames.get(clazz.getSimpleName().toLowerCase() + index);
			return (T)this.expressions[i].getSingle(event);
		}
		return null;
	}

	public Expression<?> get(String key) {
		if (expressionNames.containsKey(key.toLowerCase())) {
			int i = expressionNames.get(key.toLowerCase());
			return expressions[i];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] getAll(Event event, Class<T> clazz, int index) {
		if (expressionNames.containsKey(clazz.getSimpleName().toLowerCase() + index)) {
			int i = expressionNames.get(clazz.getSimpleName().toLowerCase() + index);
			return (T[])this.expressions[i].getAll(event);
		}
		return null;
	}

	public Map<String, Integer> getMap() {
		return expressionNames;
	}

	public Expression<?>[] getExpressions() {
		return expressions;
	}

}
