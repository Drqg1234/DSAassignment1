// The testing ground for the Web Browser Navigatin System. All methods and functionality can be tested within this class/running
// the program and following along in the console.

import java.util.Scanner;

public class Main {
    // Prompts the user if they want to visit the website or not
    public static void visitPage(Scanner file, BrowserNavigation br){
        System.out.println("Would you like to visit this page? (y/n)");
        String ans = file.next().toLowerCase();
        if (ans.equals("y")){
            br.openUrl(br.curPage);
        }
    }

    public static void main(String[] args) {
        BrowserNavigation br = new BrowserNavigation();
        Scanner file = new Scanner(System.in);

        while (true){
            System.out.println("\nWeb Browser Navigation System");
            System.out.println("1 -> Visit a Website");
            System.out.println("2 -> Go Back");
            System.out.println("3 -> Go Forward");
            System.out.println("4 -> Show Browsing History");
            System.out.println("5 -> Clear Browsing History");
            System.out.println("6 -> Close Browser and Save Current Session");
            System.out.println("7 -> Restore Last Session");
            System.out.println("8 -> Exit");
            System.out.println("Choose a number:");
            
            // Error handling for non integer inputs
            int choice = 0;
            try {
                choice = file.nextInt();
            } 
            catch (Exception e) {
                
            }
            file.nextLine();

            
            switch (choice){
                case 1:
                    System.out.println("Enter URL");
                    String url = file.nextLine();
                    System.out.println(br.visitWebsite(url));

                    visitPage(file, br);

                    break;
                case 2:
                    System.out.println("Moving Backwards.\n" + br.goBack());

                    visitPage(file, br);
                    
                    break;
                case 3:
                    System.out.println("Moving Forwards.\n" + br.goForward());

                    visitPage(file, br);

                    break;
                case 4:
                    System.out.println(br.showHistory());
                    break;
                case 5:
                    System.out.println(br.clearHistory());
                    break;
                case 6:
                    System.out.println(br.closeBrowser());
                    System.out.println("Exiting");
                    file.close();
                    System.exit(0);
                    break;
                case 7:
                    System.out.println(br.restoreLastSession());
                    break;
                case 8:
                    System.out.println("Exiting");
                    file.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again please.");
            }
        }
    }   
}
