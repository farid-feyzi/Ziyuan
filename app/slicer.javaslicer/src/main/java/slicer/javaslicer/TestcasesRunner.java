/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package slicer.javaslicer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import sav.common.core.utils.JunitUtils;
import sav.strategies.junit.JunitRunner;

/**
 * @author LLT
 * see {@link JunitRunner}
 */
@Deprecated
public class TestcasesRunner {

	/**
	 * args: essential data to execute the testcases. arg[0]: all simpleNames of
	 * test classes.
	 */
	public static void main(String[] args) {
		List<Class<?>> testClasses = extractTestClasses(args[0]);
		/*
		 * for each test method found in test class, execute it, and ignore
		 * exeption.
		 */
		for (Class<?> testClass : testClasses) {
			List<Method> testMethods = new ArrayList<Method>();
			System.out.println("scan: " + testClass.getCanonicalName());
			for (Method method : testClass.getMethods()) {
				if (JunitUtils.isTestMethod(testClass, method)) {
					testMethods.add(method);
					System.out.println("found: " + method.getName());
				}
			}
			// run test method
			if (!testMethods.isEmpty()) {
				Object instance;
				try {
					instance = testClass.getConstructor().newInstance();
					for (Method method : testMethods) {
						try {
							System.out.println("run: " + method.getName());
							method.invoke(instance);
							System.out.println("Success!");
						} catch (Throwable e) {
							System.out.println("Failure!");
							 e.printStackTrace();
							// ignore all classes cannot run as expected
						}
					}
				} catch (Throwable ex) {
					System.out.println(String.format(
							"cannot init %s due to error: %s",
							testClass.getName(), ex));
				}
			}
		}

	}

	private static List<Class<?>> extractTestClasses(String tests) {
		String[] clazzNames = tests.split(";");
		List<Class<?>> result = new ArrayList<Class<?>>(clazzNames.length);
		for (String clazzName : clazzNames) {
			try {
				result.add(Class.forName(clazzName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String toArgs(List<String> tests) {
		String result = null;
		for (String test : tests) {
			if (result == null) {
				result = test;
			} else {
				result = result + ";" + test;
			}
		}
		return result;
	}
}