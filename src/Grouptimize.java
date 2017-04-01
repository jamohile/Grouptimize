import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Grouptimize {
	static Scanner in;
	static int numPeople;
	static int numChoices;
	static PersonContainer personContainer;
	static Vector<Solution> solutions;

	public static void main(String[] args) {
		show("Welcome to Grouptimize. This algorithm will sort induviduals into pairs to the best of its ability. In its current state, this program can only compute solutions for an EVEN number of people.");
		try {
			in = new Scanner(new FileReader("input2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		personContainer = new PersonContainer();
		solutions = new Vector<>();
		initialize();
		calculateBest();

	}

	public static void initialize() {
		numPeople = in.nextInt();
		numChoices = in.nextInt();
		// initialise people into network
		for (int x = 1; x <= numPeople; x++) {
			Person person = new Person(in.next());
			personContainer.getPeople().add(person);
		}
		// add connections between people in network
		for (int x = 1; x <= numPeople; x++) {
			Person person = personContainer.getPerson(in.next());
			for (int y = numChoices; y > 0; y--) {
				String name = in.next();
				Person personChoice = personContainer.getPerson(name);
				Choice choice = new Choice(personChoice, y);
				person.addChoice(choice);
			}
		}
	}

	public static void calculateBest() {
		for (Person startingPerson : personContainer.getPeople()) {
			// reset all people
			personContainer.resetAvailability();
			int startIndex = personContainer.getPeople().indexOf(startingPerson);
			int currentIndex = startIndex;

			Solution solution = new Solution();
			Boolean solutionFailed = false;
			for (int counter = 0; counter < numPeople; counter++) {
				// do stuff
				Person currentPerson = personContainer.getPeople().elementAt(currentIndex);
				// bump index
				if (currentIndex == personContainer.getPeople().size() - 1) {
					currentIndex = 0;
				} else {
					currentIndex += 1;
				}
				// do stuff
				if (!currentPerson.isAvailable()) {
					continue;
				}
				Boolean fulfilled = false;
				for (Choice choice : currentPerson.choices) {
					if (choice.person.isAvailable()) {
						PersonPair pair = new PersonPair(currentPerson, choice.person);
						solution.addPair(pair);
						currentPerson.setAvailable(false);
						choice.person.setAvailable(false);
						fulfilled = true;
						break;
					}
				}
				if (!fulfilled) {
					solutionFailed = true;
					break;
				}
			}
			if (!solutionFailed) {
				boolean redundant = false;
				solution.identifiers.sort(null);
				for (Solution prevSolution : solutions) {
					if (solution.identifiers.equals(prevSolution.identifiers)) {
						// solution already exists in a different permutation
						redundant = true;
						break;
					}
				}
				if (!redundant) {
					solutions.add(solution);
				}
			}
		}
		// calculation has finished
		solutions.sort(null);
		if (solutions.isEmpty()) {
			show("Sorry...no solution could be found for this sample");
		} else {
			int highestStrength = solutions.elementAt(0).strength;
			boolean seperatedBadChoices = false;
			show("RECCOMENDED\n======================================");
			for (Solution solution : solutions) {
				if(solution.strength != highestStrength && !seperatedBadChoices){
					seperatedBadChoices = true;
					show("NOT RECCOMENDED\n======================================");
				}
				show(solution);
			}
		}
	}

	public static void removeRedundancy() {
		for (Solution solution : solutions) {

		}
	}

	public static void show(Object msg) {
		System.out.println(msg);
	}
}
