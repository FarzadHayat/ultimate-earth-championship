package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InputException;

public abstract class SetupManager {

	static model.Configuration config = model.Configuration.getInstance();

	/**
	 * Takes in a possible team name and returns it if it is acceptable
	 *
	 * @param input A possible team name
	 * @return The team name
	 * @throws InputException If the name is not allowed for the game
	 */
	public static String PromptForTeamName(String input) throws InputException {
		String teamName = input;

		Pattern allowedChars = Pattern.compile("[^A-Z^a-z^0-9]");

		if (teamName.length() < config.MIN_TEAM_NAME_CHARS) {
			throw new InputException("Team name is too small!");
		} else if (teamName.length() > config.MAX_TEAM_NAME_CHARS) {
			throw new InputException("Team name is too large!");
		}

		// Check for special characters
		Matcher matcher = allowedChars.matcher(teamName);

		Boolean containsSpecialCharacters = matcher.find();

		// System.out.println(containsSpecialCharacters);

		if (containsSpecialCharacters) {
			// Special characters found
			throw new InputException("Team name contains special characters!");
		}

		return teamName;
	}

	/**
	 * Takes a possible number of weeks as a string and returns it as an integer if
	 * it is acceptable
	 *
	 * @param input An inputed number of weeks as a string
	 * @return The number of teams in the game, as an integer
	 * @throws InputException If the number of weeks given is not allowed
	 */
	public static int PromptForNumWeeks(String input) throws InputException {
		int gameWeeks = 0;

		// Check for valid Integer
		try {
			gameWeeks = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new InputException("Not a valid integer!");
		}

		// Check that range is acceptable
		if (gameWeeks < config.MIN_NUM_GAME_WEEKS) {
			throw new InputException("Number of weeks chosen is too small!");
		} else if (gameWeeks > config.MAX_NUM_GAME_WEEKS) {
			throw new InputException("Number of weeks chosen is too large!");
		}

		return gameWeeks;
	}

	/**
	 * Returns a champion from a given list at a given index, throwing relevant
	 * exceptions
	 *
	 * @param champions ArrayList of Champions to be chosen from
	 * @param input     Index of the champion selected from the champions list, as a
	 *                  string
	 * @return The champion chosen
	 * @throws InputException If the inputed string is unrecognizable, or attempts
	 *                        to access a non-existent champion
	 */
	public static Champion ChooseChampionFrom(ArrayList<Champion> champions, String input) throws InputException {
		int champIndex = -1;

		// Check for valid Integer
		try {
			champIndex = Integer.parseInt(input);
			// Step down to account for lists starting at 0
			champIndex -= 1;
		} catch (NumberFormatException e) {
			throw new InputException("Not a valid integer!");
		}

		// Check for valid Index:
		if (champIndex < 0 || champIndex >= champions.size()) {
			throw new InputException("Not a valid champion!");
		}

		// Return and remove champion
		Champion out = champions.get(champIndex);
		champions.remove(champIndex);

		return out;
	}

	/**
	 * Returns a weapon from a given list at a given index, throwing relevant
	 * exceptions
	 *
	 * @param weapons ArrayList of Weapons to be chosen from
	 * @param input   Index of the weapon selected from the weapons list, as a
	 *                string
	 * @return The weapon chosen
	 * @throws InputException If the inputed string is unrecognizable, or attempts
	 *                        to access a non-existent weapon
	 */
	public static Weapon ChooseWeaponFrom(ArrayList<Weapon> weapons, String input) throws InputException {
		int weaponIndex = -1;

		// Check for valid Integer
		try {
			weaponIndex = Integer.parseInt(input);
			// Step down to account for lists starting at 0
			weaponIndex -= 1;
		} catch (NumberFormatException e) {
			throw new InputException("Not a valid integer!");
		}

		// Check for valid Index:
		if (weaponIndex < 0 || weaponIndex >= weapons.size()) {
			throw new InputException("Not a valid weapon!");
		}

		// Return and remove weapon
		Weapon out = weapons.get(weaponIndex);
		weapons.remove(weaponIndex);

		return out;
	}

	/**
	 * Takes a string and converts it to a difficulty which it returns as a float,
	 * throws exceptions if the string or difficulty are not allowed.
	 *
	 * @param input String input of game difficulty
	 * @return The game difficulty as a float
	 * @throws InputException If the provided difficulty or string cannot be parsed
	 *                        or are out of difficulty range
	 */
	public static float PromptForDifficulty(String input) throws InputException {
		float out = 0f;

		// Check for valid float
		try {
			out = Float.parseFloat(input);
		} catch (NumberFormatException e) {
			throw new InputException("Not a valid float!");
		}

		// Check within acceptable range
		if (out < 0.5f) {
			throw new InputException("Difficulty is too small!");
		}
		if (out > 2f) {
			throw new InputException("Difficulty is too large!");
		}

		return out;
	}

	/**
	 * Checks to see if a provided ArrayList of champions is of acceptable length
	 *
	 * @param champions Champions being tested
	 * @return the input champions list
	 * @throws InputException If the champions list is too small or large
	 */
	public static ArrayList<Champion> PromptForTeamChampions(ArrayList<Champion> champions) throws InputException {
		if (champions.size() < 4) {
			throw new InputException("Too few champions in team!");
		} else if (champions.size() > 4) {
			throw new InputException("Too many champions in team!");
		}

		return champions;
	}

}