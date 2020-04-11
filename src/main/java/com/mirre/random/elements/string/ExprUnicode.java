package com.mirre.random.elements.string;

import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}unicode %number%"})
public class ExprUnicode extends RandomPropertyExpression<Number, String> {

	@Override
	public String[] get(Number from) {
		try {
			char ccc = (char)Integer.parseInt(String.valueOf(from.intValue()), 16);
			String text = String.valueOf(ccc);
			return new String[] {text};
		} catch (Exception e) {
			return null;
		}
	}

}
