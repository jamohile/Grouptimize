import java.util.Vector;

public class PersonPair {
	Vector<Person> persons;
	int strength;
	String identifier;
	public PersonPair(Person one,Person two) {
		persons = new Vector<>();
		persons.add(one);
		persons.add(two);
		strength = one.getChoiceStrength(two) + two.getChoiceStrength(one);
		identifier = String.valueOf(Integer.min(one.id, two.id)) + "x" + String.valueOf(Integer.max(one.id, two.id)) +"x";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n\t\t" + persons.elementAt(0) + "---" + persons.elementAt(1) + "(" + strength + ")";
	}
}
