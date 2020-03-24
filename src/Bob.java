import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exit = "bye";
        String responseQ = "Sure.";
        String responseE = "Whoa, chill out!";
        String responseN = "Fine, be that way!";
        String responseElse = "Whatever.";
        boolean again = true;


        do {
        String input = scanner.nextLine().trim();
            if (input.toLowerCase().equals(exit)){
                again = false;
            } else if (input.endsWith("?")) {
                System.out.println(responseQ);
            } else if (input.endsWith("!")) {
                System.out.println(responseE);
            } else if (input.equals("")) {
                System.out.println(responseN);
            } else {
                System.out.println(responseElse);
            }
        } while (again);
    }
}
