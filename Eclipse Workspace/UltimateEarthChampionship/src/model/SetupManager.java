package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InputException;

public abstract class SetupManager {

	static model.Configuration config = model.Configuration.getInstance();
	
	public enum GUISetupStage {None, Name, Weeks, Champions, Difficulty, Done};
	
	public static String PromptForTeamName(String input) throws InputException
	{
		String teamName = input;
	
		Pattern allowedChars = Pattern.compile("[A-Za-z0-9]");
		
		if (teamName.length() < config.MIN_TEAM_NAME_CHARS)
		{
			throw new InputException("Team name is too small!");
		}
		else if (teamName.length() > config.MAX_TEAM_NAME_CHARS)
		{
			throw new InputException("Team name is too large!");
		}
		else 
		{
			// Check for special characters
			Matcher matcher = allowedChars.matcher(teamName);
			
			if (!matcher.find())
			{
				// Special characters found
				throw new InputException("Team name contains special characters!");
			}
		}
		
		return teamName;
	}
	
	public static int PromptForNumWeeks(String input) throws InputException
	{
		int gameWeeks = 0;
		
		// Check for valid Integer
		try 
		{
			gameWeeks = Integer.parseInt(input);
		} 
		catch (NumberFormatException e) 
		{
			throw new InputException("Not a valid integer!");
		}
		
		// Check that range is acceptable
		if (gameWeeks < config.MIN_NUM_GAME_WEEKS)
		{
			throw new InputException("Number of weeks chosen is too small!");
		}
		else if (gameWeeks > config.MAX_NUM_GAME_WEEKS)
		{
			throw new InputException("Number of weeks chosen is too large!");
		}
		
		return gameWeeks;
	}
	
	public static Champion ChooseChampionFrom(ArrayList<Champion> champions, String input) throws InputException
	{
		int champIndex = -1;
		
		// Check for valid Integer
		try 
		{
			champIndex = Integer.parseInt(input);
			// Step down to account for lists starting at 0
			champIndex -= 1;
		} 
		catch (NumberFormatException e) 
		{
			throw new InputException("Not a valid integer!");
		}
		
		// Check for valid Index:
		if (champIndex >= champions.size())
		{
			throw new InputException("Not a valid champion!");
		}
		
		// Return and remove champion
		Champion out = champions.get(champIndex);
		champions.remove(champIndex);
		
		return out;
	}
	
	public static float PromptForDifficulty(String input) throws InputException
	{
		float out = 0f;
		
		// Check for valid float
		try 
		{
			out = Float.parseFloat(input);
		} 
		catch (NumberFormatException e) 
		{
			throw new InputException("Not a valid float!");
		}
		
		// Check within acceptable range
		if (out < 0.5f)
		{
			throw new InputException("Difficulty is too small!");
		}
		if (out > 2f)
		{
			throw new InputException("Difficulty is too large!");
		}
		
		return out;
	}
	
	public static GUISetupStage getNextStage(GUISetupStage currentStage)
	{
		switch (currentStage) 
		{
		case None:
			return GUISetupStage.Name;

		case Name:
			return GUISetupStage.Weeks;
		
		case Weeks:
			return GUISetupStage.Champions;
		
		case Champions:
			return GUISetupStage.Difficulty;
		
		case Difficulty:
			return GUISetupStage.Done;
		default:
			break;
		}
		
		return GUISetupStage.None;
	}
	
}