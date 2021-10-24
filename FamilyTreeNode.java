package finalProject;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeNode {
	// Data fields
	private String lastName;
	private List<Person> members;
	public FamilyTreeNode left;
	public FamilyTreeNode right;

	/**
     	* Constructor: instantializes a new FamilyTreeNode
     	* given a lastName
     	*/
	public FamilyTreeNode(String lastName) {
        	this.lastName = lastName;
        	left = null;
        	right = null;
        	members = new ArrayList<>();
	}

	/**
     	* Returns the last name of the FamilyTreeNode
     	*/
	public String getLastName() {
		return lastName;
	}

	/**
     	* Returns the arraylist of members in the FamilyTreeNode
     	*/
	public List<Person> getMembers() {
		return members;
	}

	/*
	 * Returns true if there is an instance of Person in the FamilyTreeNode that has
	 * the same first and last name provided Return false otherwise
	 */
	public boolean doesFamilyMemberExist(String lastName, String firstName) {
		int i = members.size()-1;
		while (i>=0) {
			if (members.get(i).getFirstName()== firstName && members.get(i).getLastName() == lastName) {
				return true;
			}
			i--;
		}
        	return false;
	}

	/**
	 * Returns true if there is an instance of Person in the FamilyTreeNode whose
	 * phone number matches the one provided Returns false otherwise
	 */
	public boolean doesNumberExist(String phoneNumber) {
		int i = members.size()-1;
		while (i>=0) {
			if (members.get(i).getPhoneNumber()== phoneNumber) {
				return true;
			}
			i--;
		}
        	return false;
	}

	/*
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(String lastName, String firstName, String phoneNumber) {
		if (lastName != this.lastName) {
			throw new IllegalArgumentException("Last name does not match family last name");
		}
		Person person = new Person(lastName, firstName, phoneNumber);
		addFamilyMember(person);
		
	}

	/**
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(Person person) {
		if (person.getLastName() != this.lastName) {
			throw new IllegalArgumentException("Last name does not match family last name");
		}
		if (doesNumberExist(person.getPhoneNumber())) {
			throw new IllegalArgumentException("Phone number already exists");
		}
		if (doesFamilyMemberExist(person.getLastName(),person.getFirstName())) {
			throw new IllegalArgumentException("Person already exists");
		}
		members.add(person);
	}

	/*
	 * Returns the phone number of the person in the family with the given phone
	 * number Returns "Does not exist." if not found
	 */
	public String getPhoneNumberOfFamilyMember(String lastName, String firstName) {
		int i = members.size()-1;
		while (i>=0) {
			if (members.get(i).getFirstName()== firstName && members.get(i).getLastName() == lastName) {
				return members.get(i).getPhoneNumber();
			}
			i--;
		}
		return "Does not exist.";
	}

	/*
	 * toString method Ex: [] [John Smith (5551234567), May Smith (5551234568),
	 * April Smith (5551234569), August Smith (5551234570)]
	 */
	public String toString() {
		String s = "";
		int i = members.size()-1;
		while (i>=0) {
			s = (members.get(i).getFirstName() + " " + members.get(i).getLastName() + " (" + members.get(i).getPhoneNumber() + ")") + s;
			if (i>0) {	
				s = ", " + s;
			}
			i--;
		}
		s = "["+ s + "]";
		return s;
	}
}
