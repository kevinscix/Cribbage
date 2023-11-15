/**
 * This class creates a PowerSet given the array of elements from the user
 * @author Kevin Wu kwu347
 */
public class PowerSet<T> {
	private Set<T>[] set;
	/**
     * Constructor to create the PowerSet for a given set of elements.
     * @param elements The array of elements to create the Power Set from.
     */
	public PowerSet(T[] elements) {
        int numElements = elements.length;
        int numSubsets = (int)Math.pow(2, numElements);
        set = (Set<T>[]) new Set[numSubsets];
        for (int i = 0; i < numSubsets; i++) {
            set[i] = new Set<T>();
            String binary = Integer.toBinaryString(i);
            //left pad the string with space up to length of numElements then replace the spaces with 0s
            binary = String.format("%" + numElements + "s", binary).replace(' ', '0');
            for (int j = 0; j < binary.length(); j++) {
                if (binary.charAt(j) == '1') {
                    set[i].add(elements[j]);
                }
            }
            //System.out.println(set[i].toString());
        }
    }
	/**
     * Returns the number of items in the array (the number of sets in the Power Set).
     * @return The number of sets in the Power Set.
     */
    public int getLength() {
        return set.length;
    }
    /**
     * Returns the Set stored at index i of the array.
     * @param i The index of the set to return.
     * @return The set at index i.
     */
    public Set<T> getSet(int i) {
        return set[i];
    }
}
