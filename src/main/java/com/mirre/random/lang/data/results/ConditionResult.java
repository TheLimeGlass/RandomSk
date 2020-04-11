package com.mirre.random.lang.data.results;

public class ConditionResult extends FeatureResult {

	private boolean patternIsInverted;

	public ConditionResult(String pattern) {
		super(pattern);
	}

	@Override
	protected void identify(String match) {
		patternIsInverted = isInverted(match);
	}

	private Boolean isInverted(String match) {
		return match.equalsIgnoreCase("!");
	}

	public boolean isInverted() {
		return patternIsInverted;
	}

	@Override
	protected void after() {}

}
