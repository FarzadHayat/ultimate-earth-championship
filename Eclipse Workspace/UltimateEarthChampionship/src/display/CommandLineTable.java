package display;

import java.util.ArrayList;

import model.Champion;
import model.Weapon;

/**
 * Static class which offers various functionality for printing different tables
 * featuring the stats of champions and weapons
 */
public class CommandLineTable {

	/**
	 * Prints a table of champions from a given arraylist of champions
	 * @param champions The champions to be printed
	 */
	public static void printChampions(ArrayList<Champion> champions) {
		printChampionTitle();
		printChampionDivider();
		for (Champion champion : champions) {
			printChampionEntry(champion);
		}
	}

	/**
	 * Prints a table of weapons from a given arraylist of weapons
	 * @param weapons Weapons to be printed
	 */
	public static void printWeapons(ArrayList<Weapon> weapons) {
		printWeaponTitle();
		printWeaponDivider();
		for (Weapon weapon : weapons) {
			printWeaponEntry(weapon);
		}
	}

	/**
	 * Prints the header of the table for a champion table
	 */
	private static void printChampionTitle() {
		System.out.println(
				"Champion [ Name                     | Stamina | Regen | Offense | Defense | Level | Price | Price change weekly ]");
	}

	/**
	 * Prints the header of the table for a weapons table
	 */
	private static void printWeaponTitle() {
		System.out.println(
				"Weapon [ Name                  | Damage multiplier | Offense boost | Defense boost | Price | Price change weekly ]");
	}

	/**
	 * Prints the divider for a champion table
	 */
	private static void printChampionDivider() {
		System.out.println(
				"         [-------------------------------------------------------------------------------------------------------]");
	}

	/**
	 * Prints the divider for a weapon table
	 */
	private static void printWeaponDivider() {
		System.out.println(
				"       [---------------------------------------------------------------------------------------------------------]");
	}

	/**
	 * Prints a champion as a row in a champion table
	 * @param champion The champion to be printed
	 */
	private static void printChampionEntry(Champion champion) {
		System.out.println(String.format("         [ %24s | %6s | %7s | %7s | %7s | %5s | %5s | %19s ]",
				champion.getName(), champion.getStamina(), champion.getRegen(), champion.getOffense(),
				champion.getDefense(), champion.getLevel(), champion.getPrice(), champion.getPriceChangeWeekly()));
	}

	/**
	 * Prints a weapon as a row in a weapon table
	 * @param weapon The weapon to be printed
	 */
	private static void printWeaponEntry(Weapon weapon) {
		System.out.println(String.format("       [ %-21s | %17s | %13s | %13s | %5s | %19s ]", weapon.getName(),
				weapon.getDamageMultiplier(), weapon.getOffenseBoost(), weapon.getDefenseBoost(), weapon.getPrice(),
				weapon.getPriceChangeWeekly()));
	}

}
