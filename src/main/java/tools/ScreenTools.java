package main.java.tools;

public class ScreenTools {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void programTitle() {
        clearScreen();
        System.out.println("=================================");
        System.out.println("     " + AppConstants.APP_NAME);
        System.out.println("Developed by: " + String.join(", ", AppConstants.DEVELOPERS));
        System.out.println("=================================");
        System.out.println();
    }
}
