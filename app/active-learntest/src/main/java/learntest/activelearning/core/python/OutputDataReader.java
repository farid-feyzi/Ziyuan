/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package learntest.activelearning.core.python;

import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sav.common.core.utils.SingleTimer;
import sav.settings.SAVExecutionTimeOutException;
import sav.strategies.vm.interprocess.ServerOutputReader;

/**
 * @author LLT
 *
 */
public class OutputDataReader extends ServerOutputReader {
	private static Logger log = LoggerFactory.getLogger(OutputDataReader.class);
	private volatile OutputData readOutput;
	private RequestType requestType;

	public OutputDataReader() {
		waiting();
	}

	public boolean isMatched(String line) {
		try {
			log.debug("read data: {}", line);
			requestType = RequestType.valueOf(line);
			return true;
		} catch (Exception ex) {
			// do nothing
		}
		return false;
	}

	@Override
	protected void readData(BufferedReader br) {
		switch (requestType) {
		case $BOUNDARY_REMAINING:
			readOutput = OutputData.boundaryRemainingOuput(br);
			ready();
			break;
		case $REQUEST_LABEL:
			VariableValue v = OutputData.requestLabelOuput(br);
			ready();
			break;
		default:
			break;
		}
	}

	@Override
	public void open() {
		readOutput = null;
		waiting();
	}
	
	public OutputData readOutput() {
		return readOutput(-1);
	}

	public OutputData readOutput(long timeout) {
		SingleTimer timer = SingleTimer.start("read output");
		if (timeout > 0) {
			try {
				waitingWithTimeout(timer, timeout);
			} catch (SAVExecutionTimeOutException e) {
				log.debug("Timeout!");
				return null;
			}
		} else {
			while (isWaiting()) {
				// do nothing
			}
		}
		OutputData result = readOutput;
		readOutput = null;
		waiting();
		return result;
	}

	private void waitingWithTimeout(SingleTimer timer, long timeout) throws SAVExecutionTimeOutException {
		while (isWaiting()) {
			if (timer.getExecutionTime() > timeout) {
				System.out.println("timeout!");
				throw new SAVExecutionTimeOutException();
			}
		}
	}
}
