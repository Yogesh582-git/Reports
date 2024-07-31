package com.extent.report.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter3 {
	public static void main(String[] args) throws Exception {
		ExtentReports extentReport = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		extentReport.attachReporter(sparkReport);

		extentReport.createTest("Test is passed").log(Status.INFO, "info").log(Status.INFO, "<b>info1</b>")
				.log(Status.INFO, "<i>info1</i>");

		String json = "{\r\n" + "    \"glossary\": {\r\n" + "        \"title\": \"example glossary\",\r\n"
				+ "		\"GlossDiv\": {\r\n" + "            \"title\": \"S\",\r\n" + "			\"GlossList\": {\r\n"
				+ "                \"GlossEntry\": {\r\n" + "                    \"ID\": \"SGML\",\r\n"
				+ "					\"SortAs\": \"SGML\",\r\n"
				+ "					\"GlossTerm\": \"Standard Generalized Markup Language\",\r\n"
				+ "					\"Acronym\": \"SGML\",\r\n" + "					\"Abbrev\": \"ISO 8879:1986\",\r\n"
				+ "					\"GlossDef\": {\r\n"
				+ "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\r\n"
				+ "						\"GlossSeeAlso\": [\"GML\", \"XML\"]\r\n" + "                    },\r\n"
				+ "					\"GlossSee\": \"markup\"\r\n" + "                }\r\n" + "            }\r\n"
				+ "        }\r\n" + "    }\r\n" + "}";

		String xml = "<!DOCTYPE glossary PUBLIC \"-//OASIS//DTD DocBook V3.1//EN\">\r\n"
				+ " <glossary><title>example glossary</title>\r\n" + "  <GlossDiv><title>S</title>\r\n"
				+ "   <GlossList>\r\n" + "    <GlossEntry ID=\"SGML\" SortAs=\"SGML\">\r\n"
				+ "     <GlossTerm>Standard Generalized Markup Language</GlossTerm>\r\n"
				+ "     <Acronym>SGML</Acronym>\r\n" + "     <Abbrev>ISO 8879:1986</Abbrev>\r\n" + "     <GlossDef>\r\n"
				+ "      <para>A meta-markup language, used to create markup\r\n"
				+ "languages such as DocBook.</para>\r\n" + "      <GlossSeeAlso OtherTerm=\"GML\">\r\n"
				+ "      <GlossSeeAlso OtherTerm=\"XML\">\r\n" + "     </GlossDef>\r\n"
				+ "     <GlossSee OtherTerm=\"markup\">\r\n" + "    </GlossEntry>\r\n" + "   </GlossList>\r\n"
				+ "  </GlossDiv>\r\n" + " </glossary>";

		extentReport.createTest("xml based Test").info(MarkupHelper.createCodeBlock(xml, CodeLanguage.XML));

		extentReport.createTest("json based Test").log(Status.INFO,
				MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));

		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("Swamy");
		arrayList.add("Saranam");
		arrayList.add("Ayyappa");

		Map<Integer, String> map = new HashMap();
		map.put(1, "Swamy");
		map.put(2, "Saranam");
		map.put(3, "Ayyappa");

		Set<Integer> set = map.keySet();

		extentReport.createTest("List Test").info(MarkupHelper.createOrderedList(arrayList))
				.info(MarkupHelper.createUnorderedList(arrayList));

		extentReport.createTest("Set Test").info(MarkupHelper.createOrderedList(set))
				.info(MarkupHelper.createUnorderedList(set));

		extentReport.createTest("Map Test").info(MarkupHelper.createOrderedList(map))
				.info(MarkupHelper.createUnorderedList(map));
		
		extentReport.createTest("Highlight Text")
		.info("This is normal message")
		.info(MarkupHelper.createLabel("This is highlighted message", ExtentColor.RED));
		
		
		try {
			int a=5/0;
		} catch (Exception e) {
			extentReport.createTest("Exception Test1")
			.fail(e);
		}
		
		Throwable t= new RuntimeException("This is run time exception");
		extentReport.createTest("Exception Test2")
		.fail(t);

		extentReport.flush();
		Desktop.getDesktop().browse((file).toURI());
	}
}
