In this application, the linked list that was used in Program 1 in HW1 has been substituted with a binary search tree to serve as the foundation for an address book. The address book is stored in a text file.

From the perspective of the user, the application appears very similar to Program 1 in HW1. However, there is an additional initial prompt that asks the user "Do you want to open a file? (y/n)." If the user responds with "y", the program will request the name of a file, and upon receiving it, will open and read the file. Each pair of lines from the file will be used to create an entry in the address book, where one line represents the name and the other represents the address. If the user answers with "n", the program will not open any file. Regardless of the response, the program will proceed as it did in Program 1.

A new function named "save" has been introduced to the menu. When the user chooses "save", the program will ask for a file name, open that file, and write the entire address book to it. This function is somewhat akin to the "displayAll()" function, but there is a difference. The "displayAll()" function, in addition to displaying the contents, also shows the total count of nodes in the binary search tree.

Delving into the internal structure:
Among other classes, there’s a class named Table that stores entries as pairs of strings (key/value). This class retains the same public methods as in Program 1, with the addition of a new public method:

- public void save()
- It’s important to note that the Table class does not include any of the "mark" classes.

In this version, Table is built as a binary search tree. Each node in the tree consists of two string fields, one for the name and the other for the address. The insert, lookUp, delete, and update operations are performed using binary search tree mechanisms. Recursive traversals are employed to implement the "displayAll()" and "save()" functions. Specifically, an in-order traversal is used for the "displayAll()" function, whereas either a pre-order or post-order traversal can be used for the "save()" function.
