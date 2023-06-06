INFS 519 Program 1 is a project where a Table class is designed to implement the Table abstract data type (ADT) and used to construct a basic address book. This Table ADT is designed to store pairs of unique keys and associated values, supporting operations like value lookup, key-value pair insertion, and deletion. The address book makes use of names as keys and their corresponding addresses as values.

The software offers a user interface that prompts various options such as adding or deleting an entry, updating an address, displaying all entries, or exiting the program. When adding a name, if the name already exists, the user will be informed, and no changes will be made. If the name doesn't exist, the user can add a new entry by providing an associated address. For updates, the old address is displayed, and a new address is asked from the user to replace the old one.

An invalid selection from the menu prompts the menu to display again. If the user chooses to display all entries, all names and addresses are shown, irrespective of their order. The program continues prompting the user until the choice to quit is made.

Internally, a Table class is designed to store entries, i.e., key-value pairs of Strings. Additionally, a Node class with private fields is used to identify each entry (key), store associated data (value), and link to the next node. Public methods in the Table class facilitate operations like insertion, lookup, deletion, update, and display of all entries.

The Table class uses a linked list of Nodes for storing entries, and a private field 'mark' for traversing the list and reading the entries. Any additional methods added to the Table class must be private, maintaining the privacy of all instance fields in all classes of the program.

This Table class isn't specifically an address book but rather a general tool for storing and operating on pairs of strings. The address book is an instance of the Table class and utilizes its public methods for the operations.
