import java.util.Vector;

public class Flag {
	public String name;
	public boolean value;
	public String prompt;
	public Flag(String name, String prompt) {
		this.name = name;
		this.prompt = prompt;
	}
	public static Flag getFlagByName(String name, Vector<Flag> flags){
		for(Flag flag : flags){
			if((flag.name.equals(name))){
				return flag;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return name + ": " + value;
	}
	
}
