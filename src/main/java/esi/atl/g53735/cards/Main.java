package esi.atl.g53735.cards;

import esi.atl.g53735.model.Deck;
import java.util.Scanner;

/**
 *
 * @author Utilisateur
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Error, respect the rules");
        }
        int n;
        if (!isStringInteger(args[0])) {
            System.out.println("This is not a number");
            int nb = enterInteger("Enter a integer between 1 and 52: ");
            n = nb;
        } else {
            n = Integer.parseInt(args[0]);
        }
        if (n < 1 || n > 52) {
            throw new IllegalArgumentException("Error, respect the consign");
        }
        Deck deck = new Deck();
        deck.shuffle();
        if (n == 1) {
            System.out.print("La carte supprimée: ");
        } else {
            System.out.print("Les cartes supprimées: ");
        }
        for (int i = 0; i < n; i++) {
//            System.out.println(deck.hit());
        }
        System.out.println("Les cartes restant: " + deck.toString());
    }

    /**
     * Ask an integer as long as it is not an integer.
     * 
     * @param message the given message to display.
     * @return the given integer.
     */
    private static int enterInteger(String message) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("not an integer, try again");
            System.out.print(message);
        }
        return keyboard.nextInt();
    }

    /**
     * Check if the given string is a number.
     * 
     * @param number the given string.
     * @return false if it is not a number else true.
     */
    private static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
