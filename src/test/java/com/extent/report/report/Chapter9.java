package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter9 {
	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();

		ExtentSparkReporter allsparkReport = new ExtentSparkReporter("Alltest.html");

		ExtentSparkReporter failedsparkReport = new ExtentSparkReporter("failed.html");
		failedsparkReport.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();

		ExtentSparkReporter skippedsparkReport = new ExtentSparkReporter("skipped.html");
		failedsparkReport.filter().statusFilter().as(new Status[] { Status.SKIP, Status.WARNING }).apply();
		
		extentReports.attachReporter(allsparkReport, failedsparkReport, skippedsparkReport);

		extentReports.createTest("Test1").assignAuthor("Yogi").assignCategory("smoke").assignDevice("Chrome99")
				.skip("test failed");

		extentReports.createTest("Test2").assignAuthor("Eswar").assignCategory("sanity").assignDevice("Edge99")
				.fail("This Test is failed");

		extentReports.createTest("Test2").assignAuthor("swamy").assignCategory("sanity").assignDevice("Edge102")
				.fail("This Test is failed");

		extentReports.createTest("Test2").assignAuthor("saranam").assignCategory("smoke").assignDevice("Chrome89")
				.pass("This Test is failed");

		extentReports.createTest("Test7", "The double Test")
				.assignAuthor(new String[] { "swamy", "saranam", "Ayyappa" })
				.assignCategory(new String[] { "smoke", "sanity", "regression" })
				.assignDevice(new String[] { "edge99", "chrome99", "Edge102" }).warning("warning");

		extentReports.flush();
		Desktop.getDesktop().browse(new File("Alltest.html").toURI());
		Desktop.getDesktop().browse(new File("failed.html").toURI());
		Desktop.getDesktop().browse(new File("skipped.html").toURI());
	}
}
