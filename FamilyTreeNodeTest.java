package finalProject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class FamilyTreeNodeTest {

	@Test
	public void constructorTest() {
		// setting up test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing general cases (assuming there is no invalid inputs)
		assertEquals("Smith", test.getLastName());
		assertEquals("[]", test.getMembers().toString());
		assertEquals(0, test.getMembers().size());
		assertEquals("[]", test.toString());
		assertEquals(null, test.left);
		assertEquals(null, test.right);
	}
	
	@Test
	public void getLastNameTest() {
		// setting up test cases
		FamilyTreeNode test1 = new FamilyTreeNode("Smith");
		FamilyTreeNode test2 = new FamilyTreeNode("Johnson");
		FamilyTreeNode test3 = new FamilyTreeNode("Williams");
		
		// testing general cases
		assertEquals("Smith", test1.getLastName());
		assertEquals("Johnson", test2.getLastName());
		assertEquals("Williams", test3.getLastName());
	}
	
	@Test
	public void getMembersTest() {
		// setting up test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing general cases
		assertEquals("[]", test.getMembers().toString());
		assertEquals(0, test.getMembers().size());
		test.addFamilyMember(new Person("Smith", "John", "5551234567"));
		assertEquals("[John Smith (5551234567)]", test.getMembers().toString());
		assertEquals(1, test.getMembers().size());
		test.addFamilyMember(new Person("Smith", "April", "5551234568"));
		assertEquals("[John Smith (5551234567), April Smith (5551234568)]", test.getMembers().toString());
		assertEquals(2, test.getMembers().size());
	}
	
	@Test
	public void doesFamilyMemberExistTest() {
		// setting up test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing general cases
		assertFalse(test.doesFamilyMemberExist("Smith", "John"));
		test.addFamilyMember("Smith", "John", "5551234567");
		assertFalse(test.doesFamilyMemberExist("Williams", "John"));
		assertFalse(test.doesFamilyMemberExist("Smith", "April"));
		assertTrue(test.doesFamilyMemberExist("Smith", "John"));
		test.addFamilyMember("Smith", "April", "5551234568");
		assertFalse(test.doesFamilyMemberExist("Williams", "April"));
		assertFalse(test.doesFamilyMemberExist("Smith", "May"));
		assertTrue(test.doesFamilyMemberExist("Smith", "April"));
	}
	
	@Test
	public void doesNumberExistTest() {
		// setting up test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing general cases
		assertFalse(test.doesNumberExist("5551234567"));
		test.addFamilyMember(new Person("Smith", "John", "5551234567"));
		assertTrue(test.doesNumberExist("5551234567"));
		assertFalse(test.doesNumberExist("5551234568"));
		test.addFamilyMember(new Person("Smith", "April", "5551234568"));
		assertTrue(test.doesNumberExist("5551234567"));
		assertTrue(test.doesNumberExist("5551234568"));
	}
	
	@Test
	public void addFamilyMemberTest() {
		// setting up test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing for edge case when there are no members
		assertEquals("[]", test.toString());
		assertEquals(0, test.getMembers().size());
		
		// testing addFamilyMember(String, String, String) for edge case when the last names do not match
		try {
			test.addFamilyMember("Bryant", "John", "5551234567");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("The last name provided does not match the last of the FamilyTreeNode."));
		}
		
		// testing addFamilyMember(Person) for edge case when the last names do not match
		try {
			test.addFamilyMember(new Person("Bryant", "John", "5551234567"));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("The last name of the Person provided does not match the last of the FamilyTreeNode."));
		}
		
		// testing addFamilyMember(String, String, String)
		test.addFamilyMember("Smith", "John", "5551234567");
		assertEquals("[John Smith (5551234567)]", test.toString());
		assertEquals(1, test.getMembers().size());
		
		// testing addFamilyMember(String, String, String) for edge case when the phone number is non-unique
		try {
			test.addFamilyMember("Smith", "May", "5551234567");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This phone number is already in the Family Tree."));
		}
		
		// testing addFamilyMember(Person) for edge case when the phone number is non-unique
		try {
			test.addFamilyMember(new Person("Smith", "May", "5551234567"));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This phone number is already in the Family Tree."));
		}
		
		// testing addFamilyMember(String, String, String) for edge case when the family member is non-unique
		try {
			test.addFamilyMember("Smith", "John", "5551234568");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This person is already in the Family Tree."));
		}
		
		// testing addFamilyMember(Person) for edge case when the family member is non-unique
		try {
			test.addFamilyMember(new Person("Smith", "John", "5551234568"));
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("This person is already in the Family Tree."));
		}
		
		// testing addFamilyMember(Person)
		test.addFamilyMember(new Person("Smith", "May", "5551234568"));
		assertEquals("[John Smith (5551234567), May Smith (5551234568)]", test.toString());
		assertEquals(2, test.getMembers().size());
		
		// testing general cases
		test.addFamilyMember("Smith", "April", "5551234569");
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569)]", test.toString());
		assertEquals(3, test.getMembers().size());
		test.addFamilyMember(new Person("Smith", "August", "5551234570"));
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]", test.toString());
		assertEquals(4, test.getMembers().size());
	}
	
	@Test
	public void getPhoneNumberOfFamilyMemberTest() {
		// setting test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing general cases
		assertEquals("Does not exist.", test.getPhoneNumberOfFamilyMember("Smith", "John"));
		test.addFamilyMember("Smith", "John", "5551234567");
		assertEquals("5551234567", test.getPhoneNumberOfFamilyMember("Smith", "John"));
		assertEquals("Does not exist.", test.getPhoneNumberOfFamilyMember("Smith", "April"));
		test.addFamilyMember("Smith", "April", "5551234568");
		assertEquals("5551234567", test.getPhoneNumberOfFamilyMember("Smith", "John"));
		assertEquals("5551234568", test.getPhoneNumberOfFamilyMember("Smith", "April"));
	}
	
	@Test
	public void toStringTest() {
		// setting test cases
		FamilyTreeNode test = new FamilyTreeNode("Smith");
		
		// testing for edge case when there are no members
		assertEquals("[]", test.toString());
		
		// testing general cases
		test.addFamilyMember("Smith", "John", "5551234567");
		test.addFamilyMember(new Person("Smith", "May", "5551234568"));
		test.addFamilyMember("Smith", "April", "5551234569");
		test.addFamilyMember(new Person("Smith", "August", "5551234570"));
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]", test.toString());
	}
}