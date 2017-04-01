import java.util.Vector;

public class PersonGroup extends PersonContainer {
	int strength;
	String identifier;

	public PersonGroup(PersonContainer people) {
		this.people = people.getPeople();
		this.strength = getRawStrength();
		//this.strength = scaleStrength();
		this.identifier = generateIdentifier();


	}
	int getRawStrength(){
		int rawStrength = 0;
		for(Person person : people){
			Vector<Person> otherPeople = new Vector<>(people);
			otherPeople.remove(person);
			for(Person otherPerson : otherPeople){
				rawStrength += person.getChoiceStrength(otherPerson);
			}
		}
		return rawStrength;
	}
	int scaleStrength(){
		return (int) (strength / (3 * (Math.pow(2, people.size() - 2))));
	}
	String generateIdentifier(){
		people.sort(null);
		String identifier = "";
		for(Person person : people){
			identifier += String.valueOf(person.id) + "x";
		}
		return identifier;
	}
	@Override
	public String toString() {
		String groupString = "\n\t";
		for(Person person : people){
			groupString += person.name;
					if(!people.lastElement().equals(person)){
						groupString += "---";
					}else{
						groupString += "(" + strength + ")";
					}
		}
		return groupString;
	}

}
