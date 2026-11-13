
package browserhistory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BrowserHistory browser = new BrowserHistory(
                "https://google.com",
                "Google"
        );

        int choice;

        while (true) {

            System.out.println("\n================================");
            System.out.println("     BROWSER HISTORY MANAGER");
            System.out.println("================================");
            System.out.println("1. Visit a new page");
            System.out.println("2. Go back");
            System.out.println("3. Go forward");
            System.out.println("4. Display history");
            System.out.println("5. Show current page");
            System.out.println("6. Delete a URL");
            System.out.println("7. Restore deleted page");
            System.out.println("8. Delete all history");
            System.out.println("9. Export history");
            System.out.println("10. Import history");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Exiting Browser History Manager...");
                break;
            }

            switch (choice) {

                case 1:
                	System.out.print("Enter page URL: ");
                    String url = scanner.nextLine();

                    System.out.print("Enter page title: ");
                    String title = scanner.nextLine();

                    if (url.isBlank() || title.isBlank()) {
                        System.out.println("URL and title cannot be empty.");
                        break;
                    }

                    browser.visit(url, title);

                    System.out.println("Page visited successfully!");
                    break;

                case 2:
                	if (browser.getHistorySize() == 0) {
                	    System.out.println("Browser history is empty.");
                	    break;
                	}
                	System.out.print("How many steps do you want to go back? ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.nextLine();
                        break;
                    }

                    int backSteps = scanner.nextInt();
                    scanner.nextLine();

                    if (backSteps < 0) {
                        System.out.println("Steps cannot be negative.");
                        break;
                    }

                    String backResult = browser.back(backSteps);

                    System.out.println("Current page after going back: " + backResult);
                    break;

                case 3:
                	if (browser.getHistorySize() == 0) {
                	    System.out.println("Browser history is empty.");
                	    break;
                	}
                	System.out.print("How many steps do you want to go forward? ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.nextLine();
                        break;
                    }

                    int forwardSteps = scanner.nextInt();
                    scanner.nextLine();

                    if (forwardSteps < 0) {
                        System.out.println("Steps cannot be negative.");
                        break;
                    }

                    String forwardResult = browser.forward(forwardSteps);

                    System.out.println("Current page after going forward: " + forwardResult);
                    break;

                case 4:
                	System.out.println("\n--- Browser History ---");
                    browser.display_history();
                    System.out.println("Total pages: " + browser.getHistorySize());
                    break;

                case 5:
                    browser.currentPage();
                    break;

                case 6:
                	System.out.print("Enter the URL you want to delete: ");
                    String deleteUrl = scanner.nextLine();

                    if (deleteUrl.isBlank()) {
                        System.out.println("URL cannot be empty.");
                        break;
                    }

                    int deletedCount = browser.deleteUrl(deleteUrl);

                    if (deletedCount > 0) {
                        System.out.println(deletedCount + " page(s) deleted successfully!");
                    } else {
                        System.out.println("No matching URL found in history.");
                    }
                    break;

                case 7:
                	boolean restored = browser.restoreDeletedPage();

                    if (restored) {
                        System.out.println("Deleted page restored successfully!");
                    } else {
                        System.out.println("There are no deleted pages to restore.");
                    }
                    break;

                case 8:
                	System.out.print("Are you sure you want to delete all history? (yes/no): ");
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {
                        browser.deleteHistory();
                        System.out.println("All browser history deleted successfully!");
                    } else {
                        System.out.println("History deletion cancelled.");
                    }
                    break;

                case 9:
                	System.out.print("Enter the filename to export history: ");
                    String exportFilename = scanner.nextLine();

                    if (exportFilename.isBlank()) {
                        System.out.println("Filename cannot be empty.");
                        break;
                    }

                    browser.exportHistory(exportFilename);
                    break;

                case 10:
                    System.out.print("Enter the filename to import history: ");
                    String importFilename = scanner.nextLine();

                    if (importFilename.isBlank()) {
                        System.out.println("Filename cannot be empty.");
                        break;
                    }

                    browser.importHistory(importFilename);
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }

        scanner.close();
    }
}
