import java.util.Scanner;

public class FalstaffsFury {
    Scanner input = new Scanner(System.in);

    public static String greeting(String name) {
        return "Greetings " + name;
    }

    public static int health(int statpts) {
        int avail = statpts - 2;
        System.out.println("How many stat points for your HEALTH");
        return getInteger(1, avail);
    }

    public static int attack(int statpts) {
        int avail = statpts - 1;
        System.out.printf("\nYou have %d stat points remaining\n", statpts);
        System.out.println("\nHow many stat points for your ATTACK");
        return getInteger(1, avail);
    }

    public static String go() {
        Scanner input = new Scanner(System.in);
//        System.out.println("Would you like to continue? (y/n)");
        System.out.println("(y/n)");
        String response = input.next();
        if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")) {
            return response;
        } else {
            return go();
        }
    }

    public static int getInteger(int x, int y) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Please enter a number between %d and %d:", x, y);
        int numInput = input.nextInt();
        if (numInput < x || numInput > y) {
            return getInteger(x, y);
        }
        return numInput;
    }

    public static int difficulty() {
        Scanner input = new Scanner(System.in);
        System.out.println("Easy, Medium, or High Difficulty?: (e/m/h)");
        String response = input.next();
        int points;
        if (response.equalsIgnoreCase("e")) {
            points = 60;
        } else if (response.equalsIgnoreCase("m")) {
            points = 45;
        } else if (response.equalsIgnoreCase("h")) {
            points = 30;
        } else {
            return difficulty();
        }
        return points;
    }

    public static int potions(int x) {

        if (x == 60) {
            return 3;
        } else if (x == 45) {
            return 2;
        } else {
            return 0;
        }
    }

    public static String attr(int points) {
        System.out.printf("\nYou have %d stat points.\n\nEach attribute must have 1pt.\n\nPlease use them wisely.\n\n", points);
        int hlth = health(points);
        points -= hlth;
        int att = attack(points);
        points -= att;
        int def = points;
        String output = "";
        output += hlth + " " + att + " " + def + "\n";
        System.out.printf("\nYour Stats:    %d : Health, %d : Attack, %d : Defense\n", hlth, att, def);
        return output;
    }

    public static String enemy(int difficulty) {
        int eStats = 0;
        if (difficulty == 60) {
            eStats = difficulty / 2;
        } else if (difficulty == 30) {
            eStats = difficulty * 2;
        } else if (difficulty == 45) {
            eStats = difficulty;
        }

        int hlth = (int) (Math.random() * (eStats - 2)) + 1;
        eStats -= hlth;
        int attk = (int) (Math.random() * (eStats - 1)) + 1;
        eStats -= attk;
        int def = eStats;
        System.out.printf("\"Enemy HLTH: %d, ATTK: %d, DEF: %d\n", hlth, attk, def);
        return hlth + " " + attk + " " + def;
    }

    public static int rollDice() {
        return (int) (Math.random() * 12) + 1;
    }

    public static int defense(int hero, int enemy) {
        int rollOne = rollDice();
        int rollTwo = rollDice();

        System.out.printf("\nYou rolled a %d, your enemy rolled a %d\n\n", rollOne, rollTwo);
        int att = enemy * rollOne / 6;
        int def = hero * rollTwo / 4;

        if (def - att > 0) {
            return 0;
        } else {
            return def - att;
        }
    }

    public static int attack(int hero, int enemy) {
        int rollOne = rollDice();
        int rollTwo = rollDice();

        System.out.printf("\nYou rolled a %d, your enemy rolled a %d\n", rollOne, rollTwo);
        int att = hero * rollOne / 6;
        int def = enemy * rollTwo /6;

        if (def - att > 0) {
            return 0;
        } else {
            return def - att;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String response, attributes, eAttributes;

        do {
            int hlth, att, def, eHlth, eAtt, eDef;

            System.out.println("\nYou awake from a dreamless sleep in a tavern.");
            System.out.println("Continue?");
            response = go();
            if (response.equalsIgnoreCase("n")) {
                System.out.println("Thank you for playing.");
            } else {
                System.out.println("\nHello! My name is Falstaff.\n\nWhat is your name traveler?:");
                String name = input.nextLine();

                System.out.println(greeting(name) + "\n\nGallidor needs help to defeat the evil forces of Covid 19.");
                System.out.println("Continue");
                response = go();
            }


            int points = difficulty();
            int diff = points;
            if (response.equalsIgnoreCase("n")) {
                System.out.println("Thank you for playing.");
            }

            attributes = attr(points);

            String[] numbers = attributes.trim().split(" ");
            int[] integers = new int[numbers.length];
            for (int i = 0; i < integers.length; i++) {
                integers[i] = Integer.parseInt(numbers[i]);
            }

            hlth = integers[0];
            att = integers[1];
            def = integers[2];

            response = go();

            if (response.equalsIgnoreCase("n")) {
                System.out.println("Thank you for playing.");
            }

            System.out.println("Very Well!");
            int potions = potions(diff);
            System.out.printf("\nHere are some potions to help you.\n\nYou received %d potions\n\n", potions);

            if (response.equalsIgnoreCase("n")) {
                System.out.println("Thank you for playing.");
            }

            System.out.println("You step out of the Tavern where you are immediately attacked.");

            eAttributes = enemy(points);

            String[] eNumbers = eAttributes.trim().split(" ");
            int[] eIntegers = new int[eNumbers.length];
            for (int i = 0; i < eIntegers.length; i++) {
                eIntegers[i] = Integer.parseInt(eNumbers[i]);
            }

            eHlth = eIntegers[0];
            eAtt = eIntegers[1];
            eDef = eIntegers[2];


            do {
                System.out.println("What do you do? ('y' = ATTACK/ 'n' = DEFEND)");
                response = go();
                if (response.equalsIgnoreCase("n")) {
                    System.out.println("You chose to defend.  Defense + 25%");
                    def *= 1.25;

                    int deduction = defense(def, eAtt);

                    hlth += deduction;

                    System.out.println("Stats:" + hlth + " | " + att + " | " + def);
                    System.out.println("Enemy Stats:" + eHlth + " | " + eAtt + " | " + eDef);


                } else {
                    System.out.println("You slash with your sword.");

                    int deduction = attack(att, eDef);

                    eHlth += deduction;

                    System.out.println("Stats:" + hlth + " | " + att + " | " + def);
                    System.out.println("Enemy Stats:" + eHlth + " | " + eAtt + " | " + eDef);
                }
            } while (eHlth > 0);

            System.out.println("Continue?");

        } while (response.equalsIgnoreCase("y"));

    }
}
