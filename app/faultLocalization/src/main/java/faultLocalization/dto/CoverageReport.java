/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package faultLocalization.dto;

import faultLocalization.dto.LineCoverageInfo.LineCoverageInfoComparator;
import icsetlv.common.dto.BreakPoint;
import icsetlv.common.dto.ClassLocation;
import icsetlv.common.utils.BreakpointUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author khanh
 *
 */
public class CoverageReport {
	private Map<String, ClassCoverageInAllTestcases> mapClassLineToTestCasesCover = new HashMap<String, ClassCoverageInAllTestcases>();
	private Map<Integer, TestcaseCoverageInfo> passedTestcaseCoverageInfo = new HashMap<Integer, TestcaseCoverageInfo>();
	private Map<Integer, TestcaseCoverageInfo> failedTestcaseCoverageInfo = new HashMap<Integer, TestcaseCoverageInfo>();
	
	private List<BreakPoint> failureTraces;
	
	private List<String> testingClassNames;
	
	
	public CoverageReport(List<String> testingClassNames){
		this.testingClassNames = testingClassNames;
		failureTraces = new ArrayList<BreakPoint>();
	}
	
	public void addInfo(int testcaseIndex, String className, int lineIndex, boolean isPassed, boolean isCovered){
		//update mapClassLineToTestCasesCover
		if (isCovered) {
			ClassCoverageInAllTestcases classCoverage;
			if (mapClassLineToTestCasesCover.containsKey(className)) {
				classCoverage = mapClassLineToTestCasesCover.get(className);
			} else {
				classCoverage = new ClassCoverageInAllTestcases(className);
				mapClassLineToTestCasesCover.put(className, classCoverage);
			}

			classCoverage.addInfo(lineIndex, testcaseIndex, isPassed);
		}
		
		// update passedTestcaseCoverageInfo, failedTestcaseCoverageInfo
		Map<Integer, TestcaseCoverageInfo> allTestcasesCoverageInfo = (isPassed) ? passedTestcaseCoverageInfo
				: failedTestcaseCoverageInfo;
		TestcaseCoverageInfo testcaseCoverage;
		if(allTestcasesCoverageInfo.containsKey(testcaseIndex)){
			testcaseCoverage = allTestcasesCoverageInfo.get(testcaseIndex);
		}else{
			testcaseCoverage = new TestcaseCoverageInfo(testcaseIndex);
			allTestcasesCoverageInfo.put(testcaseIndex, testcaseCoverage);
		}
		testcaseCoverage.addInfo(className, lineIndex, isCovered);
	}
	
	public List<LineCoverageInfo> Tarantula(){
		return Tarantula(new ArrayList<ClassLocation>());
	}
	
	
	/**
	 * @param failedTestcaseIndex
	 * @return return the passed testcase with the spectrum which is nearest compared with the one of the failed testcase
	 */
	public int getNearestPassedTestcase(int failedTestcaseIndex)
	{
		HashMap<Integer, Integer> mapPassedTest2Difference = new HashMap<Integer, Integer>();
		//init value
		for(Integer passedTest: passedTestcaseCoverageInfo.keySet())
		{
			mapPassedTest2Difference.put(passedTest, 0);
		}
		
		for(Entry<String, ClassCoverageInAllTestcases> entry: mapClassLineToTestCasesCover.entrySet()){
			String tempClassName = entry.getKey().replace('/', '.');
			if(this.testingClassNames.contains(tempClassName))
			{
				ClassCoverageInAllTestcases classCoverage = entry.getValue();
				classCoverage.updateDifference(mapPassedTest2Difference, failedTestcaseIndex);
			}
		}
		
		int minDifference = Integer.MAX_VALUE;
		int nearestPassedIndex = -1;
		
		for(Entry<Integer, Integer> entry : mapPassedTest2Difference.entrySet()){
			Integer difference = entry.getValue();
			
			if(difference > 0 &&  difference < minDifference){
				minDifference = entry.getKey();
				nearestPassedIndex = entry.getKey();
			}
		}
		
		System.out.println("nearest " + nearestPassedIndex);
		return nearestPassedIndex;
	}
	
	public void Test(){
		for(Integer failTest: failedTestcaseCoverageInfo.keySet()){
			getNearestPassedTestcase(failTest);
		}
	}
	
	public void addFailureTrace(List<BreakPoint> traces) {
		failureTraces.addAll(traces);
	}
	
	public List<BreakPoint> getFailureTraces() {
		return failureTraces;
	}

	public <T extends ClassLocation>List<LineCoverageInfo> Tarantula(List<T> filteredPoints) {
		List<LineCoverageInfo> linesCoverageInfo = new ArrayList<LineCoverageInfo>();

		List<String> pointLocIds = BreakpointUtils.toLocationIds(filteredPoints);
		for (ClassCoverageInAllTestcases classCoverage : mapClassLineToTestCasesCover
				.values()) {
			if (pointLocIds.isEmpty()) {
				linesCoverageInfo.addAll(classCoverage.getLineCoverageInfo());
				continue;
			}
			for (LineCoverageInfo lineInfo : classCoverage.getLineCoverageInfo()) {
				if (pointLocIds.contains(lineInfo.getLocId())) {
					linesCoverageInfo.add(lineInfo);
				}
			}
		}

		// use slicing to remove unrelated lines in linesCoverageInfo
		// failed test -> where failed assertion -> line -> slicing
		//
		for (LineCoverageInfo lineCoverageInfo : linesCoverageInfo) {
			lineCoverageInfo.computeSuspiciousness(
					passedTestcaseCoverageInfo.size(),
					failedTestcaseCoverageInfo.size());
		}

		Collections.sort(linesCoverageInfo, new LineCoverageInfoComparator());

		for (LineCoverageInfo lineCoverageInfo : linesCoverageInfo) {
			System.out.println(lineCoverageInfo.toString());
		}
		return linesCoverageInfo;
	}
}
