package models.relationship;

/**
 * SimpleDataTest
 */
public class SimpleDataTest {

	private int identity;
	private String name;
	private String description; 
	
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public int getIdentity() {
		return identity;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return "identity="+identity+"\nname="+name+"\ndescription="+description;
	}
}
