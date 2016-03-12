package invariant.templates;

import java.util.ArrayList;
import java.util.List;

import invariant.templates.onefeature.OneNumIlpTemplate;
import invariant.templates.threefeatures.ThreeNumIlpTemplate;
import invariant.templates.twofeatures.TwoNumIlpTemplate;
import sav.strategies.dto.execute.value.ExecValue;

public class DisjunctionTemplate extends CompositeTemplate {
	
	public DisjunctionTemplate() {
		super();
	}
	
	@Override
	public boolean check() {
		List<Integer> notSatisfiedIndex = new ArrayList<Integer>();
		SingleTemplate t1 = (SingleTemplate) templates.get(0);
		SingleTemplate t2 = (SingleTemplate) templates.get(1);
		
		List<List<ExecValue>> passExecValuesList1 = t1.getPassExecValuesList();
		
		for (int k = 0; k < passExecValuesList1.size(); k++) {
			if (!t1.checkPassValue(passExecValuesList1.get(k))) {
				notSatisfiedIndex.add(k);
			}
		}
		
		List<List<ExecValue>> passExecValuesList2 = t2.getPassExecValuesList();
		
		if (t2 instanceof OneNumIlpTemplate ||
				t2 instanceof TwoNumIlpTemplate ||
				t2 instanceof ThreeNumIlpTemplate) {
			List<List<ExecValue>> newPassExecValuesList2 = new ArrayList<List<ExecValue>>();
			for (int i = 0; i < passExecValuesList2.size(); i++) {
				if (notSatisfiedIndex.contains(i)) {
					newPassExecValuesList2.add(passExecValuesList2.get(i));
				}
			}
			
			return t2.check(newPassExecValuesList2, t2.getFailExecValuesList());
		} else {
			for (int k : notSatisfiedIndex) {
				if (!t2.checkPassValue(passExecValuesList2.get(k))) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		s += templates.get(0);
		for (int i = 1; i < templates.size(); i++) {
			s += " || " + templates.get(i);
		}
		
		return s;
	}

}
