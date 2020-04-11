package com.mirre.random.lang.abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.Event;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.annonations.Events;
import com.mirre.random.lang.annonations.Warning;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.log.ErrorQuality;

public abstract class AbstractFeature<T> implements Feature<T> {

	private String[] rawPatterns;
	private String[] extractedPatterns;
	private Class<? extends Event>[] forEvents;
	protected Class<? extends T> clazz;
	private static Pattern MATCH_VARIABLE;
	
	static {
		AbstractFeature.MATCH_VARIABLE = Pattern.compile("\\{([^\\}]+)\\}");
	}
	
	public AbstractFeature(Class<? extends T> feature) {
		this.rawPatterns = null;
		this.extractedPatterns = null;
		this.forEvents = null;
		this.clazz = feature;
	}

	@Override
	public void checkWarning() {
		if (RandomSk.getInstance().getConfig().getBoolean("suppress-incomplete-warning", false))
			return;
		if (clazz.isAnnotationPresent(Warning.class)) {
			Warning w = clazz.getDeclaredAnnotation(Warning.class);
			Skript.warning(w.reason().getReason(w.data()));
		}
	}

	@Override
	public String[] getRawPatterns() {
		if (rawPatterns == null && clazz.isAnnotationPresent(com.mirre.random.lang.annonations.Pattern.class)) {
			com.mirre.random.lang.annonations.Pattern p = clazz.getDeclaredAnnotation(com.mirre.random.lang.annonations.Pattern.class);
			rawPatterns = p.patterns();
		}
		return this.rawPatterns;
	}

	public String[] getExtractedPatterns() {
		if (extractedPatterns == null) {
			List<String> list = new ArrayList<>();
			for (String pattern : getRawPatterns()) {
				StringBuffer output = new StringBuffer();
				Matcher matcher = AbstractFeature.MATCH_VARIABLE.matcher(pattern);
				while (matcher.find()) {
					matcher.appendReplacement(output, Matcher.quoteReplacement(""));
				}
				matcher.appendTail(output);
				String s;
				for (s = output.toString(); s.startsWith(" "); s = s.replaceFirst(" ", "")) {
					list.add(s);
				}
			}
			this.extractedPatterns = list.toArray(new String[list.size()]);
		}
		return this.extractedPatterns;
	}

	@Override
	public Class<? extends Event>[] forEvents() {
		if (forEvents == null && clazz.isAnnotationPresent(Events.class)) {
			Events e = clazz.getDeclaredAnnotation(Events.class);
			forEvents = e.forEvents();
		}
		return forEvents;
	}

	@Override
	public boolean isSupported() {
		return true;
	}

	@Override
	public boolean eventIsSupported() {
		Class<? extends Event>[] events = forEvents();
		if (events == null)
			return true;
		if (!ScriptLoader.isCurrentEvent(events)) {
			Skript.error("Cannot use " + clazz.getSimpleName() + " feature outside of the " + ScriptLoader.getCurrentEventName() + " event.", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

}
