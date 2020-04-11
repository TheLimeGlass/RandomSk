package com.mirre.random.lang.annonations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Warning {

	Reason reason();

	String data() default "";

	public enum Reason {
		INCOMPLETE("INCOMPLETE", 0, "This feature is incomplete. Use at your own risk. Disable these messages in RandomSK config."), 
		BUGGY("BUGGY", 1, "This feature is buggy. Use at your own risk. Disable these messages in RandomSK config."), 
		DEPRECATED("DEPRECATED", 2, "This feature is deprecated. Use {data} instead. Disable these messages in RandomSK config.");

		private final String reason;

		private Reason(String name, int ordinal, String reason) {
			this.reason = reason;
		}

		public String getReason(String data) {
			return reason.contains("{data}") ? reason.replace("{data}", data) : reason;
		}

	}

}
