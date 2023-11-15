/**
 * Counter class is used to calculate the number of points from a Cribbage hand.
 * @author Kevin Wu kwu347
 */
public class Counter {
	private PowerSet<Card> cardps;
	private Card starter;
	/**
	* Constructor to initialize the Counter object with a given hand and starter card.
	* @param hand An array of cards representing the hand to be scored
	* @param starter The starter card to be added to the hand for scoring
	*/
	public Counter(Card[] hand, Card starter) {
		this.starter = starter;
		cardps = new PowerSet<Card>(hand);
	}
	/**
	* Method to calculate the number of points for the hand that was sent into the constructor.
	* @return An integer representing the total number of points scored for the hand
	*/
	public int countPoints() {
		int points = 0;
		points += countPairs();
		points += countFifteens();
		points += countRuns();
		points += countFlush(cardps.getSet(31));
		points += countKnobs(cardps.getSet(31));
		return points;
	}
	/**
	* Helper method to count the number of knobs in the hand.
	* @return An integer representing the total number of points scored for pairs
	*/
	private int countKnobs(Set<Card> set) {
        for (int i = 1; i < set.getLength(); i++) {
        	//gets the suit of starter card
    		String starterSuit = starter.getSuit();
    		// if suit of card is the same as stater and the card is a jack give one point
        	if(set.getElement(i).getLabel() == "J" && set.getElement(i).getSuit() == starterSuit) {
        		return 1;
        	}
        }
        return 0;
	}
	/**
	* Helper method to count the number of flushes in the hand.
	* @return An integer representing the total number of points scored for pairs
	*/
	private int countFlush(Set<Card> set) {
		int count = 0;
		//grabs the starter card suit
		String starterSuit = starter.getSuit();
        String suit = null;
        for (int i = 1; i < set.getLength(); i++) {
           if (i == 1) {
        	   suit = set.getElement(i).getSuit();
           }
           if (i > 2) {
        	   //checks if there is more than one suit and if so does the starter match the rest of the suits
        	   if(set.getElement(i).getSuit() != suit && (set.getElement(i).getSuit() != starterSuit || suit != starterSuit)){
        		   return 0;
        	   }
           }
           // if the stater matches the card count, if count is 4 then its 5 points
           if(set.getElement(i).getSuit() == starterSuit) {
        	   count++;
           }
        }
        if (count == 4) {
        	return 5;
        }
        return 4;
	}
	/**
	* Helper method to count the number of highest runs in the hand.
	* @return An integer representing the total number of points scored for pairs
	*/
	private int countRuns() {
		int[] runs = new int[cardps.getLength()];
		int runcount = 0;
		for (int i = 0; i < cardps.getLength(); i++) {
			Set<Card> set = cardps.getSet(i);
			if (set.getLength() >= 3) {
				//checks if the set is a run
				if(isRun(set)) {
					runs[runcount] = set.getLength();
					runcount++;
				}
			}
		}	
		int max = 0;
		int multiplier = 1;
		// checks for the longest run if multiple caclulate how many and add to points
		for (int num : runs) {
			if (num > max) {
				max = num;
				multiplier = 1;
			}
			else if (num == max) {
				multiplier ++;
			}
		}
		return max * multiplier;
	}
	/**
	* Helper method to count the number of 2-card pairs in the hand.
	* @return An integer representing the total number of points scored for pairs
	*/
	private int countPairs() {
		int points = 0; 
		//counts the number of pairs in the hand and adds to points
		for (int i = 0; i < cardps.getLength(); i++) {
			Set<Card> set = cardps.getSet(i);
			if (set.getLength() == 2) {
				if (set.getElement(0).getLabel() == set.getElement(1).getLabel()) {
					points += 2;
				}
			}
		}
		return points;
	}
	/**
	* Helper method to count the number of 15 sets there are in the hand.
	* @return An integer representing the total number of points scored for Fifteens
	*/
	private int countFifteens() {
		int points = 0;
		for (int i = 0; i < cardps.getLength(); i++) {
			Set<Card> set = cardps.getSet(i);
			int sum = 0;
			//checks if the sets in the hand add up to fifteen if so add 2 points per set
			for (int j = 0; j < set.getLength(); j++) {
				sum += set.getElement(j).getFifteenRank();
			}

			if (sum == 15) {
				points += 2;
			}
		}
		return points;
		
	}
	private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		
		if (n <= 2) return false; // Run must be at least 3 in length.
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}
}

