/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package gentest.value.generator;

import gentest.data.statement.Rmethod;
import gentest.data.variable.GeneratedVariable;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sav.common.core.Pair;
import sav.common.core.SavException;
import sav.common.core.utils.Randomness;

/**
 * @author LLT
 * 
 */
public class ListValueGenerator extends SpecificValueGenerator {
	private Type type;

	public ListValueGenerator(Type type) {
		this(ArrayList.class, type);
	}
	
	protected ListValueGenerator(Class<?> implClazz, Type type) {
		super(implClazz, null);
		this.type = type;
	}

	public static boolean accept(Class<?> type) {
		return type == List.class;
	}

	@Override
	protected void doAppendMethod(GeneratedVariable variable, int level,
			int scopeId) throws SavException {
		try {
			Method method = getAddMethod();
			int elementNum = Randomness.nextRandomInt(10);
			Pair<Class<?>, Type> paramType = getContentType();
			for (int eleI = 0; eleI < elementNum; eleI++) {
				GeneratedVariable newVariable = variable.newVariable();
				ValueGenerator.append(newVariable, level + 2, paramType.a,
						paramType.b);
				Rmethod rmethod = new Rmethod(method, scopeId);
				variable.append(newVariable);
				variable.append(rmethod, new int[]{newVariable.getReturnVarId()}, false);
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"cannot find method add in ArrayList class");
		}
	}

	protected Method getAddMethod() throws NoSuchMethodException {
		return ArrayList.class.getMethod("add", Object.class);
	}

	private Pair<Class<?>, Type> getContentType() {
		if (type instanceof ParameterizedType) {
			Type compType = ((ParameterizedType) type).getActualTypeArguments()[0];
			if (compType instanceof Class<?>) {
				return new Pair<Class<?>, Type>((Class<?>) compType, null);
			}
		}
		return new Pair<Class<?>, Type>(Object.class, null);
	}
}