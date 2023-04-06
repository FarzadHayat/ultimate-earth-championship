package main;

import views.MainScreen;
import views.TabbedView;

public class GraphicalGameManager extends GameManager
{
	
	private MainScreen mainScreen;

	@Override
	public void play()
	{
		mainScreen = new MainScreen();
		TabbedView tabbedView = new TabbedView();
		mainScreen.displayView(tabbedView);
	}

	public static void main(String[] args)
	{
		new GraphicalGameManager();
	}

}
