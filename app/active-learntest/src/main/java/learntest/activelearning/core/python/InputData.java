package learntest.activelearning.core.python;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import learntest.activelearning.core.model.TestInputData;
import microbat.instrumentation.cfgcoverage.graph.Branch;
import sav.strategies.vm.interprocess.InputDataWriter.IInputData;

/**
 * @author LLT
 *
 */
public class InputData implements IInputData {
	private Logger log = LoggerFactory.getLogger(InputData.class);
	private RequestType requestType;
	private JSONObject obj = new JSONObject();

	@Override
	public void writeData(PrintWriter pw) {
		if (obj == null) {
			return;
		}
		log.debug("write data: {}, {}", requestType, obj);
		pw.println(String.valueOf(requestType));
		pw.println(obj);
	}
	
	public static IInputData forBoundaryRemaining(Dataset pathCoverage) {
		InputData inputData = new InputData();
		inputData.requestType = RequestType.BOUNDARY_REMAINING;
		inputData.obj.put(JsLabels.PATH_ID, pathCoverage.getId());
		inputData.obj.put(JsLabels.COVERED_DATA_POINTS, pathCoverage.getCoveredData());
		inputData.obj.put(JsLabels.UNCOVERED_DATA_POINTS, pathCoverage.getUncoveredData());
		return inputData;
	}

	public static IInputData createStartMethodRequest(String methodId) {
		InputData inputData = new InputData();
		inputData.requestType = RequestType.START_TRAINING_FOR_METHOD;
		inputData.obj.put(JsLabels.METHOD_ID, methodId);
		return inputData;
	}

	public static IInputData createBranchRequest(Branch branch) {
		InputData inputData = new InputData();
		inputData.requestType = RequestType.START_TRAINING_FOR_METHOD;
		inputData.obj.put(JsLabels.BRANCH_ID, branch.getBranchID());
		return inputData;
	}
	
	public static IInputData createTrainingRequest(Branch branch, List<TestInputData> positiveData, 
			List<TestInputData> negativeData) {
		InputData inputData = new InputData();
		inputData.requestType = RequestType.START_TRAINING_FOR_METHOD;
		inputData.obj.put(JsLabels.BRANCH_ID, branch.getBranchID());
		inputData.obj.put(JsLabels.POSITIVE_DATA, positiveData);
		inputData.obj.put(JsLabels.NEGATIVE_DATA, negativeData);
		
		return inputData;
	}

}
