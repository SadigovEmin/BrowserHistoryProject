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
	
	private static final int MAX_CAPACITY = 50;
	
	public BrowserHistory(String homepageUrl, String homepageTitle) {
		history = new DoublyLinkedList();
		Node homePageNode = new Node(homepageUrl, homepageTitle);
		history.addLast(homePageNode);
		currentPage = homePageNode;
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
	
	public void currentPage() {
		if(currentPage == null) {
			System.out.println("No current page. ");
			return;
		}
		System.out.println("\nCurrent Page Information");
		System.out.println("----------------------------");
		System.out.println("URL: " + currentPage.getUrl());
		System.out.println("Title: " + currentPage.getTitle());
		System.out.println("Visit Time: " + currentPage.getVisitTime());
	}
	
	public void deleteHistory() {
		history.clear();
		currentPage = null;
		System.out.println("Browser history has successfully been deleted.");
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
		try(BufferedReader reader = Files.newBufferedReader(
				Path.of(filename), StandardCharsets.UTF_8)){
			history.clear();
			currentPage = null;
			
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] record = line.split("\t", -1);
				String url = record[0];
				String title = record[1];
				LocalDateTime visitTime = LocalDateTime.parse(record[2]);
				boolean isCurrent = Boolean.parseBoolean(record[3]);
				
				Node newPage = new Node(url, title, visitTime);
				history.addLast(newPage);
				if(isCurrent) {
					currentPage = newPage;
				}
				
			}
			System.out.println("History successfully imported.");
			
			
		}catch(IOException | RuntimeException e) {
			System.out.println("Error while importing history: " + e.getMessage());
		}
	}
	
	
	
	public int getHistorySize() {
		return history.getSize();
	}
	

}
