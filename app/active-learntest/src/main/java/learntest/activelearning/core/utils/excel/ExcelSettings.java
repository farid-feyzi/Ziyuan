/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package learntest.activelearning.core.utils.excel;

/**
 * @author LLT
 *
 */
public class ExcelSettings {
	public static final String EXCEL_EXT_WITH_DOT = ".xlsx";
	public static final int FIRST_FILE_IDX = 0;
	public static final String FILE_IDX_START_CH = "_";
	public static final int DATA_SHEET_HEADER_ROW_IDX = 0;
	public static final int DEFAULT_HEADER_ROW_IDX = 0;
	public static int DEFAULT_MAX_ROW_PER_SHEET = 3000;
	
	public static String DATA_SHEET_NAME = "data";
	public static final String METHOD_ID_SEPARATOR = "_";
	public static final boolean DEFAULT_EXCEL_APPEND = true;
	
	
	private int maxRowPerSheet = DEFAULT_MAX_ROW_PER_SHEET;
	private String excelRootFolder;
	private String excelFilePrefix;
	private boolean appendLastFile;

	public ExcelSettings(String rootFolder, String filePrefix) {
		this.excelRootFolder = rootFolder;
		this.excelFilePrefix = filePrefix;
	}

	public ExcelSettings() {
	}

	public String getExcelRootFolder() {
		return excelRootFolder;
	}

	public void setExcelRootFolder(String excelRootFolder) {
		this.excelRootFolder = excelRootFolder;
	}

	public boolean isAppendLastFile() {
		return appendLastFile;
	}

	public void setAppendLastFile(boolean appendLastFile) {
		this.appendLastFile = appendLastFile;
	}

	public String getExcelFilePrefix() {
		return excelFilePrefix;
	}

	public void setExcelFilePrefix(String excelFilePrefix) {
		this.excelFilePrefix = excelFilePrefix;
	}

	public int getMaxRowPerSheet() {
		return maxRowPerSheet;
	}

	public void setMaxRowPerSheet(int maxRowPerSheet) {
		this.maxRowPerSheet = maxRowPerSheet;
	}

}
