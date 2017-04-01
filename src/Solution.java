import java.util.Vector;

public class Solution implements Comparable<Solution>{
	public int strength;
	public Vector<Person> ungrouped;
	public Vector<String> identifiers;
	public Vector<PersonGroup> groups;
	
	public Solution() {
		identifiers = new Vector<>();
		ungrouped = new Vector<>();
		groups = new Vector<PersonGroup>();
	}
	public Solution(Solution solution){
		identifiers = new Vector<>(solution.identifiers);
		ungrouped = new Vector<>(solution.ungrouped);
		groups = new Vector<PersonGroup>(solution.groups);
	}
	@Override
	public int compareTo(Solution other) {
		// TODO Auto-generated method stub
		return -(Integer.compare(this.strength, other.strength));
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String groupString = "";
		for(PersonGroup group : groups){
			groupString += group.toString();
		}
		return "Strength: " + strength + groupString + returnUngrouped() + "\n----------------------------";
	}
	String returnUngrouped(){
		if(ungrouped.size() > 0){
			String ungroupedString = "\n" +  ungrouped.size() + " people could not be grouped: \n\t";
			for(Person person : ungrouped){
				ungroupedString += person.toString() + "\n\t";
			}
			return ungroupedString;
		}else{
			return "\nEveryone was grouped.";
		}
	}
	public void addGroup(PersonGroup group){
		groups.add(group);
		strength += group.strength;
		identifiers.add(group.identifier);
	}
	public void addUngrouped(Person person){
		ungrouped.add(person);
	}
}
