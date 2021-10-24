package finalProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PhoneBook {
	// Data fields
	public Map<Character, BSFamilyTree> directory;

	/**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
		//TODO
		directory = new HashMap<>();
		directory.put('A', new BSFamilyTree());
		directory.put('B', new BSFamilyTree());
		directory.put('C', new BSFamilyTree());
		directory.put('D', new BSFamilyTree());
		directory.put('E', new BSFamilyTree());
		directory.put('F', new BSFamilyTree());
		directory.put('G', new BSFamilyTree());
		directory.put('H', new BSFamilyTree());
		directory.put('I', new BSFamilyTree());
		directory.put('J', new BSFamilyTree());
		directory.put('K', new BSFamilyTree());
		directory.put('L', new BSFamilyTree());
		directory.put('M', new BSFamilyTree());
		directory.put('N', new BSFamilyTree());
		directory.put('O', new BSFamilyTree());
		directory.put('P', new BSFamilyTree());
		directory.put('Q', new BSFamilyTree());
		directory.put('R', new BSFamilyTree());
		directory.put('S', new BSFamilyTree());
		directory.put('T', new BSFamilyTree());
		directory.put('U', new BSFamilyTree());
		directory.put('V', new BSFamilyTree());
		directory.put('W', new BSFamilyTree());
		directory.put('X', new BSFamilyTree());
		directory.put('Y', new BSFamilyTree());
		directory.put('Z', new BSFamilyTree());
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		if ((letter>='A'&&letter<='Z')||(letter>='a'&&letter<='z')) {
			return directory.get(Character.toUpperCase(letter));
		}else {
			throw new IllegalArgumentException(letter + " is not alphabet");
		}
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		if (lastName == "") {
			throw new IllegalArgumentException("lastName empty");
		}
		BSFamilyTree ft = getFamilyTree(lastName.charAt(0));
		ft.addFamilyTreeNode(lastName);
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		if (lastName == "" || firstName =="" || phoneNumber=="") {
			throw new IllegalArgumentException("Argument empty");
		}
		
		if (getFamilyTree(lastName.charAt(0)).doesFamilyExist(lastName) == false) {
			addFamily(lastName);
		}
		for (Map.Entry<Character, BSFamilyTree> e : directory.entrySet()) {
			if (e.getValue().doesNumberExist(phoneNumber)) {
				throw new IllegalArgumentException("Repeat number");
			}
		}
		FamilyTreeNode ft = getFamilyTree(lastName.charAt(0)).getFamilyTreeNode(lastName);
		if (ft.doesFamilyMemberExist(lastName, firstName)||ft.doesNumberExist(phoneNumber)==false) {
			ft.addFamilyMember(lastName, firstName, phoneNumber);
		}else {
			throw new IllegalArgumentException("Repeat name");
		}
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		if ( getFamilyTree(lastName.charAt(0)).doesFamilyExist(lastName)==false) {
			return "Does not exist.";
		}
		return getFamilyTree(lastName.charAt(0)).getFamilyTreeNode(lastName).getPhoneNumberOfFamilyMember(lastName, firstName);
	}


    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
		StringBuilder r = new StringBuilder();
		for (Map.Entry<Character, BSFamilyTree> e : directory.entrySet()) {
			r.append(e.getKey().toString()+"\n");
			r.append(e.getValue().toString());
		}
		return r.toString();
	}
}
