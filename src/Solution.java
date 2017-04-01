import java.util.Vector;

public class Solution implements Comparable<Solution>{
	public int strength;
	public Vector<Person> ungrouped;
	public Vector<String> identifiers;
	public Vector<PersonPair> pairs;
	
	public Solution() {
		pairs = new Vector<>();
		identifiers = new Vector<>();
		ungrouped = new Vector<>();
	}
	@Override
	public int compareTo(Solution other) {
		// TODO Auto-generated method stub
		return -(Integer.compare(this.strength, other.strength));
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String pairsString = "";
		for(PersonPair pair : pairs){
			pairsString += pair.toString();
		}
		return "Strength: " + strength + pairsString + returnUngrouped() + "\n----------------------------";
	}
	String returnUngrouped(){
		if(ungrouped.size() > 0){
			String ungroupedString = "\n" +  ungrouped.size() + " people could not be grouped: \n\t";
			for(Person person : ungrouped){
				ungroupedString += person.toString() + "\n\t";
			}
			return ungroupedString;
		}else{
			return "Everyone was grouped.";
		}
	}
	public void addPair(PersonPair pair){
		pairs.add(pair);
		strength += pair.strength;
		identifiers.add(pair.identifier);
	}
	public void addUngrouped(Person person){
		ungrouped.add(person);
	}
}
