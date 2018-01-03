/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package learntest.core.commons.data;

import java.util.ArrayList;
import java.util.List;

import learntest.core.commons.data.classinfo.MethodInfo;
import sav.common.core.utils.CollectionUtils;
import sav.common.core.utils.StringUtils;

/**
 * @author LLT
 *
 */
public class LineCoverage {
	private MethodInfo methodInfo;
	private List<Integer> coveredLineNums;
	private String testcase;
	private String branchCoverageText;
	private boolean testPass;

	public LineCoverage(MethodInfo methodInfo, String testcase) {
		this.methodInfo = methodInfo;
		this.testcase = testcase;
	}

	public void setCoveredLineNums(List<Integer> coveredLineNums) {
		this.coveredLineNums = coveredLineNums;
	}

	public MethodInfo getMethodInfo() {
		return methodInfo;
	}

	public String getTestcase() {
		return testcase;
	}

	public List<Integer> getCoveredLineNums() {
		return coveredLineNums;
	}
	
	public void setTestcase(String testcase) {
		this.testcase = testcase;
	}

	public void addCoveredLineNo(int line) {
		if (coveredLineNums == null) {
			coveredLineNums = new ArrayList<Integer>();
		}
		CollectionUtils.addIfNotNullNotExist(coveredLineNums, line);
	}
	
	public String getBranchCoverageText() {
		return branchCoverageText;
	}

	public void setBranchCoverageText(String branchCoverageText) {
		this.branchCoverageText = branchCoverageText;
	}

	public String getDisplayText() {
		StringBuilder sb = new StringBuilder();
		sb.append("Testcase: ").append(testcase).append("\n")
			.append("Target method: ").append(methodInfo.getMethodFullName()).append("\n")
			.append("Covered lines: ").append(StringUtils.join(coveredLineNums, ", "));
		return sb.toString();
	}
	
	public boolean isTestPass() {
		return testPass;
	}

	public void setTestPass(boolean testPass) {
		this.testPass = testPass;
	}

	@Override
	public String toString() {
		return getDisplayText();
	}
}
