package display;

import java.util.ArrayList;

import model.Champion;
import model.Weapon;

public class CommandLineTable {

	public static void printChampions(ArrayList<Champion> champions) {
		printChampionTitle();
		printChampionDivider();
		for (Champion champion : champions) {
			printChampionEntry(champion);
		}
	}

	public static void printWeapons(ArrayList<Weapon> weapons) {
		printWeaponTitle();
		printWeaponDivider();
		for (Weapon weapon : weapons) {
			printWeaponEntry(weapon);
		}
	}

	private static void printChampionTitle() {
		System.out.println(
				"Champion [ Name                     | Stamina | Regen | Offense | Defense | Level | Price | Price change weekly ]");
	}

	private static void printWeaponTitle() {
		System.out.println(
				"Weapon [ Name                  | Damage multiplier | Offense boost | Defense boost | Price | Price change weekly ]");
	}

	private static void printChampionDivider() {
		System.out.println(
				"         [-------------------------------------------------------------------------------------------------------]");
	}

	private static void printWeaponDivider() {
		System.out.println(
				"       [---------------------------------------------------------------------------------------------------------]");
	}

	private static void printChampionEntry(Champion champion) {
		System.out.println(String.format("         [ %24s | %6s | %7s | %7s | %7s | %5s | %5s | %19s ]",
				champion.getName(), champion.getStamina(), champion.getRegen(), champion.getOffense(),
				champion.getDefense(), champion.getLevel(), champion.getPrice(), champion.getPriceChangeWeekly()));
	}

	private static void printWeaponEntry(Weapon weapon) {
		System.out.println(String.format("       [ %-21s | %17s | %13s | %13s | %5s | %19s ]", weapon.getName(),
				weapon.getDamageMultiplier(), weapon.getOffenseBoost(), weapon.getDefenseBoost(), weapon.getPrice(),
				weapon.getPriceChangeWeekly()));
	}

}
