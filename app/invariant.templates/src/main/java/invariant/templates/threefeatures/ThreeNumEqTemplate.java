package invariant.templates.threefeatures;

import java.util.ArrayList;
import java.util.List;

import sav.common.core.formula.Eq;
import sav.common.core.formula.Var;
import sav.strategies.dto.execute.value.ExecValue;
import sav.strategies.dto.execute.value.ExecVar;
import sav.strategies.dto.execute.value.ExecVarType;

// Tempalte ax + by + cz = d

public class ThreeNumEqTemplate extends ThreeFeaturesTemplate {
	
	private double a = 0.0;
	
	private double b = 0.0;
	
	private double c = 0.0;
	
	private double d = 0.0;
	
	public ThreeNumEqTemplate(List<List<ExecValue>> passExecValuesList, List<List<ExecValue>> failExecValuesList) {
		super(passExecValuesList, failExecValuesList);
	}
	
	@Override
	public boolean checkPassValue(List<ExecValue> evl) {
		double v1 = evl.get(0).getDoubleVal();
		double v2 = evl.get(1).getDoubleVal();
		double v3 = evl.get(2).getDoubleVal();
		return a * v1 + b * v2 + c * v3 + d == 0;
	}
	
	@Override
	public boolean checkFailValue(List<ExecValue> evl) {
		double v1 = evl.get(0).getDoubleVal();
		double v2 = evl.get(1).getDoubleVal();
		double v3 = evl.get(2).getDoubleVal();
		return a * v1 + b * v2 + c * v3 + d != 0;
	}
	
	@Override
	public boolean check() {
		if (passValues.size() <= 3) return false;
		
		double x1 = passValues.get(0).get(0).getDoubleVal();
		double y1 = passValues.get(0).get(1).getDoubleVal();
		double z1 = passValues.get(0).get(2).getDoubleVal();
		
		double x2 = passValues.get(1).get(0).getDoubleVal();
		double y2 = passValues.get(1).get(1).getDoubleVal();
		double z2 = passValues.get(1).get(2).getDoubleVal();
		
		double x3 = passValues.get(2).get(0).getDoubleVal();
		double y3 = passValues.get(2).get(1).getDoubleVal();
		double z3 = passValues.get(2).get(2).getDoubleVal();
		
		double x12 = x2 - x1, y12 = y2 - y1, z12 = z2 - z1;
		double x13 = x3 - x1, y13 = y3 - y1, z13 = z3 - z1;
		
		a = y12 * z13 - z12 * y13;
		b = - (x12 * z13 - z12 * x13);
		c = x12 * y13 - y12 * x13;
		d = a * x1 + b * y1 + c * z1;
		
		return check(passValues, failValues);
	}

	@Override
	public List<List<Eq<?>>> sampling() {
		List<List<Eq<?>>> samples = new ArrayList<List<Eq<?>>>();
		
		ExecValue ev1 = passValues.get(0).get(0);
		Var v1 = new ExecVar(ev1.getVarId(), ev1.getType());
		
		ExecValue ev2 = passValues.get(0).get(1);
		Var v2 = new ExecVar(ev2.getVarId(), ev2.getType());
		
		ExecValue ev3 = passValues.get(0).get(2);
		Var v3 = new ExecVar(ev3.getVarId(), ev3.getType());
		
		List<Eq<?>> sample1 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample1.add(new Eq<Number>(v1, (int) (d / a)));
			sample1.add(new Eq<Number>(v2, 0));
			sample1.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample1.add(new Eq<Number>(v1, (long) (d / a)));
			sample1.add(new Eq<Number>(v2, 0L));
			sample1.add(new Eq<Number>(v3, 0L));
		} else {
			sample1.add(new Eq<Number>(v1, (d / a)));
			sample1.add(new Eq<Number>(v2, 0.0));
			sample1.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample2 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample2.add(new Eq<Number>(v1, (int) (d / a) - 1));
			sample2.add(new Eq<Number>(v2, 0));
			sample2.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample2.add(new Eq<Number>(v1, (long) (d / a) - 1));
			sample2.add(new Eq<Number>(v2, 0L));
			sample2.add(new Eq<Number>(v3, 0L));
		} else {
			sample2.add(new Eq<Number>(v1, (d / a) - 1));
			sample2.add(new Eq<Number>(v2, 0.0));
			sample2.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample3 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample3.add(new Eq<Number>(v1, (int) (d / a) + 1));
			sample3.add(new Eq<Number>(v2, 0));
			sample3.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample3.add(new Eq<Number>(v1, (long) (d / a) + 1));
			sample3.add(new Eq<Number>(v2, 0L));
			sample3.add(new Eq<Number>(v3, 0L));
		} else {
			sample3.add(new Eq<Number>(v1, (d / a) + 1));
			sample3.add(new Eq<Number>(v2, 0.0));
			sample3.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample4 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample4.add(new Eq<Number>(v1, 0));
			sample4.add(new Eq<Number>(v2, (int) (d / b)));
			sample4.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample4.add(new Eq<Number>(v1, 0L));
			sample4.add(new Eq<Number>(v2, (long) (d / b)));
			sample4.add(new Eq<Number>(v3, 0L));
		} else {
			sample4.add(new Eq<Number>(v1, 0.0));
			sample4.add(new Eq<Number>(v2, (d / b)));
			sample4.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample5 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample5.add(new Eq<Number>(v1, 0));
			sample5.add(new Eq<Number>(v2, (int) (d / b) - 1));
			sample5.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample5.add(new Eq<Number>(v1, 0L));
			sample5.add(new Eq<Number>(v2, (long) (d / b) - 1));
			sample5.add(new Eq<Number>(v3, 0L));
		} else {
			sample5.add(new Eq<Number>(v1, 0.0));
			sample5.add(new Eq<Number>(v2, (d / b) - 1));
			sample5.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample6 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample6.add(new Eq<Number>(v1, 0));
			sample6.add(new Eq<Number>(v2, (int) (d / b) + 1));
			sample6.add(new Eq<Number>(v3, 0));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample6.add(new Eq<Number>(v1, 0L));
			sample6.add(new Eq<Number>(v2, (long) (d / b) + 1));
			sample6.add(new Eq<Number>(v3, 0L));
		} else {
			sample6.add(new Eq<Number>(v1, 0.0));
			sample6.add(new Eq<Number>(v2, (d / b) + 1));
			sample6.add(new Eq<Number>(v3, 0.0));
		}
		
		List<Eq<?>> sample7 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample7.add(new Eq<Number>(v1, 0));
			sample7.add(new Eq<Number>(v2, 0));
			sample7.add(new Eq<Number>(v3, (int) (d / c)));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample7.add(new Eq<Number>(v1, 0L));
			sample7.add(new Eq<Number>(v2, 0L));
			sample7.add(new Eq<Number>(v3, (long) (d / c)));
		} else {
			sample7.add(new Eq<Number>(v1, 0.0));
			sample7.add(new Eq<Number>(v2, 0.0));
			sample7.add(new Eq<Number>(v3, (d / c)));
		}
		
		List<Eq<?>> sample8 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample8.add(new Eq<Number>(v1, 0));
			sample8.add(new Eq<Number>(v2, 0));
			sample8.add(new Eq<Number>(v3, (int) (d / c) - 1));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample8.add(new Eq<Number>(v1, 0L));
			sample8.add(new Eq<Number>(v2, 0L));
			sample8.add(new Eq<Number>(v3, (long) (d / c) - 1));
		} else {
			sample8.add(new Eq<Number>(v1, 0.0));
			sample8.add(new Eq<Number>(v2, 0.0));
			sample8.add(new Eq<Number>(v3, (d / c) - 1));
		}
		
		List<Eq<?>> sample9 = new ArrayList<Eq<?>>();
		if (ev1.getType() == ExecVarType.INTEGER) {
			sample9.add(new Eq<Number>(v1, 0));
			sample9.add(new Eq<Number>(v2, 0));
			sample9.add(new Eq<Number>(v3, (int) (d / c) + 1));
		} else if (ev1.getType() == ExecVarType.LONG) {
			sample9.add(new Eq<Number>(v1, 0L));
			sample9.add(new Eq<Number>(v2, 0L));
			sample9.add(new Eq<Number>(v3, (long) (d / c) + 1));
		} else {
			sample9.add(new Eq<Number>(v1, 0.0));
			sample9.add(new Eq<Number>(v2, 0.0));
			sample9.add(new Eq<Number>(v3, (d / c) + 1));
		}
		
		samples.add(sample1);
		samples.add(sample2);
		samples.add(sample3);
		samples.add(sample4);
		samples.add(sample5);
		samples.add(sample6);
		samples.add(sample7);
		samples.add(sample8);
		samples.add(sample9);
		
		return samples;
	}
	
	@Override
	public String toString() {
		return a + "*" + passValues.get(0).get(0).getVarId() + " + " +
				b + "*" + passValues.get(0).get(1).getVarId() + " + " +
				c + "*" + passValues.get(0).get(2).getVarId() + " = " + d;
	}
	
}
