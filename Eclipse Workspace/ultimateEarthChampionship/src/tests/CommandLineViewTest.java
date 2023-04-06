package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.CommandLineView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class CommandLineViewTest
{

	private CommandLineView view;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStream() {
		view = new CommandLineView();
	    System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void restoreStream() {
	    System.setOut(originalOut);
	}
	
	@Test
	void testPrintView()
	{
		String expected = "";
		expected += "====================\n";
		expected += "====== Title =======\n";
		expected += "====================\n";
		expected += "First line\n";
		expected += "Second line\n";
		expected += "Third line\n";
		expected += "====================\n";
		expected += "1 First option\n";
		expected += "2 Second option\n";
		expected += "3 Third option\n";

		String title = "Title";
		ArrayList<String> content = new ArrayList<String>(List.of("First line",
				"Second line", "Third line"));
		ArrayList<String> options = new ArrayList<String>(List.of("First option",
				"Second option", "Third option"));
		
		view.printView(title, content, options);
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintTitle_emptyString()
	{
		String expected = "=========  =========\n";
		
		view.printTitle("");

		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintTitle_oddString()
	{		
		String expected = "==== Odd title =====\n";
		
		view.printTitle("Odd title");

		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintTitle_evenString()
	{		
		String expected = "==== Even title ====\n";
		
		view.printTitle("Even title");

		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintTitle_longString()
	{		
		String expected = " 123456789123456789 \n";
		
		view.printTitle("123456789123456789");

		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintTitle_overflowingString()
	{		
		String expected = " 012345678901234567890123456789 \n";
		
		view.printTitle("012345678901234567890123456789");

		assertEquals(expected, outContent.toString());
	}

	@Test
	void testPrintLine() {
		String expected = "====================\n";
		view.printLine();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintContent_emptyList() {
		String expected = "";
		ArrayList<String> content = new ArrayList<String>();  
		view.printContent(content);
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintContent_nonEmptyList() {
		String expected = "";
		expected += "First line\n";
		expected += "Second line\n";
		expected += "Third line\n";
		ArrayList<String> content = new ArrayList<String>(List.of("First line",
				"Second line", "Third line"));  
		view.printContent(content);
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintOptions_emptyList() {
		String expected = "";
		ArrayList<String> options = new ArrayList<String>();  
		view.printOptions(options);
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	void testPrintOptions_nonEmptyList() {
		String expected = "";
		expected += "1 First option\n";
		expected += "2 Second option\n";
		expected += "3 Third option\n";
		ArrayList<String> options = new ArrayList<String>(List.of("First option",
				"Second option", "Third option"));  
		view.printOptions(options);
		assertEquals(expected, outContent.toString());
	}
}
