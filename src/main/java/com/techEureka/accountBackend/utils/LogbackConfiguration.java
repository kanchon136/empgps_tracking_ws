package com.techEureka.accountBackend.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LogbackConfiguration {

	@EventListener(ApplicationReadyEvent.class)
	public void createLogFolderIfNotExists() {
		String logFilePath = "/home/eurakaadmin/deployment/tender_Logs"; // Change this to the desired log file path
		File logFolder = new File(logFilePath);

		if (!logFolder.exists()) {
			boolean folderCreated = logFolder.mkdirs();
			if (folderCreated) {
				System.out.println("Log folder created: " + logFolder.getAbsolutePath());
			} else {
				System.err.println("Failed to create log folder: " + logFolder.getAbsolutePath());
			}
		}
	}
}
