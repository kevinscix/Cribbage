/**
 * This class creates a set based on LinearNodes created and added by the user
 * @author Kevin Wu kwu347
 */
public class Set<T> {
	private LinearNode<T> setStart;
	 /**
     * Creates an empty set.
     */
	public Set() {
		setStart = null;
	}
	/**
     * Adds the specified element to the set.
     *
     * @param element the element to add to the set
     */
	public void add(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
        // If the set is empty, set the start node to the new node
        if (setStart == null) {
            setStart = newNode;
        } else {
            // Else it will add the new node to the front of the list
            newNode.setNext(setStart);
            setStart = newNode;
        }
	}
	/**
     * Returns the number of elements in the set.
     *
     * @return the number of elements in the set
     */

	public int getLength() {
        int count = 0;
        LinearNode<T> currentNode = setStart;
        // Loops through the list and count the number of nodes

        while (currentNode != null) {
            currentNode = currentNode.getNext();
            count++;
        }
        return count;
    }
	
	/**
     * Returns the element stored in the ith node of the linked list.
     *
     * @param i the index of the node to get the element from
     * @return the element stored in the ith node of the linked list
     */
	public T getElement(int i) {
        //loops through the list until the ith node is found

        LinearNode<T> currentNode = setStart;
        for (int j = 0; j < i; j++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getElement();
        
        
    }
	
	 /**
     * Returns true if the set contains the specified element, false otherwise.
     *
     * @param element the element to check for in the set
     * @return true if the set contains the specified element, false otherwise
     */
	public boolean contains(T element) {
        LinearNode<T> currentNode = setStart;
        // loops through the list and check if the element is found

        while (currentNode != null) {
            if (currentNode.getElement().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }
	/**
     * Returns a string containing each of the elements in the set separated by a space.
     *
     * @return a string containing each of the elements in the set separated by a space
     */
	public String toString() {
		String sentence = "";
        LinearNode<T> currentNode = setStart;
        while (currentNode != null) {
        	sentence += String.format("%s ", currentNode.getElement().toString());
        	currentNode = currentNode.getNext();
        }
        return sentence;
	}
	
}

