import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Grouptimize {
	static Scanner in;
	static int numPeople;
	static int numPerGroup;
	static int numChoices;
	static PersonContainer personContainer;
	static Vector<Solution> solutions;
	static Vector<Flag> flags;

	//flags, declarations which will be implemented in the addFlags function
	//static boolean ACCEPT_UNSORTED = false; //if true, the system will include solutions where some people could not be sorted. 
	//static boolean ACCEPT_ZEROES = true; //if true, the solution will include solutions where some people have 0 strengths between them, if that guarantees the highest overall.

	public static void main(String[] args) {
		personContainer = new PersonContainer();
		solutions = new Vector<>();
		flags = new Vector<>();
		show("Welcome to Grouptimize. This algorithm will sort induviduals into pairs to the best of its ability. In its current state, this program can only compute solutions for an EVEN number of people.");
		addFlags();
		setupFlags(); //sets values for flags
		askYesNo("Would You like to run the calculation?");
		initialize(); //reads in data file, sets up connections
		calculateBest(); //calculates the optimal solution(s)
	}
	public static void addFlags(){
		flags.add(new Flag("ACCEPT_UNSORTED", "Would you like to see solutions where not everyone is sorted into a group, if that is the best overall solution found?"));
		flags.add(new Flag("ACCEPT_ZEROES", "Would you like to see solutions where some pairs have a strength of zero, if that is the best overall solution found?"));
	}
	public static void setupFlags(){
		show("Just a few questions to configure the algorithm:");
		for(Flag flag : flags){
			flag.value = askYesNo(flag.prompt);
		}
		show("Here are your current settings: ");
		for(Flag flag : flags){
			show(flag.toString());
		}
		boolean correct = askYesNo("Are these settings correct?");
		if(correct){
			show("Thank you, setup has completed.");
		}else{
			show("Alright, let's try again.");
			setupFlags();
		}
	}
	public static boolean askYesNo(String prompt){
		Scanner input = new Scanner(System.in);
		String response = "";
		show("\t" + prompt);
		show("\tEnter y or n, followed by ENTER: ", true);
		response = input.next();
		if(response.equals("n")){
			return false;
		}else{
			return true;
		}

	}
	public static void initialize() {
		try {
			in = new Scanner(new FileReader("input.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		numPeople = in.nextInt();
		numPerGroup = in.nextInt();
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
			Boolean solutionIncomplete = false;//indicates that there is a chance not all induviduals were sorted into groups
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
				PersonContainer tempContainer = new PersonContainer();
				tempContainer.getPeople().add(currentPerson);
				for (Choice choice : currentPerson.choices) {
					if (choice.person.isAvailable()) {
						tempContainer.getPeople().add(choice.person);
						currentPerson.setAvailable(false);
						choice.person.setAvailable(false);
						fulfilled = true;
						if (tempContainer.people.size() >= numPerGroup) {
							break;
						}
					}
				}
				if (fulfilled) {
					PersonGroup group = new PersonGroup(tempContainer);
					solution.addGroup(group);
				} else {
					solutionIncomplete= true;
				}
			}
			if ((solutionIncomplete && Flag.getFlagByName("ACCEPT_UNSORTED", flags).value) || !solutionIncomplete) {
				if (solutionIncomplete) {
					for (Person person : personContainer.getPeople()) {
						if (person.isAvailable()) {
							solution.addUngrouped(person);
						}
					}
				}
				boolean redundant = false;
				solution.identifiers.sort(null);
				for (Solution prevSolution : solutions) {
					if (solution.identifiers.equals(prevSolution.identifiers)) {
						// solution already exists in a different permutation
						redundant = true;
						break;
					}
				}
				boolean hasZero;
				if (!redundant) {
					hasZero = false;
					for (PersonGroup personGroup : solution.groups) {
						if (personGroup.strength <= 0) {
							hasZero = true;
							break;
						}
					} 
					if (!redundant &&((hasZero && Flag.getFlagByName("ACCEPT_ZEROES", flags).value) || !hasZero)) {
						solutions.add(solution);
					} 
				}
			}

		}
		
		// calculation has finished
		solutions.sort(null);
		if (solutions.isEmpty()) {
			show("Sorry...no solution could be found for this sample");
		} else {
			showSolutions();
		}
	}
	private static void showSolutions() {
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
	public static void show(Object msg) {
		show(msg, false);
	}
	public static void show(Object msg, boolean noLineFeed) {
		if(noLineFeed){
			System.out.print(msg);
		}else{
			System.out.println(msg);	
		}
	}
}
