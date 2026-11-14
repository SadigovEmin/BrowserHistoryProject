package browserhistory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.time.LocalDateTime;

public class BrowserHistory {
	
	private DoublyLinkedList history;
	private Node currentPage;
	private DeletedHistoryStack deletedPages;
	
	private static final int MAX_CAPACITY = 50;
	
	public BrowserHistory(String homepageUrl) {
	    this(homepageUrl, homepageUrl);
	}
	
	public BrowserHistory(String homepageUrl, String homepageTitle) {
		history = new DoublyLinkedList();
		Node homePageNode = new Node(homepageUrl, homepageTitle);
		history.addLast(homePageNode);
		currentPage = homePageNode;
		deletedPages = new DeletedHistoryStack();
	} 
	
	public void visit(String url) {
	    visit(url, url);
	}
	
	public void visit(String url, String title) {
		Node newPage = new Node(url, title);
		if (currentPage == null) {
	        history.addLast(newPage);
	        currentPage = newPage;
	        return;
	    }
		while(currentPage.getNext() != null) {
			history.removeNode(currentPage.getNext());
		}
			
		history.addLast(newPage);
			
		currentPage = newPage;
		if(history.getSize() > MAX_CAPACITY) history.removeFirst(); //Capacity requirement
			
		
	}
	
	public void display_history() {
		Node current = history.getHead();
		while(current != null) {
			if(current == currentPage) {
				System.out.print("[<]");
			}
			System.out.print(current.getTitle() + " -> ");
			current = current.getNext();
		}
		System.out.println("NULL");
	}
	
	public String back(int steps) {
		if(steps < 0) {
			throw new IllegalArgumentException("Steps cannot be negative!");
			
		}
		for (int i = 0; i < steps; i++) {
			if(currentPage.getPrev() == null) {
				break;
			}
			currentPage = currentPage.getPrev();
		}
		return currentPage.getUrl();
	}
	
	public String forward(int steps) {
		if(steps < 0) {
			throw new IllegalArgumentException("Steps cannot be negative!");
		}
		for(int i = 0; i < steps; i++) {
			if(currentPage.getNext() == null) {
				break;
			}
			currentPage = currentPage.getNext();
		
		}
		return currentPage.getUrl();
	}
	
	public Node currentPage() {
		if(currentPage == null) {
			System.out.println("No current page. ");
			return null;
		}
		System.out.println("\nCurrent Page Information");
		System.out.println("----------------------------");
		System.out.println("URL: " + currentPage.getUrl());
		System.out.println("Title: " + currentPage.getTitle());
		System.out.println("Visit Time: " + currentPage.getVisitTime());
		return currentPage;
	}
	
	public void deleteHistory() {
		history.clear();
		currentPage = null;
		System.out.println("Browser history has successfully been deleted.");
		deletedPages.clear();
	}
	
	public void exportHistory(String filename) {
		try(BufferedWriter writer = Files.newBufferedWriter(
				Path.of(filename), StandardCharsets.UTF_8)){
			Node current = history.getHead();
			while(current != null) {
				String url = current.getUrl();
				String title = current.getTitle();
				String visitTime = current.getVisitTime().toString();
				String isCurrent = String.valueOf(current == currentPage);
				
				writer.write(url + "\t" + title + "\t" + visitTime + "\t" + isCurrent);
				writer.newLine();
				current = current.getNext();
			}
			System.out.println("History exported successfully.");
		}catch(IOException e){
			System.out.println("Error exporting history: " + e.getMessage());
		}
	}
	
	public void importHistory(String filename) {
		DoublyLinkedList importedHistory = new DoublyLinkedList();
		Node importedCurrentPage = null;
		boolean currentPageFound = false;
		
		try(BufferedReader reader = Files.newBufferedReader(
				Path.of(filename), StandardCharsets.UTF_8)){
			
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] record = line.split("\t", -1);
				if(record.length != 4) throw new IllegalArgumentException("Invalid record format");
				
				String url = record[0];
				String title = record[1];
				if(url.isBlank() || title.isBlank()) {
					throw new IllegalArgumentException("URL and title cannot be empty.");
				}
				LocalDateTime visitTime = LocalDateTime.parse(record[2]);
				
				if (!record[3].equals("true") && !record[3].equals("false")) {
					throw new IllegalArgumentException("Invalid current page flag.");
				}
				boolean isCurrent = Boolean.parseBoolean(record[3]);
				
				if(isCurrent && currentPageFound) {
					throw new IllegalArgumentException("Multiple current pages found.");
				}
				
				if(importedHistory.getSize() >= MAX_CAPACITY) {
					throw new IllegalArgumentException("History cannot exceed 50 pages.");
				}
				
				Node newPage = new Node(url, title, visitTime);
				importedHistory.addLast(newPage);
				if(isCurrent) {
					importedCurrentPage = newPage;
					currentPageFound = true;
				}
				
			}
			
			if(importedHistory.getSize() > 0 && !currentPageFound) {
				throw new IllegalArgumentException("No current page found in imported history.");
			}
			history = importedHistory;
			currentPage = importedCurrentPage;
			deletedPages.clear();
			System.out.println("History successfully imported.");
			
			
		}catch(IOException | RuntimeException e) {
			System.out.println("Error while importing history: " + e.getMessage());
		}
	}
	

	public int deleteUrl(String url) {
	
	    if (url == null) {
	        throw new IllegalArgumentException("URL cannot be null.");
	    }
	
	    Node current = history.getHead();
	    int index = 0;
	    int deletedCount = 0;
	
	    while (current != null) {
	
	        Node next = current.getNext();
	
	        if (current.getUrl().equals(url)) {
	
	            boolean wasCurrent = (current == currentPage);
	
	            Node previous = current.getPrev();
	
	            history.removeNode(current);
	
	            DeletedPage deletedPage =
	                    new DeletedPage(current, index, wasCurrent);
	
	            deletedPages.push(deletedPage);
	
	            if (wasCurrent) {
	                if (next != null) {
	                    currentPage = next;
	                } else {
	                    currentPage = previous;
	                }
	            }
	
	            deletedCount++;
	
	        } else {
	            index++;
	        }
	
	        current = next;
	    }
	
	    return deletedCount;
	}


public boolean restoreDeletedPage() {

    DeletedPage deletedPage = deletedPages.pop();

    if (deletedPage == null) {
        return false;
    }

    Node page = deletedPage.getPage();


    int index = Math.min(
            deletedPage.getOriginalIndex(),
            history.getSize()
    );

    history.insertAt(index, page);

    if (deletedPage.wasCurrent() || currentPage == null) {
        currentPage = page;
    }

    if (history.getSize() > MAX_CAPACITY) {

        Node removed = history.removeFirst();

        if (currentPage == removed) {
            currentPage = history.getHead();
        }
    }

    return true;
}


	
	
	
	public int getHistorySize() {
		return history.getSize();
	}
	

}
