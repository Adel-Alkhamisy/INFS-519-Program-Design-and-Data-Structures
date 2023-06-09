This program is a practice in hashing techniques. It extends the Address Book theme from the initial three assignments and makes alterations to the table, node, and addressBook classes to employ Open Hashing with Chaining as the new implementation approach.

Chaining makes use of a hash table built as an array of references. Each element of this hash table points to a singly linked list that contains the collision items tied to that specific array index. Essentially, the hash function results in the same index for all elements within a particular linked list.

The program remains a basic address book, storing names (as keys) and corresponding addresses. A menu is presented to the user, with options to add a name with an address, look up a name to display the associated address, update an address for a name, delete an entry, display all entries, save the address book to a file, and exit the program. Notably, during the display option, the average probe length (explained later) is also shown.

If a user selects to add an entry, the program asks for a name. If this name already exists, a message indicates so and no alterations are made. If the name is new, an associated address is requested and then stored. When a user picks the look-up or update options, they are prompted to provide a name. The program searches for this entry and, for updates, allows the user to input a new address to replace the old one. For deletions, after providing a name, the corresponding entry is eliminated. If the name is not found in the look-up, update, or delete operations, an apt message is displayed. Selecting the display option shows all names and addresses along with the average probe length. The menu continues to be shown until the user selects to quit.

Similar to HW#3, the program initially asks the user, "Do you want to open a file? (y/n)." If "y" is selected, the user is prompted for a file name, which is then opened and read. Each pair of lines - one with a name and another with an address - becomes an entry in the address book. If "n" is chosen, no file is opened. The program also includes a "save" operation in the menu, which prompts for a file name, and the entire address book is written to this file.

Separate Chaining, the hashing technique employed here, uses each slot in the hash table to reference a linked list for managing collisions. The hash table consists solely of references. The key of an item is hashed to determine the index, and the item is appended to the linked list at that index. Other items with the same hash are also added to this list. Normally, there can be N or more items in an N-cell array. The initial cell is found in O(1) time, but searching a list is proportional to the number of items in the list.

In this assignment, the hash table has a size of 10.

Regarding the average probe length:
The average probe length, which is indicative of the average access time, relies on the load factor - the ratio of the number of items to the size of the table. As the load factor rises, so does the average probe length. i.e : 
(8 empty slots * (1 probe/empty slot)) / 13 total slots
+
(2 slots with one element * (2 probes for slot with one element)) /13 total slots
+
(2 slots with two elements * (3 probes for slot with two elements)) /13 total slots +
(1 slots with four elements * (5 probes for slot with four elements)) /13 total slots
= 8/13 + 4/13 + 6/13 + 5/13 = 23/13
= 1.77 Average Probe Length
