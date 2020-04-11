package com.mirre.random.lang.data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import com.mirre.random.RandomSk;
import com.mirre.random.lang.abstracts.AbstractFeature;
import com.mirre.random.lang.annonations.ChangeModes;
import com.mirre.random.lang.annonations.ExpressionsType;
import com.mirre.random.lang.expressions.RandomPropertyExpression;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;

public class ExpressionFeature<T> extends AbstractFeature<Expression<T>> {

	private ChangeMode[] changeModes;
	private Class<?>[] acceptModes;
	private boolean isProperty;
	private Class<T> returnType;
	private ExpressionType type;

	public ExpressionFeature(Class<? extends Expression<T>> feature) {
		super(feature);
		this.changeModes = null;
		this.acceptModes = null;
		this.returnType = null;
		this.type = null;
		this.isProperty = RandomPropertyExpression.class.isAssignableFrom(feature);
		if (this.isProperty)
			this.type = ExpressionType.PROPERTY;
	}

	public ExpressionType getType() {
		if (clazz.isAnnotationPresent(ExpressionsType.class)) {
			ExpressionsType p = clazz.getDeclaredAnnotation(ExpressionsType.class);
			return p.type();
		}
		return type == null ? ExpressionType.SIMPLE : type;
	}

	public ChangeMode[] getChangeModes() {
		if (changeModes == null && clazz.isAnnotationPresent(ChangeModes.class)) {
			ChangeModes p = clazz.getDeclaredAnnotation(ChangeModes.class);
			changeModes = p.changeModes();
		}
		return this.changeModes;
	}

	public Class<?>[] getAcceptModes() {
		if (acceptModes == null && clazz.isAnnotationPresent(ChangeModes.class)) {
			ChangeModes p = clazz.getDeclaredAnnotation(ChangeModes.class);
			acceptModes = ((p.acceptModes().length == 1 && p.acceptModes()[0] == Class.class) ? new Class[] {getReturnType()} : p.acceptModes());
		}
		return this.acceptModes;
	}

	public Type[] getTypeArgument() {
		return ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments();
	}

	@SuppressWarnings("unchecked")
	public Class<T> getReturnType() {
		if (returnType == null) {
			Type[] types = ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments();
			returnType = (Class<T>)(isProperty ? types[1] : types[0]);
		}
		return returnType;
	}

	@Override
	public void register() {
		if (isSupported()) {
			if (RandomSk.getInstance().getConfig().getBoolean("debug", false))
				System.out.println("Registering: " + clazz.getSimpleName() + " Patterns: " + Arrays.toString(getExtractedPatterns()));
			Skript.registerExpression(clazz, getReturnType(), getType(), getExtractedPatterns());
		}
	}

}
