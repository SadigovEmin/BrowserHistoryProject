package tests;
import browserhistory.BrowserHistory;
public class BrowserHistoryIOTest {
	public static void main(String[] args) {
		
		System.out.println("Test 1: Testing Exporting");
        BrowserHistory browser = new BrowserHistory(
                "https://www.google.com", "Google");

        browser.visit("https://www.youtube.com", "YouTube");
        browser.visit("https://github.com", "GitHub");
        browser.visit("https://www.reddit.com", "Reddit");

        browser.back(1);

        System.out.println("Current browser history:");
        browser.display_history();

        System.out.println("\nExporting history:");

        browser.exportHistory("browser_history.txt");
        
        System.out.println("\nTest 2: Testing Importing");
        BrowserHistory importedBrowser = new BrowserHistory(
                "https://nvidia.com", "NVIDIA");
        System.out.println("Before Importing: ");
        importedBrowser.display_history();
        System.out.println("\nImporting...");
        importedBrowser.importHistory("browser_history.txt");

        System.out.println("\nImported history:");
        importedBrowser.display_history();

        System.out.println("\nImported current page:");
        importedBrowser.currentPage();
    }
}
