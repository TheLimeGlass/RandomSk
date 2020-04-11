package com.mirre.random.lang.data.results;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FeatureResult {

	private final static Pattern MATCH_VARIABLE = Pattern.compile("\\{([^\\}]+)\\}");

	public FeatureResult(String pattern) {
		init(pattern);
	}

	protected void init(String... patterns) {
		for (String pattern : patterns) {
			find(pattern);
		}
	}

	private void find(String pattern) {
		Matcher matcher = MATCH_VARIABLE.matcher(pattern);
		if (matcher.find()) {
			String variable = matcher.group(1);
			identify(variable);
		}
		after();
	}

	protected abstract void after();

	protected abstract void identify(String p0);

}
