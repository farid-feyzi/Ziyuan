/*
 * Copyright (C) 2013 by SUTD (Singapore)
 * All rights reserved.
 *
 * 	Author: SUTD
 *  Version:  $Revision: 1 $
 */

package sav.strategies.vm.interprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sav.strategies.vm.VMRunner;

/**
 * @author LLT
 * This VmRunner support interprocess communication.
 * example: 
 * 		ServerInputWriter inputWriter = new ServerInputWriter();
 * 		ServerOutputReader outputReader = new ServerOutputReader();
 * 		InterprocessVmRunner vmRunner = new  InterprocessVmRunner(inputWriter, outputReader);
 * 		try {
 *			vmRunner.start();
 *			inputWriter.sendData(...));
 *			output = outputReader.readOutput();
 * 		} finally {
 * 			vmRunner.stop();
 *			inputWriter.close();
 *			outputReader.close();
 * 		}
 * 
 * Extend ServerInputWriter and ServerOutputReader for your particular purpose.
 * Note: in case of Python, make sure you flush the stdout in order for main process to read!
 * using stdout.flush()
 */
public class InterprocessVmRunner extends VMRunner {
	private final Logger log = LoggerFactory.getLogger(InterprocessVmRunner.class);
	private ServerInputWriter inputWriter;
	private ServerOutputReader outputReader;
	
	public InterprocessVmRunner(ServerInputWriter inputWriter, ServerOutputReader outputReader) {
		this.inputWriter = inputWriter;
		this.outputReader = outputReader;
	}

	public void stop(boolean closeStreams) {
		if (process != null) {
			process.destroy();
		}
		if (closeStreams) {
			inputWriter.close();
			outputReader.close();
		}
	}
	
	@Override
	protected void setupErrorStream(InputStream errorStream, StringBuffer sb) {
		super.setupInputStream(errorStream, sb);
	}
	
	@Override
	public void setupInputStream(final InputStream is, final StringBuffer sb) {
		new Thread(new Runnable() {
			public void run() {
				final InputStreamReader streamReader = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(streamReader);
				try {
					String line = null;
					try {
						while ((line = br.readLine()) != null) {
							if (!outputReader.isClosed() && outputReader.isMatched(line)) {
								outputReader.read(br);
							}
						}
					} catch (IOException e) {
						log.warn(e.getMessage());
					}
				} finally {
					IOUtils.closeQuietly(streamReader);
					IOUtils.closeQuietly(br);
					IOUtils.closeQuietly(is);
				}
				
			}
		}).start();
	}
	
	@Override
	protected void setupOutputStream(final OutputStream outputStream) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				process.getOutputStream();
				inputWriter.setOutputStream(outputStream);
				try {
					while(!inputWriter.isClosed()) {
						if (inputWriter.isReady()) {
							inputWriter.write();
							try {
								outputStream.flush();
							} catch (IOException e) {
								log.warn(e.getMessage());
							}
						}
					}
				} finally {
					IOUtils.closeQuietly(outputStream);
				}
			}
		});
		thread.start();
	}
}
