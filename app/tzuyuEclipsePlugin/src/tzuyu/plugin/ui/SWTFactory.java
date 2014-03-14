/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package tzuyu.plugin.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import tzuyu.engine.utils.Assert;
import tzuyu.plugin.TzuyuPlugin;
import tzuyu.plugin.preferences.ParamDeclarationFormat;

/**
 * @author LLT
 * 
 */
public class SWTFactory {
	/**
	 * Creates a horizontal separator for a grid layout
	 */
	public static void createHorizontalSeperator(Composite parent, int colSpan) {
		Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalSpan(label, colSpan);
	}
	
	/**
	 * creates a horizontal spacer for separating components
	 */
	public static void createHorizontalSpacer(Composite comp, int numlines) {
		Label lbl = new Label(comp, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = numlines;
		lbl.setLayoutData(gd);
	}
	
	public static void horizontalSpan(Control comp, int cols) {
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = cols;
        comp.setLayoutData(gridData);
	}
	
	public static Label createLabel(Composite parent, String text, int colSpan) {
		Label label = createLabel(parent, text);
		horizontalSpan(label, colSpan);
		return label; 
	}
	
	public static Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		return label; 
	}
	
	public static Button createCheckbox(Composite parent, String text, int colSpan) {
		Button btn = createCheckbox(parent, text);
		horizontalSpan(btn, colSpan);
		return btn;
	}

	public static Button createCheckbox(Composite parent, String text) {
		Button btn = new Button(parent, SWT.CHECK);
		btn.setText(text);
		return btn;
	}
	
	public static Group createGroup(Composite parent, String text, int colSpan) {
		Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		group.setText(text);
		group.setLayout(new GridLayout(1, true));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = colSpan;
		gridData.horizontalIndent = 3; 
		group.setLayoutData(gridData);
		return group;
	}

	public static Combo creatDropdown(Composite parent) {
		Combo comb = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		return comb;
	}

	public static <T extends Enum<?>>Combo creatDropdown(Composite parent,
			T[] values) {
		Combo comb = creatDropdown(parent);
		Assert.assertNotNull(values, "Values to creat dropdown can not be null!!");
		for (T val : values) { 
			comb.add(TzuyuPlugin.getMessages().getMessage(val));
		}
		return comb;
	}
}
