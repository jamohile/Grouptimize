
public class Choice {
	Person person;
	int strength;
	public int getStrength() {
		return strength;
	}

	public Choice(Person person, int strength) {
		this.person = person;
		this.strength = strength;
	}
	
	public Person getPerson() {
		return person;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "-" + person.name;
	}
}
