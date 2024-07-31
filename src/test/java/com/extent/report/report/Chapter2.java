package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter2 {
	public static void main(String[] args) throws IOException {
		ExtentReports extentReport= new ExtentReports();
		File file= new  File("report1.html");
		ExtentSparkReporter sparkReport= new ExtentSparkReporter(file);
		extentReport.attachReporter(sparkReport);
		
		extentReport.createTest("Test1")
		.log(Status.INFO, "info1");
		
		
		extentReport.flush();
		Desktop.getDesktop().browse(file.toURI());
		
	}

}
