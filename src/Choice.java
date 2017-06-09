
public class Choice {
	PersonGroup people;
	int strength;
	public int getStrength() {
		return strength;
	}
	//this function is used to create a Choice object based on an incoming group
	public Choice(PersonGroup people, int strength) {
		this.people = people;
		this.strength = strength;
	}
	//this function makes calling a choice with one person more efficient
	public Choice(Person person, int strength){
	    PersonGroup people = new PersonGroup();
		people.addPerson(person);
		Choice(people, strength);
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
