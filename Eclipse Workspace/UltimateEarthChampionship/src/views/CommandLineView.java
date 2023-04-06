package views;

import java.util.ArrayList;
import java.util.List;

import weapons.Chainsaw;
import weapons.Pickaxe;
import weapons.Shield;

public class CommandLineView
{
	private final String filler = "=";
	private final int lineWidth = 20;
	
	public void printView(String title, ArrayList<String> content, ArrayList<String> options) {
		printLine();
		printTitle(title);
		printLine();
		printContent(content);
		printLine();
		printOptions(options);
	}
	
	public void printTitle(String title) {
		int numberOfFillers = Integer.max((int) ((lineWidth - title.length() - 2) / 2), 0);
		String text = "";
		for (int i = 0; i < numberOfFillers; i++) {
			text += filler;
		}
		if (title.length() % 2 == 0) {
			System.out.println(text + " " + title + " " + text);
		}
		else {
			System.out.println(text + " " + title + " " + filler + text);
		}
	}
	
	public void printLine() {
		String text = "";
		for (int i = 0; i < lineWidth; i++) {
			text += filler;
		}
		System.out.println(text);
	}
	
	public void printContent(ArrayList<String> content) {
		for (String line : content) {
			System.out.println(line);
		}
	}
	
	public void printOptions(ArrayList<String> options) {
		for (int i = 0; i < options.size(); i++) {
			System.out.println(String.valueOf(i+1) + " " + options.get(i));
		}
	}
}
