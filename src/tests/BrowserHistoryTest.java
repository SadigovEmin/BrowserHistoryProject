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
	}
}
