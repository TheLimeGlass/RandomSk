package com.mirre.random.lang.annonations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ch.njol.skript.classes.Changer.ChangeMode;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChangeModes {

	ChangeMode[] changeModes();

	Class<?>[] acceptModes() default {Class.class};

}
