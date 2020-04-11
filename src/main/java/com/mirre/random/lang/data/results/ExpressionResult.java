package com.mirre.random.lang.data.results;

public class ExpressionResult extends FeatureResult {

	private boolean isSinglePattern;

	public ExpressionResult(String pattern) {
		super(pattern);
	}

	@Override
	protected void identify(String match) {
		isSinglePattern = isSingle(match);
	}

	private boolean isSingle(String match) {
		return match.equalsIgnoreCase("S") || !match.equalsIgnoreCase("M");
	}

	public boolean isSingle() {
		return isSinglePattern;
	}

	@Override
	protected void after() {}

}
