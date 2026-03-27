package bst;

public class BSTTimeDriver {
    public static void main(String[] args) throws Exception {
        BST myBST = new BST();

        // Part 3.i: Empty tree height
        System.out.println("Empty BST height: " + myBST.getHeight());

        // Part 3.ii: Add 1000 elements
        long startAdd = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++)
            myBST.putItem(i);
        long endAdd = System.currentTimeMillis();
        System.out.println("Time to add 1000 elements: " + (endAdd - startAdd) + " ms");
        System.out.println("BST height before balancing: " + myBST.getHeight());

        // Part 3.iii: Search 10,000 times (unbalanced)
        long startSearch = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++)
            myBST.getItem(999);
        long endSearch = System.currentTimeMillis();
        System.out.println("Time for 10000 searches (unbalanced): " + (endSearch - startSearch) + " ms");

        // Part 3.iv: Balance the tree
        long startBalance = System.currentTimeMillis();
        myBST.splitBalance();
        long endBalance = System.currentTimeMillis();
        System.out.println("Time to balance BST: " + (endBalance - startBalance) + " ms");
        System.out.println("BST height after balancing: " + myBST.getHeight());

        // Part 3.v: Search 10,000 times (balanced)
        long startSearchBalanced = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++)
            myBST.getItem(999);
        long endSearchBalanced = System.currentTimeMillis();
        System.out.println("Time for 10000 searches (balanced): " + (endSearchBalanced - startSearchBalanced) + " ms");

        // Reverse Pre-order traversal
        System.out.println("Reverse Pre-order traversal:");
        myBST.resetTree("RevPre");
        while (!myBST.travEmpty()) {
            System.out.print(myBST.getNextItem() + " ");
        }
        System.out.println();
    }
}
