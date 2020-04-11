package com.mirre.random.elements.expressions;

import java.util.Arrays;
import java.util.Base64;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

@Pattern(patterns = {"{S}[base64] encode %string%", "{S}[base64] decode %string%"})
public class ExprBase64 extends RandomPropertyExpression<String, String> {

	private Base64.Encoder encoder;
	private Base64.Decoder decoder;

	public ExprBase64() {
		this.encoder = Base64.getEncoder();
		this.decoder = Base64.getDecoder();
	}

	@Override
	public String[] get(String from) {
		return new String[] {patternMark == 0 ? encoder.encodeToString(from.getBytes()) : Arrays.toString(decoder.decode(from))};
	}

}
