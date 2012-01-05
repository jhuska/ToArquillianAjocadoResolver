/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.richfaces.qa.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:jhuska@redhat.com">Juraj Huska</a>
 */
public class Resolver {

	// Contstants
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private final static String ERROR_MSG_PATH = "USAGE: You should provide path, which has to point to the tests source top level directory, "
			+ "e.g. in metamer case: src/test/java/org/richfaces/tests/metamer/ftest";

	private final static String EOL = System.getProperty("line.separator");

	// key is the richfaces-selenium stuff and the value is the Ajocado stuff
	private final static Map<String, String> FIXES_WHOLE_LINES = new LinkedHashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("import static org.jboss.test.selenium.utils.URLUtils.buildUrl;",
					"import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;");
			put("import org.jboss.test.selenium.utils.URLUtils;",
					"import static org.jboss.arquillian.ajocado.utils.URLUtils.buildUrl;");
			put("import org.jboss.test.selenium.locator.JQueryLocator;",
					"import org.jboss.arquillian.ajocado.locator.JQueryLocator;"
							+ EOL
							+ "import static org.jboss.arquillian.ajocado.Ajocado.*;"
							+ EOL);
			put("import org.jboss.test.selenium.waiting.selenium.SeleniumCondition;",
					"import org.jboss.arquillian.ajocado.waiting.selenium.SeleniumCondition;");
			put("import org.testng.annotations.Test;",
					"import org.testng.annotations.Test;"
							+ EOL
							+ "import org.jboss.arquillian.ajocado.framework.AjocadoConfigurationContext;"
							+ EOL
							+ "import static org.jboss.arquillian.ajocado.Ajocado.*;"
							+ EOL);
			put("import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.guardHttp;",
					"import static org.jboss.arquillian.ajocado.Ajocado.guardHttp;");
			put("import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.guardXhr;",
					"import static org.jboss.arquillian.ajocado.Ajocado.guardXhr;");
			put("import static org.jboss.test.selenium.locator.option.OptionLocatorFactory.optionLabel;",
					"import static org.jboss.arquillian.ajocado.locator.option.OptionLocatorFactory.optionLabel;");
			put("import org.jboss.test.selenium.encapsulated.JavaScript;",
					"import org.jboss.arquillian.ajocado.javascript.JavaScript;");
			put("import org.richfaces.tests.metamer.ftest.AbstractMetamerTest;",
					"import org.richfaces.tests.metamer.ftest.AbstractAjocadoTest;");
			put("import static org.jboss.test.selenium.locator.LocatorFactory.jq;",
					"import static org.jboss.test.selenium.locator.utils.LocatorEscaping.jq;");
			put("import static org.jboss.test.selenium.locator.option.OptionLocatorFactory.optionValue;",
					"import static org.jboss.arquillian.ajocado.locator.option.OptionLocatorFactory.optionValue;");
			put("import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.guardNoRequest;",
					"import static org.jboss.arquillian.ajocado.Ajocado.guardNoRequest;");
			put("import org.jboss.test.selenium.dom.Event;",
					"import org.jboss.arquillian.ajocado.dom.Event;");
			put("import org.jboss.test.selenium.locator.Attribute;",
					"import org.jboss.arquillian.ajocado.dom.Attribute;");
			put("import org.jboss.test.selenium.locator.AttributeLocator;",
					"import org.jboss.arquillian.ajocado.locator.attribute.AttributeLocator;");
			put("import org.jboss.test.selenium.css.CssProperty;",
					"import org.jboss.arquillian.ajocado.css.CssProperty;"
							+ EOL
							+ "import org.jboss.arquillian.ajocado.javascript.KeyCode;");
			put("import static org.jboss.test.selenium.dom.Event.*;",
					"import static org.jboss.arquillian.ajocado.dom.Event.*;");
			put("import static org.jboss.test.selenium.utils.text.SimplifiedFormat.format;",
					"import static org.jboss.arquillian.ajocado.format.SimplifiedFormat.format;");
			put("import org.jboss.test.selenium.SystemProperties;", "");
			put("import static org.jboss.test.selenium.SystemProperties.isSeleniumDebug;",
					"import org.jboss.arquillian.ajocado.framework.AjocadoConfigurationContext;");
			put("import org.jboss.test.selenium.request.RequestType;",
					"import org.jboss.arquillian.ajocado.request.RequestType;");
			put("URL contextPath = SystemProperties.getContextPath();", "");
			put("import org.jboss.test.selenium.waiting.retrievers.TextRetriever;",
					"import org.jboss.arquillian.ajocado.waiting.retrievers.TextRetriever;");
			put("import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;",
					"import static org.jboss.arquillian.ajocado.guard.RequestGuardFactory.guard;"
							+ EOL
							+ "import org.jboss.arquillian.ajocado.request.RequestType;"
							+ EOL);
			put("import org.jboss.test.selenium.locator.ElementLocator;",
					"import org.jboss.arquillian.ajocado.locator.element.ElementLocator;");
			put("import static org.jboss.test.selenium.encapsulated.JavaScript.js;",
					"import static org.jboss.arquillian.ajocado.javascript.JavaScript.js;");
			put("import static org.jboss.test.selenium.locator.Attribute.ALT;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.ALT;");
			put("import org.jboss.test.selenium.framework.AjaxSelenium;",
					"import org.jboss.arquillian.ajocado.framework.AjaxSelenium;");
			put("import org.jboss.test.selenium.framework.AjaxSeleniumProxy;",
					"import org.jboss.arquillian.ajocado.framework.AjaxSeleniumContext;");
			put("AjaxSelenium selenium = AjaxSeleniumProxy.getInstance();",
					"AjaxSelenium selenium = AjaxSeleniumContext.getProxy();");
			put("import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.guard;",
					"import static org.jboss.arquillian.ajocado.guard.RequestGuardFactory.guard;");
			put("import static org.jboss.test.selenium.dom.Event.DBLCLICK;",
					"import static org.jboss.arquillian.ajocado.dom.Event.DBLCLICK;");
			put("import static org.jboss.test.selenium.dom.Event.MOUSEDOWN;",
					"import static org.jboss.arquillian.ajocado.dom.Event.MOUSEDOWN;");
			put("import static org.jboss.test.selenium.dom.Event.MOUSEUP;",
					"import static org.jboss.arquillian.ajocado.dom.Event.MOUSEUP;");
			put("import static org.jboss.test.selenium.locator.Attribute.SRC;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.SRC;");
			put("import static org.jboss.test.selenium.locator.Attribute.COLSPAN;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.COLSPAN;");
			put("import static org.jboss.test.selenium.locator.Attribute.ROWSPAN;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.ROWSPAN;");
			put("import org.jboss.test.selenium.actions.Drag;",
					"import org.jboss.arquillian.ajocado.actions.Drag;");
			put("import org.jboss.test.selenium.encapsulated.FrameLocator;",
					"import org.jboss.arquillian.ajocado.locator.frame.FrameIndexLocator;"
							+ EOL
							+ "import org.jboss.arquillian.ajocado.locator.frame.FrameRelativeLocator;"
							+ EOL);
			put("import org.jboss.test.selenium.utils.array.ArrayTransform;",
					"import org.jboss.arquillian.ajocado.utils.array.ArrayTransform;");
			put("import org.jboss.test.selenium.waiting.retrievers.Retriever;",
					"import org.jboss.arquillian.ajocado.waiting.retrievers.Retriever;");
			put("import org.jboss.test.selenium.locator.option.OptionValueLocator;",
					"import org.jboss.arquillian.ajocado.locator.option.OptionValueLocator;");
			put("import org.jboss.test.selenium.geometry.Offset;",
					"import org.jboss.arquillian.ajocado.geometry.Offset;");
			put("import org.jboss.test.selenium.geometry.Point;",
					"import org.jboss.arquillian.ajocado.geometry.Point;");
			put("import static org.jboss.test.selenium.dom.Event.CLICK;",
					"import static org.jboss.arquillian.ajocado.dom.Event.CLICK;");
			put("import static org.jboss.test.selenium.dom.Event.KEYDOWN;",
					"import static org.jboss.arquillian.ajocado.dom.Event.KEYDOWN;");
			put("import static org.jboss.test.selenium.dom.Event.KEYPRESS;",
					"import static org.jboss.arquillian.ajocado.dom.Event.KEYPRESS;");
			put("import static org.jboss.test.selenium.dom.Event.MOUSEMOVE;",
					"import static org.jboss.arquillian.ajocado.dom.Event.MOUSEMOVE;");
			put("import static org.jboss.test.selenium.dom.Event.MOUSEOUT;",
					"import static org.jboss.arquillian.ajocado.dom.Event.MOUSEOUT;");
			put("import static org.jboss.test.selenium.dom.Event.MOUSEOVER;",
					"import static org.jboss.arquillian.ajocado.dom.Event.MOUSEOVER;");
			put("import static org.jboss.test.selenium.dom.Event.KEYUP;",
					"import static org.jboss.arquillian.ajocado.dom.Event.KEYUP;");
			put("import org.jboss.test.selenium.locator.ExtendedLocator;",
					"import org.jboss.arquillian.ajocado.locator.element.ExtendedLocator;");
			put("import org.jboss.test.selenium.waiting.selenium.SeleniumWaiting;",
					"import org.jboss.arquillian.ajocado.waiting.selenium.SeleniumWaiting;");
			put("import org.jboss.test.selenium.geometry.Dimension;",
					"import org.jboss.arquillian.ajocado.geometry.Dimension;");
			put("import org.jboss.test.selenium.utils.text.SimplifiedFormat;",
					"import org.jboss.arquillian.ajocado.format.SimplifiedFormat;");
			put("import org.jboss.test.selenium.waiting.retrievers.AttributeRetriever;",
					"import org.jboss.arquillian.ajocado.waiting.retrievers.AttributeRetriever;");
			put("import org.jboss.test.selenium.waiting.retrievers.RetrieverFactory;",
					"import org.jboss.arquillian.ajocado.waiting.retrievers.TextRetriever;");
			put("import static org.jboss.test.selenium.locator.Attribute.TITLE;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.TITLE;");
			put("import static org.jboss.test.selenium.waiting.WaitFactory.WAIT_AJAX;",
					"");
			put("import static org.jboss.test.selenium.waiting.retrievers.RetrieverFactory.RETRIEVE_TEXT;",
					"");
			put("import static org.jboss.test.selenium.waiting.retrievers.RetrieverFactory.RETRIEVE_ATTRIBUTE;",
					"");
			put("import org.jboss.test.selenium.locator.option.OptionLocator;",
					"import org.jboss.arquillian.ajocado.locator.option.OptionLocator;");
			put("import static org.jboss.test.selenium.locator.Attribute.VALUE;",
					"import static org.jboss.arquillian.ajocado.dom.Attribute.VALUE;");
			put("import org.jboss.test.selenium.interception.CommandContext;",
					"import org.jboss.arquillian.ajocado.command.CommandContext;");
			put("import org.jboss.test.selenium.interception.CommandInterceptionException;",
					"import org.jboss.arquillian.ajocado.command.CommandInterceptorException;");
			put("import org.jboss.test.selenium.interception.CommandInterceptor;",
					"import org.jboss.arquillian.ajocado.command.CommandInterceptor;");
			put("import org.jboss.test.selenium.locator.LocatorFactory;",
					"import org.jboss.arquillian.ajocado.locator.LocatorFactory;");
			put("import org.jboss.test.selenium.locator.option.OptionLabelLocator;",
					"import org.jboss.arquillian.ajocado.locator.option.OptionLabelLocator;");
			put("import org.jboss.test.selenium.waiting.Wait;", "");
			put("import static org.jboss.test.selenium.waiting.Wait.waitAjax;",
					"");
			put("import org.jboss.test.selenium.waiting.conditions.IsDisplayed;",
					"");
			put("waitAjax().dontFail().interval(50).timeout(2000).until(IsDisplayed.getInstance().locator(this));",
					"waitAjax.dontFail().interval(50).timeout(2000).until(elementVisible.locator(this));");
			put("assertNotEquals(statusAfter, statusBefore, \"The status attribute doesn't work.\");",
					"assertFalse(statusAfter.equals(statusBefore), \"The status attribute doesn't work.\");");
			put("import static org.testng.Assert.assertNotEquals;", "");
		}
	};

	private final static Map<String, String> FIXES_WORDS = new LinkedHashMap<String, String>() {

		private static final long serialVersionUID = 2L;

		{
			put("extends AbstractMetamerTest", "extends AbstractAjocadoTest");
			put(".getAsString()", ".getRawLocator()");
			put("buildUrl(contextRoot", "buildUrl(contextPath");
			put("import static org.jboss.test.selenium.utils.PrimitiveUtils",
					"import static org.jboss.arquillian.ajocado.utils.PrimitiveUtils");
			put(".getRequestInterceptor().clearRequestTypeDone();",
					".getRequestGuard().clearRequestDone();");
			put(".getRequestInterceptor().waitForRequestTypeChange()",
					".getRequestGuard().waitForRequest();");
			put("SystemProperties.isSeleniumDebug()",
					"AjocadoConfigurationContext.getProxy().isSeleniumDebug()");
			put("isSeleniumDebug()",
					"AjocadoConfigurationContext.getProxy().isSeleniumDebug()");
			put("seleniumDebug",
					"AjocadoConfigurationContext.getProxy().isSeleniumDebug()");
			put(".getNthOccurence", ".get");
			put("waitXhr(selenium)", " guard(selenium, RequestType.XHR)");
			put(".isDisplayed(", ".isVisible(");
			put("isDisplayed.", "elementVisible.");
			put("URLUtils.buildUrl(", "buildUrl(");
			put("isNotDisplayed.", "elementNotVisible.");
			put(".getRequestInterceptor().getRequestTypeDone()",
					".getRequestGuard().getRequestDone()");
			put("FrameLocator.PARENT", "FrameRelativeLocator.PARENT");
			put("private FrameLocator frameLocator = new FrameLocator(\"jquery=iframe:eq(0)\");",
					"//private FrameLocator frameLocator = new FrameLocator(\"jquery=iframe:eq(0)\");"
							+ EOL
							+ "    private FrameIndexLocator frameLocator = new FrameIndexLocator(0);");
			put("\"\\\\40\"", "KeyCode.DOWN_ARROW");
			put(".getAllOccurrences()", ".iterator()");
			put("RetrieverFactory.RETRIEVE_TEXT", "TextRetriever.getInstance()");
			put("phasesItems.getNthChildElement(i)",
					"jq(SimplifiedFormat.format(\"{0}:nth-child({1})\", phasesItems.getRawLocator(), i))");
			put("RETRIEVE_TEXT.", "retrieveText.");
			put("RETRIEVE_ATTRIBUTE.", "retrieveAttribute.");
			put("WAIT_AJAX.", "waitAjax.");
			put("AjaxSeleniumProxy.getInstance();",
					"AjaxSeleniumContext.getProxy();");
			put("Wait.waitAjax()", "waitAjax");
			put(".getInterceptionProxy()", ".getCommandInterceptionProxy()");
			put("CommandInterceptionException", "CommandInterceptorException");
			put("new FrameLocator(\"relative=top\")",
					"FrameRelativeLocator.TOP");
			put("IsDisplayed.getInstance()", "elementVisible");
		}
	};

	private static final String[] DO_NOT_FIX_FILES = { "AbstractModel",
			"AbstractMetamerTest", "MatrixConfigurator",
			"MetamerConsoleStatusTestListener", "CollapsibleSubTable",
			"DataGrid", "DataTable", "ExtendedDataTable", "OrderingList",
			"PanelMenu", "ListModel", "AbstractTreeNodeModel", "ModelIterable",
			"MetamerFailureLoggingTestListener", "MetamerProperties",
			"MetamerSeleniumLoggingTestListener", "MetamerTestInfo",
			"ColumnModel", "Autocomplete"};

	// Fields
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static File outPutDir = new File("ftest");

	private static int countOfTests = 0;

	// Static Methods
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static boolean resolve(String pathToTopLevelDirectoryOfTestSuite)
			throws IOException {

		File topLevelDir = new File(pathToTopLevelDirectoryOfTestSuite);

		recursiveResolve(topLevelDir, outPutDir.getAbsolutePath());

		return false;
	}

	private static void recursiveResolve(File topLevelDir, String currentDir)
			throws IOException {

		if (!topLevelDir.isDirectory()) {

			throw new IllegalArgumentException(ERROR_MSG_PATH);
		}

		File[] allFiles = topLevelDir.listFiles();
		String currentDirBackup = new String(currentDir);

		for (File i : allFiles) {

			if (i.isDirectory() & !(i.getName().equals(".svn"))) {

				File dir = new File(currentDirBackup
						+ System.getProperty("file.separator") + i.getName());
				dir.mkdir();
				currentDir = dir.getAbsolutePath();

				recursiveResolve(i, currentDir);
			} else if (!(i.isDirectory()) && willBeThisFileFixed(i.getName())) {

				fixTheImportsEtc(i, currentDirBackup);
				countOfTests++;
			}
		}
	}

	private static boolean willBeThisFileFixed(String filename) {

		boolean result = true;

		for (String i : DO_NOT_FIX_FILES) {

			if ((filename).equals(i + ".java")) {
				result = false;
			}
		}

		return result;

	}

	private static void fixTheImportsEtc(File file, String currentDir)
			throws IOException {

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader fir = new InputStreamReader(fis);
		BufferedReader bir = new BufferedReader(fir);

		File fixedFile = new File(currentDir
				+ System.getProperty("file.separator") + file.getName());
		FileOutputStream fos = new FileOutputStream(fixedFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bof = new BufferedWriter(osw);

		String line;

		while ((line = bir.readLine()) != null) {

			String fixedLine = fixLine(line);
			bof.write(fixedLine);
		}

		fixedFile.createNewFile();
		bof.close();
		bir.close();
	}

	private static String fixLine(String line) {

		String newLine = fixNonTrivialThingsWhichCanNotBeFixedBySimpleReplacing(line);

		Collection<String> c = FIXES_WHOLE_LINES.keySet();
		Iterator<String> i = c.iterator();

		while (i.hasNext()) {

			String key = i.next();
			if ((line.trim()).equals(key.trim())) {

				newLine = FIXES_WHOLE_LINES.get(key);
				break;
			}

		}

		Collection<String> words = FIXES_WORDS.keySet();
		Iterator<String> j = words.iterator();

		while (j.hasNext()) {

			String key = j.next();
			if ((newLine.trim()).contains(key.trim())) {

				newLine = line.replace(key, FIXES_WORDS.get(key));
				break;
			}

		}

		/*
		 * for(String i : FIXES_WHOLE_LINES.keySet()) {
		 * 
		 * if ((line.trim()).contains(i.trim())) newLine =
		 * FIXES_WHOLE_LINES.get(i); }
		 */

		/*
		 * for(String j : FIXES_WORDS.keySet()) {
		 * 
		 * if( (line.trim()).contains(j.trim()) ) newLine = line.replace(j,
		 * FIXES_WORDS.get(j)); }
		 */

		return (newLine + EOL);
	}

	private static String fixNonTrivialThingsWhichCanNotBeFixedBySimpleReplacing(
			final String line) {

		String newLine = line;

		if (line.indexOf("keyPress(") != -1) {

			if (line.indexOf("String.valueOf") != -1) {
				
				newLine = fixKeyPressNative(line);

			} else if ((line.indexOf("\")") != -1)
					|| (line.indexOf("\")") != -1)) {

				newLine = newLine.replace("\"", "\'");
			}
		} else if ((line.indexOf(".keyPressNative(") != -1)) {
			newLine =  fixKeyPressNative(line);
		} else if( line.indexOf(".keyDownNative(") != -1 ) {
			newLine =  fixKeyPressNative(line);
		} else if( line.indexOf(".keyUpNative(") != -1 ) {
			newLine =  fixKeyPressNative(line);
		}
			
		return newLine;
	}

	private static String fixKeyPressNative(String line) {

		String newLine = line;

		if (line.indexOf("String.valueOf") != -1) {

			newLine = newLine.replace("String.valueOf(", "");
			newLine = newLine.replace("))", ")");
			newLine = newLine.replace(") )", ")");
		} else {
			
			newLine = newLine.replace("\"", "");
		}

		return newLine;
	}

	private static boolean deleteNonEmptyDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteNonEmptyDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public static void main(String[] args) {

		if (args.length != 1) {

			throw new IllegalArgumentException(ERROR_MSG_PATH);
		}

		if (outPutDir.exists()) {
			boolean result = deleteNonEmptyDirectory(outPutDir);
			System.out.println("The existing output directory was "
					+ (result ? "deleted!" : " not deleted!"));
		}
		outPutDir.mkdir();

		try {
			resolve(args[0]);
		} catch (Exception ex) {

			throw new RuntimeException("An error occured during migration!", ex);
		}

		System.out.println("The number of affected files is: " + countOfTests);
	}
}
