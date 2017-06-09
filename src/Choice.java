
public class Choice {
	PersonGroup people;
	int strength;
	public int getStrength() {
		return strength;
	}

	public Choice(PersonGroup people, int strength) {
		this.people = people;
		this.strength = strength;
	}
	
	public PersonGroup getPeople() {
		return people;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-" + people.toString();
	}
}
