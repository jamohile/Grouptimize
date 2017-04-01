import java.util.Vector;

public class Person {
	static int lastID = 0;
	int id;
	boolean available = true;
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	String name;
	Vector<Choice> choices;

	public Person(String name) {	
		this.name = name;
		lastID += 1;
		this.id = lastID;
		choices = new Vector<Choice>();
	}
	public void addChoice(Choice choice){
		choices.add(choice);
	}
	public int getChoiceStrength(Person person){
		for(Choice choice : choices){
			if(choice.getPerson() == person){
				return choice.getStrength();
			}
		}
		return -1;	
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
