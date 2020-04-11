package com.mirre.random.elements.string;

import com.mirre.random.utils.others.HiddenStringUtils;
import org.bukkit.event.Event;
import com.mirre.random.lang.annonations.Pattern;
import com.mirre.random.lang.RandomCondition;

@Pattern(patterns = {"%string% has (hidden|encoded) string[s]", "{!}%string% doesn't have (hidden|encoded) string[s]"})
public class CondHasHiddenString extends RandomCondition {

	@Override
	public boolean inspect(Event event) {
		String s = expressions.getSingle(event, String.class, 0);
		return HiddenStringUtils.hasHiddenString(s);
	}

}
