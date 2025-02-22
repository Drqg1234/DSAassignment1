// 

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        BrowswerNavigation br = new BrowswerNavigation();
        Scanner file = new Scanner(System.in);

        while (true){
            System.out.println("\nWeb Browser Navigation System");
            System.out.println("1: Visit a Website");
            System.out.println("2: Go Back");
            System.out.println("3: Go Forward");
            System.out.println("4: Show Browsing History");
            System.out.println("5: Clear Browsing History");
            System.out.println("6: Close Browser and Save Current Session");
            System.out.println("7: Restore Last Session");
            System.out.println("8. Exit");
            System.out.println("Choose a number:");
            int choice = file.nextInt();
            file.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter URL");
                    String url = file.nextLine();
                    System.out.println(br.visitWebsite(url));
                    break;
                case 2:
                    System.out.println(br.goBack());
                    break;
                case 3:
                    System.out.println(br.goForward());
                    break;
                case 4:
                    System.out.println(br.showHistory());
                    break;
                case 5:
                    System.out.println(br.clearHistory());
                    break;
                case 6:
                    System.out.println(br.closeBrowser());
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
