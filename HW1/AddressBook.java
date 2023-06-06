/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1.v2;

import java.util.Scanner;

/**
 *
 * @author adel
 */
public class AddressBook {

    /**
     * @param args the command line arguments
     * The AddressBook Class is the class that contains main method.
     * It is acting like an interface to the user.
     * It use a switch statement options: n->insert, l->lookUp, u->update, d->delete, a->displayAll
     */
    public static void main(String[] args) {
        Table t = new Table();
        String name;
        String address;
        String operation = null;
        Scanner o = new Scanner(System.in);
        Scanner n = new Scanner(System.in);
        Scanner a = new Scanner(System.in);
        /*While loop will constantly show the menu to the user, the condition to exit the loop
        is to select "q"*/
        while (operation != "q") {

            System.out.println("Add a name (n)");
            System.out.println("Look up a name (l)");
            System.out.println("Update address (u)");
            System.out.println("Delete an entry (d)");
            System.out.println("Display all entries (a)");
            System.out.println("Quit (q)");
            operation = o.nextLine();

            // Switch used to show the meanu to the user 
            switch (operation) {
                // if the user select 'n'----> case "n" will execute
                case "n":
                    System.out.println("Please enter the name:");
                    // Take the input from the user and store it in name variable
                    name = n.nextLine();
                    //----------------------------------------
                    // check if the list is empty, then insert the first node in it.
                    if (!t.markToStart()) {
                        System.out.println("Please enter the address:");
                        address = a.nextLine();
                        // Call the the method insert
                        t.insert(name, address);
                        
                        //This break statement  used to exit the case 'n' after the insertion is done 
                        break;

                    } else {
                        // set mark which is the current node to equal to first node
                        t.markToStart();
                        // Call lookup method to see if the name exists
                        // if lookup method return an address, this mean the name is already exist,
                        // then, show the message to the user, and exit case "n"
                        if (t.lookUp(name) != null) {
                            System.out.println(name+" already exists! ");
                            break;
                        } else {
                            // the while loop is used to travel through the list node by node,
                            // and check if the name exits. If it exists, then show the message to the user
                            // and exit the case "n"
                            while (t.advanceMark()) {
                                if (t.lookUp(name) != null) {
                                    System.out.println(name+" already exists! ");
                                    break;
                                }

                            }
                            // if the while loop finished and name doesn't exit, then ask the user to enter 
                            // the new address and call insert method to create new node.
                            if (t.lookUp(name) == null) {
                                System.out.println("Pease enter the address:");
                                address = a.nextLine();
                                t.insert(name, address);
                            }

                        }

                    }

                    //--------------------------------------
                    break;
                /* This case is used to search for specific address associate with a name*/
                case "l":
                    
                    
                    // if list is  empty, then inform the user about that
                    if(!t.markToStart()){
                        System.out.println("The Address Book is empty");
                        break;
                    }
                    else{
                        System.out.println("Please enter a name to check is address:");
                        Scanner search = new Scanner(System.in);
                        String nameToSearchForAddress;
                        // Take the input from the user using a Scanner and store the value
                        // in nameToSearchForAddress
                        nameToSearchForAddress = search.nextLine();
                        t.markToStart();
                        // if the name (key) was found in the first node, then print its value which is address
                        if (t.lookUp(nameToSearchForAddress) != null) {
                            System.out.println(t.lookUp(nameToSearchForAddress));
                        } else {
                            // Traverse the list and check if the name is exist

                            while (t.advanceMark()) {
                                // if name is found, then print its value which is the address
                                if (t.lookUp(nameToSearchForAddress) != null) {
                                    System.out.println(t.lookUp(nameToSearchForAddress));
                                    break;
                                }

                            }
                            // if the while loop reached the end of the list without finding 
                            // the name, then show the following message to the user.
                            if(t.lookUp(nameToSearchForAddress) == null){
                               System.out.println(nameToSearchForAddress+" doesn't exist");  
                            }
                           
                        }
                    }
                    break;
                case "u":
                    Scanner s1=new Scanner(System.in);
                    String nameTobeUpdated;
                    System.out.println("Please enter a name to Update it:");
                    nameTobeUpdated=s1.nextLine();
                    String newAddress;
                    // set mark to equal first if list is not empty.
                    t.markToStart();
                    if (!t.markToStart()) {
                        System.out.println("The address book is empty!");
                    } else {
                        if (t.lookUp(nameTobeUpdated) != null) {
                            System.out.println("Name:" + t.keyAtMark());
                            System.out.println("\nOld Address:" + t.valueAtMark());
                            System.out.println("\nPlease enter the new address:\n");
                            newAddress=s1.nextLine();
                            t.update(nameTobeUpdated, newAddress);
                            System.out.println("\nThe Address has been updated successfully!");
    
                        } else {
                            // while lookUp method return null, this mean that the name is not found
                            // Then, advance the mark
                            while (t.lookUp(nameTobeUpdated) == null) {
                                t.advanceMark();
                            }
                            // the while loop will exit if you found the name, or if you reach the last node
                            //then we have to check the last node
                            if (t.lookUp(nameTobeUpdated) != null) {
                                System.out.println("Name:" + t.keyAtMark());
                                System.out.println("Old Address:" + t.valueAtMark());
                                System.out.println("Please enter the new address:\n");
                                newAddress=s1.nextLine();
                                t.update(nameTobeUpdated, newAddress);
                                System.out.println("The Address has been updated successfully!");
                 
                            } else {
                                System.out.println(nameTobeUpdated + " doesn't not exist!");
                       
                            }
                        }
                    }

                    break;
                case "d":
                    Scanner s2=new Scanner(System.in);
                    String nameToDelete;
                    System.out.println("Please enter a name to delete:");
                    nameToDelete=s2.nextLine();
                    t.delete(nameToDelete); 
                    break;
                case "a":
                    // if the user select "a", then we need to print all nodes and their associate values
                    // first, we call markToStart method to set mark=first
                    // second, we call method displayALL() which will travel through the list node by node
                    //and print their asociate values (name, address)
                    
                    // Handle the exception if the list is empty
                    if(!t.markToStart()){
                        System.out.println("The Address Book is empty!");
                        break;  
                    }
                    else{
                        t.markToStart();
                        t.displayAll();
                        break;
                    }
             
                case "q":
                    // this case is used to exit the program
                    operation = "q";
                    break;

            }
        }
    }

}
