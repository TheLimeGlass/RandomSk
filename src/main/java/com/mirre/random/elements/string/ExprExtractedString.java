package com.mirre.random.elements.string;

import com.mirre.random.utils.others.HiddenStringUtils;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}(extracted|revealed) %string%"})
public class ExprExtractedString extends RandomPropertyExpression<String, String> {

	@Override
	public String[] get(String from) {
		return new String[] { HiddenStringUtils.extractHiddenString(from) };
	}

}
