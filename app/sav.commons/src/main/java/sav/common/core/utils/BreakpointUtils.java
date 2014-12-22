/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package sav.common.core.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sav.strategies.dto.BreakPoint;
import sav.strategies.dto.ClassLocation;

/**
 * @author LLT
 *
 */
public class BreakpointUtils {
	private BreakpointUtils(){}
	
	public static Map<String, List<BreakPoint>> initBrkpsMap(
			List<BreakPoint> brkps) {
		HashMap<String, List<BreakPoint>> brkpsMap = new HashMap<String, List<BreakPoint>>();
		for (BreakPoint brkp : brkps) {
			List<BreakPoint> bps = CollectionUtils.getListInitIfEmpty(brkpsMap,
					brkp.getClassCanonicalName());
			bps.add(brkp);
		}
		return brkpsMap;
	}
	
	public static <T extends ClassLocation>String getLocationId(T bkp) {
		return getLocationId(bkp.getClassCanonicalName(), bkp.getLineNo());
	}
	
	public static String getLocationId(String classPath, int lineNo) {
		return String.format("%s:%s", classPath.replace("/", "."), lineNo);
	}

	public static <T extends ClassLocation> List<String> toLocationIds(
			Collection<T> bkps) {
		List<String> locs = new ArrayList<String>(bkps.size());
		for (T bkp : bkps) {
			locs.add(getLocationId(bkp));
		}
		return locs;
	}

	public static String getPrintStr(Collection<BreakPoint> bkps) {
		return StringUtils.join(toLocationIds(bkps), ", ");
	}

	public static List<String> extractClasses(Set<BreakPoint> bkps) {
		Set<String> classNames = new HashSet<String>();
		for (BreakPoint bkp : bkps) {
			classNames.add(bkp.getClassCanonicalName());
		}
		return new ArrayList<String>(classNames);
	}
	
}