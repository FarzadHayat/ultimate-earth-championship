package main;

import java.util.ArrayList;
import java.util.List;

import views.CommandLineView;
import weapons.*;

public class CommandLineGameManager extends GameManager
{

	private CommandLineView commandLineView;
	
	@Override
	public void play()
	{
		commandLineView = new CommandLineView();
		ArrayList<String> weaponsStrings = new ArrayList<String>();
		for (Weapon weapon : getWeapons()) {
			weaponsStrings.add(weapon.toString());
		}
		ArrayList<String> options = new ArrayList<String>(List.of("One", "Two", "Three")); 
		commandLineView.printView("My title", weaponsStrings, options);
	}

	public static void main(String[] args)
	{
		new CommandLineGameManager();
	}

}
