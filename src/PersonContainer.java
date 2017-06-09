import java.util.Vector;

public class PersonContainer {
	protected Vector<Person> people;

	public PersonContainer(){
		people = new Vector<Person>();
	}
	public Vector<Person> getPeople(){
		return people;
	}
	public Person getPerson(String name){
		for(Person person : people){
			if(person.name.equals(name)){
				return person;
			}
		}
		return null;
	}
	public void addPerson(Person person){
	    people.add(Person);
	}
	public void addPeople(PersonContainer people){
		for(Person person : people){
			people.add(person);
		}
	}
	public Person elementAt(int index){
		return people.elementAt(index);
	}
	//returns the number of people in the container
	public int getSize(){
	    return people.size();
	}
	public void resetAvailability() {
		for(Person person : people){
			person.setAvailable(true);
		}
	}
}
