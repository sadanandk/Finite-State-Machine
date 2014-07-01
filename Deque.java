import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int N;               // number of elements on queue
    
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue

    
// Interface to be tested....

//  public Deque()                           // construct an empty deque
//  public boolean isEmpty()                 // is the deque empty?
//  public int size()                        // return the number of items on the deque
//  public void addFirst(Item item)          // insert the item at the front
//  public void addLast(Item item)           // insert the item at the end
//  public Item removeFirst()                // delete and return the item at the front
//  public Item removeLast()                 // delete and return the item at the end
//  public Iterator<Item> iterator()         // return an iterator over items in order from front to end
//  public static void main(String[] args)   // unit testing
//

    
    
    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;   // next in Q
        private Node<Item> previous; // previous in Q
    }

    /**
     * Initializes an empty queue.
     */
    public Deque() {
        first = null;
        last  = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return N==0;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return N;     
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
/*    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
*/
    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void addLast(Item item) {
    	
        if (item == null)
            throw new java.lang.NullPointerException();

    	
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }

    
    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void addFirst(Item item) {
    	
        if (item == null)
            throw new java.lang.NullPointerException();
    	
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (isEmpty()) last = first;
        else           oldfirst.previous = first;
        N++;
    }
    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item removeFirst() {
        if (isEmpty()) 
        	throw new NoSuchElementException("Queue underflow");
        
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        else{
        	first.previous = null;  // node to be GCCd
        }
       
        return item;
    }
    
    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item removeLast() {
        
    	if (isEmpty()) 
        	throw new NoSuchElementException("Queue underflow");
        
        Item item = last.item;
        last = last.previous;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        else
        {
        	last.next = null;
        }
        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
/*    private String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    } 
*/
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the <tt>Queue</tt> data type.
     */
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) q.enqueue(item);
//            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
//        }
//        StdOut.println("(" + q.size() + " left on queue)");
     
        q.addLast("5");
        q.addLast("4");
        q.addLast("3");
        q.addLast("2");
        q.addLast("1");
        q.addLast("0");
        
        q.addFirst("9");
        q.addFirst("8");
        q.addFirst("7");
        q.addFirst("6");
        
        
        q.removeFirst();
        q.removeLast();
        q.removeLast();
        q.removeFirst();
        
        // Test Iteratr
        
        Iterator test = q.iterator();
        
        while (test.hasNext()){
         System.out.println("value  is " + (String)test.next());
        }
        
        
    } // end of main methiod used here for testing.... 
    
 }
