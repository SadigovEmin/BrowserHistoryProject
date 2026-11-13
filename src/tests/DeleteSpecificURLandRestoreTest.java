package tests;

import browserhistory.BrowserHistory;

public class DeleteSpecificURLandRestoreTest {
	public static void main(String[] args) {
		BrowserHistory browser = new BrowserHistory("https://google.com", "Google");
		browser.visit("https://youtube.com", "Youtube");
		browser.visit("https://github.com", "GitHub");
		browser.visit("https://reddit.com","Reddit");
		
		browser.back(1);
		
		System.out.println("Before Deletion of Specific Url");
		browser.display_history();
		
		int deletedCount = browser.deleteUrl("https://github.com");
		System.out.println("\nDeleted pages: " + deletedCount);
		
		System.out.println("\nAfter deletion: ");
		browser.display_history();
		
		System.out.println("History size: " + browser.getHistorySize());
		
		boolean restored = browser.restoreDeletedPage();

		System.out.println("\nWas page restored? " + restored);

		System.out.println("\nAfter restoration:");
		browser.display_history();

		System.out.println("\nCurrent page information:");
		browser.currentPage();

		System.out.println("\nHistory size: " + browser.getHistorySize());
		
		System.out.println("\nTest restoration from empty stack: ");
		restored = browser.restoreDeletedPage();

	    System.out.println("Restored: " + restored);
	    System.out.println("History size: " + browser.getHistorySize());
	    
	    System.out.println("\n\nTest Multiple Deletion and Restoration Test: ");
	    testMultipleDeletionsAndRestoration();
	    
	    System.out.println("\n\nTest Deletions of Duplicate Urls: ");
	    testDeleteDuplicateUrls();
	    
	    System.out.println("\n\nTest Restore at Maximum Capacity: ");
	    testRestoreAtMaximumCapacity();
	    
		
	}
	

	public static void testMultipleDeletionsAndRestoration() {

	    BrowserHistory browser = new BrowserHistory(
	            "https://google.com", "Google"
	    );
	
	    browser.visit("https://youtube.com", "YouTube");
	    browser.visit("https://github.com", "GitHub");
	    browser.visit("https://reddit.com", "Reddit");
	    browser.visit("https://stackoverflow.com", "Stack Overflow");
	
	    System.out.println("Original history:");
	    browser.display_history();

	    browser.deleteUrl("https://youtube.com");
	
	    browser.deleteUrl("https://reddit.com");
	
	    System.out.println("\nAfter deleting YouTube and Reddit:");
	    browser.display_history();
	
	    browser.restoreDeletedPage();
	
	    System.out.println("\nAfter first restoration:");
	    browser.display_history();
	

	    browser.restoreDeletedPage();
	
	    System.out.println("\nAfter second restoration:");
	    browser.display_history();
	
	    System.out.println("\nFinal history size: "
	            + browser.getHistorySize());
	}
	
	public static void testDeleteDuplicateUrls() {

	    BrowserHistory browser = new BrowserHistory(
	            "https://google.com", "Google"
	    );

	    browser.visit("https://youtube.com", "YouTube");
	    browser.visit("https://github.com", "GitHub");
	    browser.visit("https://youtube.com", "YouTube Again");
	    
	    System.out.println("Original History: ");
	    browser.display_history();

	    int deleted = browser.deleteUrl("https://youtube.com");

	    System.out.println("Deleted occurrences: " + deleted);
	    browser.display_history();

	    System.out.println("History size: " + browser.getHistorySize());

	    browser.restoreDeletedPage();

	    System.out.println("\nAfter restoring one time:");
	    browser.display_history();
	    
	    browser.restoreDeletedPage();
	    System.out.println("\nAfter restoring second time:");
	    browser.display_history();
	}
	

	public static void testRestoreAtMaximumCapacity() {

	    BrowserHistory browser = new BrowserHistory(
	            "https://google.com", "Google"
	    );
	
	    for (int i = 1; i <= 49; i++) {
	        browser.visit(
	                "https://example.com/page" + i,
	                "Page " + i
	        );
	    }
	
	    System.out.println("Initial size: "
	            + browser.getHistorySize());
	
	    browser.deleteUrl("https://example.com/page25");
	
	    System.out.println("After deletion: "
	            + browser.getHistorySize());
	
	    browser.visit("https://newpage.com", "New Page");
	
	    System.out.println("Before restoration: "
	            + browser.getHistorySize());
	    
	    boolean restored = browser.restoreDeletedPage();
	
	    System.out.println("Restored: " + restored);
	    System.out.println("Final size: "
	            + browser.getHistorySize());
	
	    System.out.println("Current page:");
	    browser.currentPage();
	
	    System.out.println("After going back: "
	            + browser.back(1));
	    browser.display_history();
	    System.out.println("Because of restoration the first node is deleted which is Google.");
	}


}
