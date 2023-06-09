In this application, the Binary Search Tree (BST) from assignment #2 is transformed into an AVL tree, which serves as the foundation for an address book. To the end user, the application seems identical to the second program, but with an additional feature in the "displayAll()" function. Now, it showcases the height, balance factor, and the name of the parent node for each node in the tree. Additionally, the root of the tree is specifically labeled.

Here is a brief explanation of the terms:

The height of a node refers to the number of edges between that node and the furthest leaf.
The balance factor of a node is calculated by subtracting the height of its left subtree from that of its right subtree.
The parent of a node is the node to which it is attached, one level above. The name corresponding to the parent node is displayed.
The program incorporates a class named Table, among other classes. This class stores entries made up of pairs of strings as keys and values. The public methods in Table remain the same as in Program 2, but it includes extra private methods for maintaining the balance of the AVL tree:

-private void rebalance(Node n)
-private Node rotateLeft(Node n)
-private Node rotateRight(Node n)
-private Node rotateLeftThenRight(Node n)
-private Node rotateRightThenLeft(Node n)
In this version, the Table is constructed as an AVL tree. Each node in the tree holds two string fields for name and address, in addition to integer fields for height and balance. Moreover, each node contains a reference to its parent node, as well as references to its left and right children. All the other requirements from assignment #2 are still applicable.
