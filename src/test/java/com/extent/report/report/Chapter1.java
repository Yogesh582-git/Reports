package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter1 {

	public static void main(String[] args) throws Exception {
		ExtentReports extentReport = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		extentReport.attachReporter(sparkReport);

		ExtentTest test1 = extentReport.createTest("Test1");
		test1.pass("This is pass");

		ExtentTest test2 = extentReport.createTest("Test2");
		test2.log(Status.FAIL, "This is failed");

		extentReport.createTest("Test3").skip("This is skipped");

		extentReport.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
	}

}
