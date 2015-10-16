/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package sav.java.parser.cfg;


/**
 * @author LLT
 *
 */
public class CfgEntryNode implements CfgNode {

	@Override
	public Type getType() {
		return Type.ENTRY;
	}

}
