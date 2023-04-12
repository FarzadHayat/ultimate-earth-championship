package main;

import views.CommandLineView;

public class CommandLineGameManager extends GameManager
{

	private CommandLineView commandLineView;
	
	@Override
	public void play()
	{
		commandLineView = new CommandLineView();
		getShop().generateCatalogue();
		commandLineView.displayShop(getShop());
		commandLineView.closeScanner();
	}
}
