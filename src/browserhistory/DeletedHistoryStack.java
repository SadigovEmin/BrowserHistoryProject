package browserhistory;

public class DeletedHistoryStack {

    private class StackNode {
        private DeletedPage data;
        private StackNode next;

        public StackNode(DeletedPage data) {
            this.data = data;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public DeletedHistoryStack() {
        top = null;
        size = 0;
    }

    public void push(DeletedPage page) {
        StackNode newNode = new StackNode(page);

        newNode.next = top;
        top = newNode;

        size++;
    }

    public DeletedPage pop() {
        if (top == null) {
            return null;
        }

        DeletedPage deletedPage = top.data;

        top = top.next;
        size--;

        return deletedPage;
    }

    public DeletedPage peek() {
        if (top == null) {
            return null;
        }

        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
    
    public void clear() {
        top = null;
        size = 0;
    }
}
