package finalProject;

import java.util.ArrayList;

/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    //Data Fields
    private FamilyTreeNode root;

    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
    	root = null;
    }
	
    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
    	lastName = lastName.toLowerCase();
    	if (root == null) {
    		return false;
    	}
    	FamilyTreeNode curr = root;
    	while (curr != null) {
    		String i = curr.getLastName().toLowerCase();
    		if (lastName.compareTo(i) == 0) {
    			return true;
    		}else if (lastName.compareTo(i)<0) {
    			curr = curr.left;
    		}else if (lastName.compareTo(i)>0) {
    			curr = curr.right;
    		}
    		
    	}
        return false; 
    }

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
    	String ln = lastName.toLowerCase();
    	FamilyTreeNode node = new FamilyTreeNode(lastName);
    	if (root == null) {
    		root = node;
    		return;
    	}
    	FamilyTreeNode curr = root;
    	while (curr != null) {
    		String i = curr.getLastName().toLowerCase();
    		if (ln.compareTo(i) == 0) {
    			throw new IllegalArgumentException("Last name already exists in BS family tree");
    		}else if (ln.compareTo(i)<0) {
    			if (curr.left == null) {
    				curr.left = node;
    				return;
    			}
    			curr = curr.left;
    		}else if (ln.compareTo(i)>0) {
    			if (curr.right == null) {
    				curr.right = node;
    				return;
    			}
    			curr = curr.right;
    		}	
    	}
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
    	lastName = lastName.toLowerCase();
    	if (root == null) {
    		throw new IllegalArgumentException("Last name not in tree!");
    	}
    	FamilyTreeNode curr = root;
    	while (curr != null) {
    		String i = curr.getLastName().toLowerCase();
    		if (lastName.compareTo(i) == 0) {
    			return curr;
    		}else if (lastName.compareTo(i)<0) {
    			curr = curr.left;
    		}else if (lastName.compareTo(i)>0) {
    			curr = curr.right;
    		}
    		
    	}
    	throw new IllegalArgumentException("Last name not in tree!"); 
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
    	return doesNumberExist(phoneNumber, root);
    }
    
    private boolean doesNumberExist(String phoneNumber, FamilyTreeNode curr) {
    	if (curr == null) {
    		return false;
    	}
    	if (curr.doesNumberExist(phoneNumber)) {
    		return true;
    	}else {
    		return (doesNumberExist(phoneNumber,curr.left) || doesNumberExist(phoneNumber,curr.right)); 
    	}
    }

    /**
     * Returns the string representation of the BSFamilyTree
     */
    private StringBuilder toString_helper(FamilyTreeNode current, int level) {
		StringBuilder r = new StringBuilder();
		for (int i = 0; i<level;i++) {
			r.append("  ");
		}
		if (current == null) {
			r.append("null\n");
		}
		else {
			r.append(current.toString()+"\n");
			r.append(toString_helper(current.left,level+1));
			r.append(toString_helper(current.right,level+1));
			
		}
		return r;
		
	}
	public String toString() {
          return toString_helper(root,0).toString();
    }
}
