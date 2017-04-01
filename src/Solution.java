import java.util.Vector;

public class Solution implements Comparable<Solution>{
	public int strength;
	public Vector<String> identifiers;
	private Vector<PersonPair> pairs;
	
	public Solution() {
		pairs = new Vector<>();
		identifiers = new Vector<>();
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
		return "Strength: " + strength + pairsString + "\n----------------------------";
	}
	public void addPair(PersonPair pair){
		pairs.add(pair);
		strength += pair.strength;
		identifiers.add(pair.identifier);
	}
}
