/**
 * An implementation of the List ADT using
 * a linked list.  Specifically, this implementation
 * only allows a List to contain Comparable items.
 *
 * @author Sneha Narayan
 * @author Eric Alexander
 * @author Titus Klinge
 * @author Layla Oesper
 * @author Jeff Ondich
 * @author Alice Cutter (student)
*/

/* Note <E extends Comparable<E> means this container
 * can only old objects of type E that are Comparable.
 */
public class RecursiveLinkedList<E extends Comparable<E>>
{

    /* Internal Node class used for creating linked objects.
    */
    private class Node
    {
        private E data;
        private Node next;

        private Node(E dataItem)
        {
            data = dataItem;
            next = null;
        }

        private Node(E dataItem, Node nextNode)
        {
            data = dataItem;
            next = nextNode;
        }

    } // End Node class

    //Instance variables for RecursiveLinkedList
    private Node head;
    private int numItems;

    /**
     * Creates an empty RecursiveLinkedList
     */
    public RecursiveLinkedList()
    {
        head = null;
        numItems = 0;
    }

    /**
     * Returns the data stored at positon index.
     * @param index
     * @return The data stored at position index.
     */
    public E get(int index)
    {
        if (index < 0 || index >= numItems)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node node = getNode(index);
        return node.data;
    }

    /*
     * Helper method that retrieves the Node stored at
     * the specified index.
     */
    private Node getNode(int index)
    {
        Node node = head;
        for (int i = 0; i < index && node != null; i++)
        {
            node = node.next;
        }
        return node;
    }

    /**
     * Removes and returns the data stored at the specified index.
     * @param index The position of the data to remove.
     * @return The data previously stored at index position.
     */
    public E remove(int index)
    {
        if (index < 0 || index >= numItems)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (index == 0){
            return removeFirst();
        } else
        {
            Node before = getNode(index - 1);
            return removeAfter(before);
        }
    }

    /*
     * Helper method that removes the Node after the
     * specified Node. Returns the data that was
     * stored in the removed node.
     */
    private E removeAfter(Node node)
    {
        Node temp = node.next;
        if (temp != null)
        {
            node.next = temp.next;
            numItems--;
            return temp.data;
        } else
        {
            return null;
        }
    }

    /*
     * Helper method that removes the first Node in
     * the Linked List.  Returns the data that was
     * stored in the removed node.
     */
    private E removeFirst()
    {
        Node temp = head;
        if (head != null)
        {
            head = head.next;
        }

        if (temp != null)
        {
            numItems--;
            return temp.data;
        } else
        {
            return null;
        }
    }

    /**
     * Adds the data to the list at the specified index.
     * @param index The position to add the data.
     * @param anEntry The particular data to add to the list.
     */
    public void add(int index, E anEntry)
    {
        if (index < 0 || index > numItems)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0)
        {
            addFirst(anEntry);
        } else
        {
            Node node = getNode(index - 1);
            addAfter(node, anEntry);
        }
    }

    /*
     * Helper method that adds anEntry to the first
     * position in the list.
     */
    private void addFirst(E anEntry)
    {
        head = new Node(anEntry, head);
        numItems++;
    }

    /*
     * Helper method that adds anEntry after the
     * specified Node in the linked list.
     */
    private void addAfter(Node before, E anEntry)
    {
        before.next = new Node(anEntry, before.next);
        numItems++;
    }

    /**
     * Add the specified data to the end of the list.
     * @param anEntry The data to add to this list.
     */
    public boolean add(E anEntry)
    {
        add(numItems, anEntry);
        return true;
    }

    /**
     * Returns the size of the list in terms of items stored.
     * @returns the number of items in the list.
     */
    public int size()
    {
        return numItems;
    }

    /**
     * Modifies the list so the specified index now
     * contains newValue (overwriting the old data).
     * @param index The position int he list to add data.
     * @param newValue The data to place in the list.
     * @return The previous data value stored at index.
     */
    public E set(int index, E newValue)
    {
        if (index < 0 || index >= numItems)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }

    /**
     * A string representation of the List.
     * @returns A string representation of the list.
     */
    public String toString()
    {
        String s = "[";
        Node temp = head;
        for (int i = 0; i < numItems; i++)
        {
            s = s + temp.data.toString();
            if (i < numItems - 1)
            {
                s = s + ", ";
            }
            temp = temp.next;
        }
        s = s + "]";
        return s;
    }

    /**
     * Return the maximum element in the list using
     * compareTo() method of Comparable.
     *
     * @return maximum element of the list
     **/
    public E max()
    {

			return maxHelper(head.data, head); // calls the maxHelper method 
		
    }
	/**
		@param curMax: holds the data that all the other node data is compared to and is the highest 
		@param currNode: the current node being looked at 

		**/
		public E maxHelper (E curMax, Node currNode){
			if(currNode.data.compareTo(curMax)> 0){
				curMax = currNode.data;// sets curMax to hold the data
			}
			if (currNode.next != null){ 
				return maxHelper (curMax, currNode.next);// This is the recursive call!  
			}
			// once you are at the end 
				return curMax; 
		}
			
		

    /**
     * Remove all elements that match element using the
     * equals() operator to determine a match.
     * (Don't use ==).
     *
     * @param element The element that should be removed
     **/
    public void removeAll(E element)
    {	
			removeAllHelper(element, head); // removes all the middle nodes 
			if (getNode(numItems-1).data.compareTo(element ) == 0){ // removes the last node if it needs to be removed 
				remove(numItems-1);
				return;  
			}	
			if (head.data.compareTo(element) == 0){ // gets the first node and detaches it if needs to be removed. 
				removeFirst(); 
			}

		
			 
    }

		/**
		* Is the helper method for removeAll. Goes through the linked list checking if the nodes need to be removed
		* @param element: the element that will be removed from the list 
		*	@param curNode: the node that the opperations are currently being done on 
		**/
		public void removeAllHelper(E element, Node curNode){
			// if the node's data should be removed: 
			// Think about the first node as another base case
   		 if (curNode == null){ // this is also a base case
        return; 
      }
			 	if (curNode.next == null){ // pretty sure this is the base case 
      	return; 
      }	
			if (curNode.next.data.compareTo(element) == 0){
				removeAfter(curNode);

			}
			
			
			if (curNode.next != null){
			 removeAllHelper(element, curNode.next);

			}
	
}
    /**
     * Duplicate each element of the list
     *
     * For example, the list [ 0 1 2 ] duplicated becomes
     * [ 0 0 1 1 2 2 ]
     **/
    public void duplicate()
    {
				if (head == null){// if the list is empty then it returns null because there is nothing to duplicate
					return; 
				}
        if (numItems ==2){// the main recursive method is a little funky for short lists. 
					addAfter(head, head.data); // adds the first item after the head 
					addAfter(getNode(numItems-1), getNode(numItems-1).data); // adds the second item at end of list
					return; 
				}
				if (numItems == 1){
					Node temp = head; 
					addAfter(head, head.data); // duplicates the head
					return; 
				}
				if (numItems > 2){// this is what the majority of the calls will use. If it's a "normal list" or a longer one. 
					duplicateHelper(head); 
					Node temp1 = getNode(numItems-2); 
					addAfter(getNode(numItems-2), temp1.data); 
					Node temp2 = getNode(numItems-1); 
					addAfter(getNode(numItems-2), temp2.data); 
				}
				
		}
		/**
		*This method is the helper for duplicate
		* @ param curNode: The current node that is being duplicated. 
		**/
		public void duplicateHelper(Node curNode){
			if (curNode == null){
				return; 
			}
			if (curNode.next == null){
				E tempData = curNode.data; 
				addAfter(curNode, tempData); 
			}
			if (curNode.next.next != null){
				E tempData = curNode.data; 
				addAfter(curNode, tempData);
				duplicateHelper(curNode.next.next); 
			}
			return; 
		}

    private void printHelper(Node node){ // TEST -- for grading
      if(node == null){
        return;
      }
      System.out.println(node.data);
      printHelper(node.next);
    }

    /**
     * Here are a couple short tests. You should
     * should make sure to thoroughly test your code.
     */
    public static void main(String[] args)
    {
        RecursiveLinkedList<String> l1 = new RecursiveLinkedList<String>(); // l1 is the test set for a normal list of length 5
				l1.add("a"); 
				l1.add("c"); 
				l1.add("b");
				l1.add("c"); 
				l1.add("1");
				System.out.println("List1 is: " + l1);System.out.println("The max of l1 is "+ l1.max());l1.removeAll("c");
				System.out.println("l1 with \" c \" removed "+ l1 );
				 l1.duplicate();
				System.out.println("l1 duplicated is " + l1); 

				RecursiveLinkedList<String> l2 = new RecursiveLinkedList<String>(); // l2 is a test to show what happens when the list is very short 
				l2.add ("zop"); 
				System.out.println("l2 is " + l2); 
				l2.duplicate(); 
				System.out.println("l2 duplicated is " + l2); 
				l2.removeAll("zop"); 
				System.out.println("l2 with \" zop \" removed is " + l2); 

				RecursiveLinkedList<String> l3 = new RecursiveLinkedList<String>(); // l3 is to show what happens when the list is empty. 
				System.out.println("l3 is " + l3);
				l3.duplicate(); 
				System.out.println("l3 duplicated is " + l3);
				
				// TESTS -- grading
        // System.out.println("TEST empty max: " + l1.max());
        // l1.add("hello");
        // l1.printHelper(l1.head);
        // l1.add("world");
        // // l1.add("a");
        // l1.add("computer");
        // // l1.add("a");
        // // l1.add("a");
        // l1.add("science");
        // l1.duplicate();
        // l1.printHelper(l1.head);
        // l1.printHelper(l1.head);
        // System.out.println("TEST single max: " + l1.max());

        // l1.removeAll("a");
        // l1.printHelper(l1.head);
        // l1.printHelper(l.head);
      }
}
