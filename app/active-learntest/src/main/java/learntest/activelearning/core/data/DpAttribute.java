package learntest.activelearning.core.data;

import sav.strategies.dto.execute.value.BooleanValue;
import sav.strategies.dto.execute.value.CharValue;
import sav.strategies.dto.execute.value.ExecValue;
import sav.strategies.dto.execute.value.IntegerValue;

public class DpAttribute {
	private ExecValue value;
	private boolean isPadding = false;
	private DpAttribute paddingCondition = null;
	private boolean isModifiable = true;
	
	public DpAttribute(ExecValue value) {
		this.value = value;
	}
	
	public DpAttribute(ExecValue value, boolean isPadding, DpAttribute paddingCondtion) {
		this.value = value;
		this.isPadding = isPadding;
		this.paddingCondition = paddingCondtion;
	}
	
	public DpAttribute(ExecValue value, boolean isPadding, DpAttribute paddingCondtion, boolean isModifiable) {
		this.value = value;
		this.isPadding = isPadding;
		this.paddingCondition = paddingCondtion;
		this.isModifiable = isModifiable;
	}
	
	public boolean isPadding() {
		return isPadding;
	}

	public void setPadding(boolean isPadding) {
		this.isPadding = isPadding;
	}

	public DpAttribute getPaddingCondition() {
		return paddingCondition;
	}

	public void setPaddingCondition(DpAttribute paddingCondition) {
		this.paddingCondition = paddingCondition;
	}

	public ExecValue getValue() {
		return value;
	}

	public void setValue(ExecValue value) {
		this.value = value;
	}

	public boolean isModifiable() {
		return isModifiable;
	}

	public void setModifiable(boolean isModifiable) {
		this.isModifiable = isModifiable;
	}

	public DpAttribute setBoolean(boolean newValue) {
		setValue(new BooleanValue(value.getVarId(), newValue));
		setPadding(false);
		return this;
	}

	public void setChar(char newValue) {
		setValue(new CharValue(value.getVarId(), newValue));
		setPadding(false);
	}

	public DpAttribute setInt(int newValue) {
		setValue(new IntegerValue(value.getVarId(), newValue));
		setPadding(false);
		return this;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}