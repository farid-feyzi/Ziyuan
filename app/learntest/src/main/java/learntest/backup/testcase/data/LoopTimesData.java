package learntest.backup.testcase.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cfgcoverage.jacoco.analysis.data.NodeCoverage;
import icsetlv.common.dto.BreakpointValue;
import learntest.backup.breakpoint.data.DecisionLocation;
import libsvm.core.Category;
import libsvm.core.Machine.DataPoint;

public class LoopTimesData extends BreakpointData {
	
	public LoopTimesData(DecisionLocation location) {
		super(location);
		oneTimeValues = new ArrayList<BreakpointValue>();
		moreTimesValues = new ArrayList<BreakpointValue>();
	}
	
	public void addOneTimeValue(BreakpointValue bkpValue) {
		oneTimeValues.add(bkpValue);
		trueValues.add(bkpValue);
	}
	
	public void addMoreTimesValue(BreakpointValue bkpValue) {
		moreTimesValues.add(bkpValue);
		trueValues.add(bkpValue);
	}
	
	public List<BreakpointValue> getOneTimeValues() {
		return oneTimeValues;
	}
	
	public List<BreakpointValue> getMoreTimesValues() {
		return moreTimesValues;
	}	
	
	@Override
	public boolean merge(BreakpointData bkpData) {
		if(super.merge(bkpData)){
			if(bkpData instanceof LoopTimesData) {
				LoopTimesData loopData = (LoopTimesData) bkpData;
				oneTimeValues.addAll(loopData.getOneTimeValues());
				moreTimesValues.addAll(loopData.getMoreTimesValues());
			}
			return true;
		}
		return false;
	}

	public List<DataPoint> toOneMoreDatapoints(List<String> labels) {
		Set<DataPoint> datapoints = new HashSet<DataPoint>();
		for (BreakpointValue bValue : moreTimesValues) {
			datapoints.add(toDataPoint(labels, bValue, Category.POSITIVE));
		}

		for (BreakpointValue bValue : oneTimeValues) {
			datapoints.add(toDataPoint(labels, bValue, Category.NEGATIVE));
		}
		return new ArrayList<DataPoint>(datapoints);
	}
	
	@Override
	public String toString() {
		return "LoopTimesData (" + location + "), \nfalseValues=" + falseValues
				+ ", \ntrueValues=" + trueValues + ", \n\toneTimeValues="
				+ oneTimeValues + ", \n\tmoreTimesValues=" + moreTimesValues + "]\n";
	}

	/* (non-Javadoc)
	 * @see learntest.testcase.data.INodeCoveredData#update(cfgcoverage.jacoco.analysis.data.NodeCoverage, int, java.util.List)
	 */
	@Override
	public void update(NodeCoverage coverage, int samplesFirstIdx, List<BreakpointValue> sampleTestInputs) {
		// TODO Auto-generated method stub
		
	}

}