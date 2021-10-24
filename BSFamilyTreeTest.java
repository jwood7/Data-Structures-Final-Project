package finalProject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class BSFamilyTreeTest {
	
	@Test
	public void doesFamilyExistTest() {
		
		// setting up test cases
		BSFamilyTree test = new BSFamilyTree();
		//System.out.print(test);
		// testing for edge case when tree is empty
		assertFalse(test.doesFamilyExist("Smith"));
		
		// testing for general cases
		assertFalse(test.doesFamilyExist("Smith"));
		test.addFamilyTreeNode("Smith");
		assertTrue(test.doesFamilyExist("Smith"));
		
		assertFalse(test.doesFamilyExist("Johnson"));
		test.addFamilyTreeNode("Johnson");
		assertTrue(test.doesFamilyExist("Johnson"));
		assertTrue(test.doesFamilyExist("Smith"));
		
		assertFalse(test.doesFamilyExist("Williams"));
		test.addFamilyTreeNode("Williams");
		assertTrue(test.doesFamilyExist("Williams"));
		assertTrue(test.doesFamilyExist("Smith"));
		assertTrue(test.doesFamilyExist("Johnson"));
		
	}
	
	@Test
	public void addFamilyTreeNodeTest() {
		// setting up test cases
		BSFamilyTree test = new BSFamilyTree();
		
		// testing general cases
		test.addFamilyTreeNode("Smith");
		assertTrue(test.doesFamilyExist("Smith"));
		test.addFamilyTreeNode("Johnson");
		assertTrue(test.doesFamilyExist("Johnson"));
		test.addFamilyTreeNode("Williams");
		assertTrue(test.doesFamilyExist("Williams"));
		
		// testing for edge case when node is already in tree
		try {
			test.addFamilyTreeNode("Smith");
			fail("Expected IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is already in the tree."));
		}
		
		try {
			test.addFamilyTreeNode("Johnson");
			fail("Expected IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is already in the tree."));
		}
	}
	
	@Test
	public void getFamilyTreeNodeTest() {
		// setting up test cases
		BSFamilyTree test = new BSFamilyTree();
		
		// testing for edge case when node isn't in tree
		try {
			test.getFamilyTreeNode("Smith");
			fail("Expected an IllegalArgumentException to be thrown.");
		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("Last name is not in the tree."));
		}
		
		// testing general cases
		test.addFamilyTreeNode("Smith");
		FamilyTreeNode smithFamily = test.getFamilyTreeNode("Smith");
		assertTrue(test.doesFamilyExist("Smith"));
		smithFamily.addFamilyMember("Smith", "John", "5551234567");
		assertEquals("[John Smith (5551234567)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", test.toString());
		assertEquals(1, smithFamily.getMembers().size());
		smithFamily.addFamilyMember("Smith", "May", "5551234568");
		smithFamily.addFamilyMember("Smith", "April", "5551234569");
		smithFamily.addFamilyMember("Smith", "August", "5551234570");
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", test.toString());
		assertEquals(4, smithFamily.getMembers().size());
		
		test.addFamilyTreeNode("Johnson");
		FamilyTreeNode johnsonFamily = test.getFamilyTreeNode("Johnson");
		assertTrue(test.doesFamilyExist("Johnson"));
		johnsonFamily.addFamilyMember("Johnson", "Michael", "5557654321");
		assertEquals("[Michael Johnson (5557654321)]", johnsonFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n" + 
				"", test.toString());
		assertEquals(1, johnsonFamily.getMembers().size());
		johnsonFamily.addFamilyMember("Johnson", "Andrew", "5557654322");
		johnsonFamily.addFamilyMember("Johnson", "Barry", "5557654323");
		johnsonFamily.addFamilyMember("Johnson", "Kelly", "5557654324");
		assertEquals("[Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]", johnsonFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n" + 
				"", test.toString());
		assertEquals(4, johnsonFamily.getMembers().size());
		
		test.addFamilyTreeNode("Williams");
		FamilyTreeNode williamsFamily = test.getFamilyTreeNode("Williams");
		assertTrue(test.doesFamilyExist("Williams"));
		williamsFamily.addFamilyMember("Williams", "Clara", "5551593570");
		assertEquals("[Clara Williams (5551593570)]", williamsFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  [Clara Williams (5551593570)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"", test.toString());
		assertEquals(1, williamsFamily.getMembers().size());
		williamsFamily.addFamilyMember("Williams", "Virginia", "5551593571");
		williamsFamily.addFamilyMember("Williams", "Henry", "5551593572");
		williamsFamily.addFamilyMember("Williams", "Charles", "5551593573");
		assertEquals("[Clara Williams (5551593570), Virginia Williams (5551593571), Henry Williams (5551593572), Charles Williams (5551593573)]", williamsFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  [Clara Williams (5551593570), Virginia Williams (5551593571), Henry Williams (5551593572), Charles Williams (5551593573)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"", test.toString());
		assertEquals(4, williamsFamily.getMembers().size());
	}
	
	@Test
	public void doesNumberExistTest() {
		// setting up test cases
		BSFamilyTree test = new BSFamilyTree();
		
		// testing for edge case when tree is empty
		assertFalse(test.doesNumberExist("5551234567"));
		
		// testing for general cases
		//System.out.print(test);
		test.addFamilyTreeNode("Smith");
		FamilyTreeNode smithFamily = test.getFamilyTreeNode("Smith");
		smithFamily.addFamilyMember("Smith", "John", "5551234567");
		assertFalse(test.doesNumberExist("5551234568"));
		assertTrue(test.doesNumberExist("5551234567"));
		smithFamily.addFamilyMember("Smith", "May", "5551234568");
		assertFalse(test.doesNumberExist("5551234569"));
		assertTrue(test.doesNumberExist("5551234567"));
		assertTrue(test.doesNumberExist("5551234568"));
		
		test.addFamilyTreeNode("Johnson");
		FamilyTreeNode johnsonFamily = test.getFamilyTreeNode("Johnson");
		assertFalse(test.doesNumberExist("5557654321"));
		johnsonFamily.addFamilyMember("Johnson", "Michael", "5557654321");
		assertTrue(test.doesNumberExist("5557654321"));
		assertFalse(test.doesNumberExist("5557654322"));
		johnsonFamily.addFamilyMember("Johnson", "Andrew", "5557654322");
		assertFalse(test.doesNumberExist("5557654323"));
		assertTrue(test.doesNumberExist("5557654321"));
		assertTrue(test.doesNumberExist("5557654322"));
		
		test.addFamilyTreeNode("Williams");
		FamilyTreeNode williamsFamily = test.getFamilyTreeNode("Williams");
		assertFalse(test.doesNumberExist("5551593570"));
		williamsFamily.addFamilyMember("Williams", "Clara", "5551593570");
		assertTrue(test.doesNumberExist("5551593570"));
		assertFalse(test.doesNumberExist("5551593571"));
		williamsFamily.addFamilyMember("Williams", "Virginia", "5551593571");
		assertFalse(test.doesNumberExist("5551593572"));
		assertTrue(test.doesNumberExist("5551593570"));
		assertTrue(test.doesNumberExist("5551593571"));
	}
	
	@Test
	public void toStringTest() {
		// setting up test cases
		BSFamilyTree test = new BSFamilyTree();
		
		// testing for edge case when tree is empty
		assertEquals("null\n", test.toString());
		
		// testing for general cases
		test.addFamilyTreeNode("Smith");
		FamilyTreeNode smithFamily = test.getFamilyTreeNode("Smith");
		assertTrue(test.doesFamilyExist("Smith"));
		smithFamily.addFamilyMember("Smith", "John", "5551234567");
		assertEquals("[John Smith (5551234567)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", test.toString());
		smithFamily.addFamilyMember("Smith", "May", "5551234568");
		smithFamily.addFamilyMember("Smith", "April", "5551234569");
		smithFamily.addFamilyMember("Smith", "August", "5551234570");
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]", smithFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  null\n" + 
				"  null\n" + 
				"", test.toString());
		
		test.addFamilyTreeNode("Johnson");
		FamilyTreeNode johnsonFamily = test.getFamilyTreeNode("Johnson");
		johnsonFamily.addFamilyMember("Johnson", "Michael", "5557654321");
		johnsonFamily.addFamilyMember("Johnson", "Andrew", "5557654322");
		johnsonFamily.addFamilyMember("Johnson", "Barry", "5557654323");
		johnsonFamily.addFamilyMember("Johnson", "Kelly", "5557654324");
		assertEquals("[Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]", johnsonFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n" + 
				"", test.toString());
		
		test.addFamilyTreeNode("Williams");
		FamilyTreeNode williamsFamily = test.getFamilyTreeNode("Williams");
		williamsFamily.addFamilyMember("Williams", "Clara", "5551593570");
		williamsFamily.addFamilyMember("Williams", "Virginia", "5551593571");
		williamsFamily.addFamilyMember("Williams", "Henry", "5551593572");
		williamsFamily.addFamilyMember("Williams", "Charles", "5551593573");
		assertEquals("[Clara Williams (5551593570), Virginia Williams (5551593571), Henry Williams (5551593572), Charles Williams (5551593573)]", williamsFamily.toString());
		assertEquals("[John Smith (5551234567), May Smith (5551234568), April Smith (5551234569), August Smith (5551234570)]\n" + 
				"  [Michael Johnson (5557654321), Andrew Johnson (5557654322), Barry Johnson (5557654323), Kelly Johnson (5557654324)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"  [Clara Williams (5551593570), Virginia Williams (5551593571), Henry Williams (5551593572), Charles Williams (5551593573)]\n" + 
				"    null\n" + 
				"    null\n" + 
				"", test.toString());
	}
}