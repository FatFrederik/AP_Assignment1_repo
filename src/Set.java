public class Set implements SetInterface {
	private static final int INITIAL_AMOUNT_OF_ELEMENTS = 20;

    public Identifier[] identifierArray;
    public int amountOfElements;

    public Set () {
    	identifierArray = new Identifier[INITIAL_AMOUNT_OF_ELEMENTS];
    	amountOfElements = 0;
    }
    
    private void copyElements (Identifier[] dest, Identifier[] src, int amount) {
    	for (int i = 0; i < amount; i++) {
    		dest[i] = new Identifier(src[i]);
    	}
    }

    public Set (Set src) {
    	identifierArray = new Identifier[src.identifierArray.length];
    	amountOfElements = src.amountOfElements;
    	copyElements(identifierArray, src.identifierArray, amountOfElements);
    }
    
	public void init () {
    	amountOfElements = 0;
    }

    public boolean isEmpty () {
    	return amountOfElements == 0;
    }

    public int size () {
    	return amountOfElements;
    }
    
    public Identifier getElement() {
    	//double randomNumber = (Math.random()*amountOfElements);
    	//int convertedNumber = (int)randomNumber;
    	//Identifier randomElement = identifierArray[convertedNumber];
		Identifier randomElement = identifierArray[amountOfElements-1];
    	return randomElement;
    }
    
    public boolean checkForPresence(Identifier element) {
    	
    	for (int i = 0; i < amountOfElements; i++) {
    		if(identifierArray[i].isIdentical(element)) {
    			return true;
    		}
    	} return false;
    }

	public void addElement (Identifier element) throws Exception {
    	if (checkForPresence(element)) {
    		return;
    	}
    	if (amountOfElements == MAX_ELEMENTS) {
    		throw new Exception("Maximum size of set reached, cannot add element");
    	}
    	identifierArray[amountOfElements] = new Identifier(element);
    	amountOfElements++;
    }
    
    private int getIndex(Identifier element) {
    	for (int i = 0; i < amountOfElements; i++) {
    		if(identifierArray[i].isIdentical(element)) {
    			return i;
    		}
    	} return 0;
    }
    
    public void removeElement(Identifier element) {
    	if (checkForPresence(element) == false) {
    		return;
    	}
		amountOfElements--;
    	for (int i = getIndex(element); i <= amountOfElements; i++) {
			identifierArray [i] = identifierArray [i + 1];
		}
    }
    
    public Set intersection(Set set2) {
    	
    	Set intersection = new Set();
    	
    	for(int i = 0; i < amountOfElements; i++) {
    		for(int j = 0; j < set2.amountOfElements; j++) {
    			
    			if(identifierArray[i].isIdentical(set2.identifierArray[j])) {
    				try {
						intersection.addElement(set2.identifierArray[j]);
					} catch (Exception e) {
					} 
    			}
    		}
    	}
    	return intersection;
    }
    
    
    public Set union(Set set2) throws Exception{
    	
    	int elementsInUnion = size() + set2.size() - intersection(set2).size();
    	
    	if(elementsInUnion > MAX_ELEMENTS) {
    		throw new Exception("Cannot give union of sets since contains too many elements");
    	}
    	Set union = new Set(set2);

    	for(int i = 0; i < amountOfElements; i++) {
			try{
				union.addElement(identifierArray[i]);
			} catch (Exception e){

			}
		}

    	return union;
    }
    
    public Set difference(Set set2) {
    	Set difference = new Set();
    	
    	for(int i = 0; i < amountOfElements; i++) {
    		for(int j = 0; j < set2.amountOfElements; j++) {
    			
    			if(identifierArray[i].isIdentical(set2.identifierArray[j]) == false) {
    				try {
						difference.addElement(identifierArray[i]);
					} catch (Exception e){
					}
    			}
    		}
    	} return difference;
    }



	public Set symmetricDifference(Set set2) throws Exception {
		Set intersection = intersection(set2);
		int elementsInSymmetricDifference = size() + set2.size() - 2*intersection.size();
    	
    	if(elementsInSymmetricDifference > MAX_ELEMENTS) {
    		throw new Exception("Cannot give symmetricDifference of sets since it contains too many elements");
    	}
		Set differenceSet1 = difference(set2);
		Set differenceSet2 = set2.difference(intersection);
    	Set symmetricDifference = differenceSet1.union(differenceSet2);

    	return symmetricDifference;
	}

	public String printSet() {
		String result = "";
		for (int i = 0; i < amountOfElements; ) {
			result = result.concat(identifierArray[i].print());
		}
		return result;
	}
}


