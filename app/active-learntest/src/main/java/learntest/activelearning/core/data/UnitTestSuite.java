package learntest.activelearning.core.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gentest.core.data.Sequence;
import microbat.instrumentation.cfgcoverage.graph.CoverageSFlowGraph;
import sav.common.core.utils.JunitUtils;
import sav.strategies.dto.execute.value.ExecVar;

/**
 * @author LLT
 *
 */
public class UnitTestSuite {
	private List<String> junitClassNames;
	private String mainClass;
	private List<File> junitfiles;
	private List<String> junitTestcases;
	private Map<String, TestInputData> inputDataMap; //inputData.get(i) might be null in case target method isn't even reached. 
	private List<ExecVar> inputVars;
	private Map<String, Sequence> testcaseSequenceMap;
	private CoverageSFlowGraph coverageGraph;
	private LearnDataSetMapper learnDataMapper;
	
	public void setInputData(List<TestInputData> inputData) {
		this.inputDataMap = new HashMap<>();
		for (TestInputData testInput : inputData) {
			if (testInput != null) {
				inputDataMap.put(testInput.getTestcase(), testInput);
			}
		}
	}
	
	public Map<String, TestInputData> getInputData() {
		return inputDataMap;
	}

	public Map<String, Sequence> getTestcaseSequenceMap() {
		return testcaseSequenceMap;
	}

	public void setTestcaseSequenceMap(Map<String, Sequence> testcaseSequenceMap) {
		this.testcaseSequenceMap = testcaseSequenceMap;
	}
	
	public void setCoverageGraph(CoverageSFlowGraph coverageGraph) {
		this.coverageGraph = coverageGraph;
	}
	
	public CoverageSFlowGraph getCoverageGraph() {
		return coverageGraph;
	}
	
	public List<String> getJunitClassNames() {
		return junitClassNames;
	}
	
	public void setJunitTestcases(List<String> junitTestcases) {
		this.junitTestcases = junitTestcases;
	}
	
	public void setJunitClassNames(List<String> junitClasses) {
		this.junitClassNames = junitClasses;
	}

	public void setJunitClassNames(List<String> junitClasses, ClassLoader classLoader) {
		this.junitClassNames = junitClasses;
		this.junitTestcases = JunitUtils.extractTestMethods(junitClasses, classLoader);
	}

	public List<File> getJunitfiles() {
		return junitfiles;
	}

	public void setJunitfiles(List<File> junitfiles) {
		this.junitfiles = junitfiles;
	}
	
	public List<String> getJunitTestcases() {
		return junitTestcases;
	}
	
	public List<ExecVar> getInputVars() {
		return inputVars;
	}

	public void addTestCases(UnitTestSuite newTestSuite) {
		this.junitClassNames.addAll(newTestSuite.getJunitClassNames());
		this.junitfiles.addAll(newTestSuite.junitfiles);
		this.junitTestcases.addAll(newTestSuite.junitTestcases);
		this.inputDataMap.putAll(newTestSuite.inputDataMap);
		if (this.testcaseSequenceMap == null) {
			this.testcaseSequenceMap = newTestSuite.testcaseSequenceMap;
		} else if (newTestSuite.testcaseSequenceMap != null) {
			this.testcaseSequenceMap.putAll(newTestSuite.testcaseSequenceMap);
		}
		this.coverageGraph.addCoverageInfo(newTestSuite.coverageGraph);
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
	public void setLearnDataMapper(LearnDataSetMapper learnDataMapper) {
		this.learnDataMapper = learnDataMapper;
		for (TestInputData testInput : inputDataMap.values()) {
			testInput.setDataMapper(learnDataMapper);
		}
	}
	
	public LearnDataSetMapper getLearnDataMapper() {
		return learnDataMapper;
	}
}