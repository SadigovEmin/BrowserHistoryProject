package tests;
import browserhistory.BrowserHistory;
public class BrowserHistoryTest {
	public static void main(String[] args) {
		BrowserHistory browser = new BrowserHistory("Google");
		browser.visit("Youtube");
		browser.visit("Github");
		browser.visit("Linkedin");
		
		System.out.println("Test 1: Visited pages");
		browser.display_history();
		System.out.println("\n \n \n");
		
		System.out.println("Test 2: Exceeding maximum capacity");
		BrowserHistory newBrowser = new BrowserHistory("Home");
		for(int i=1; i <= 60; i++) {
			newBrowser.visit("Page " + i);
		}
		newBrowser.display_history();
		System.out.println("History size: " + newBrowser.getHistorySize());
		System.out.println("\n \n \n");
		
		System.out.println("Test 3: Testing Back");
		System.out.print("Initial history: ");
		browser.display_history();
		String lastPage = browser.back(2);
		System.out.println("Last stopped page after going back 2: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		
		lastPage = browser.back(0);
		System.out.println("Last stopped page after going back 0: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		
		lastPage = browser.back(100);
		System.out.println("Last stopped page after going back 100: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		try {
			System.out.println("Going negative steps back: ");
			lastPage = browser.back(-5);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.print("History: ");
			browser.display_history();
		}
		System.out.println("\n \n");
		
		System.out.println("Test 4: Testing Forward");
		System.out.print("Initial history: ");
		browser.display_history();
		lastPage = browser.forward(2);
		System.out.println("Last stopped page after going forward 2: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		
		lastPage = browser.forward(0);
		System.out.println("Last stopped page after going forward 0: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		
		lastPage = browser.forward(100);
		System.out.println("Last stopped page after going forward 100: " + lastPage);
		System.out.print("History: ");
		browser.display_history();
		System.out.println();
		try {
			System.out.println("Going negative steps forward: ");
			lastPage = browser.back(-5);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.print("History: ");
			browser.display_history();
		}
		System.out.println("\n \n");
		
		System.out.println("Test 5: Branching history");

		BrowserHistory branchTest = new BrowserHistory("Google");

		branchTest.visit("YouTube");
		branchTest.visit("GitHub");
		branchTest.visit("Reddit");

		System.out.println("Original history:");
		branchTest.display_history();
		
		System.out.println("2 Pages back....");
		branchTest.back(2);
		branchTest.display_history();

		branchTest.visit("Stack Overflow");

		System.out.println("After visiting Stack Overflow:");
		branchTest.display_history();
		
		System.out.println("\nForward 1 page after branching:");
		System.out.println("Current page: " + branchTest.forward(1));
		branchTest.display_history();
		
	}
}
