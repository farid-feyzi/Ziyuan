/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package learntest.activelearning.plugin.settings;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.runtime.CoreException;

import learntest.activelearning.plugin.utils.IStatusUtils;
import sav.eclipse.plugin.PluginException;

/**
 * @author LLT
 *
 */
public class LearntestLogger {

	public static void initLog4j(String projectName) throws PluginException {
		try {
			ResourceBundle log4j = ResourceBundle.getBundle("activetest_log4j");
			Properties props = new Properties();
			for (String key : log4j.keySet()) {
				props.setProperty(key, log4j.getString(key));
			}
			String logFilePath = getLearntestLogFile(projectName);
			System.out.println("log file: " + logFilePath);
			props.setProperty("log4j.appender.file.File", logFilePath);
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			throw PluginException.wrapEx(e);
		}
	}

	private static String getLearntestLogFile(String projectName) throws CoreException {
		String fileName = "learntest-eclipse.log";
		return getLogFile(projectName, fileName);
	}

	private static String getLogFile(String projectName, String fileName) throws CoreException {
		try {
			String outputFolder = ProjectSetting.getLearntestOutputFolder(projectName);
			File logFile = new File(outputFolder, fileName);
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			return logFile.getAbsolutePath();
		} catch (IOException e) {
			throw new CoreException(IStatusUtils.exception(e, e.getMessage()));
		}
	}
	
}
