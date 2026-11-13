package browserhistory;

public class DeletedPage {
	private Node page;
	private int originalIndex;
	private boolean wasCurrent;
	
	public DeletedPage(Node page, int originalIndex, boolean wasCurrent) {
		this.page = page;
		this.originalIndex = originalIndex;
		this.wasCurrent = wasCurrent;
	}
	
	public Node getPage() {
        return page;
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    public boolean wasCurrent() {
        return wasCurrent;
    }
    

}
