/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3v2;

/**
 *
 * @author adel
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AddressBook {
    /**
     * @param args the command line arguments The AddressBook Class is the class
     * that contains main method. It is acting like an interface to the user. It
     * use a switch statement options: n->insert, l->lookUp, u->update,
     * d->delete, a->displayAll, s-> save to new or existing file
     */
    public static void main(String[] args) {
        Table t = new Table();
        String name;
        String address;
        String operation = null;
        Scanner o = new Scanner(System.in);
        Scanner n = new Scanner(System.in);
        Scanner a = new Scanner(System.in);
        Scanner oF = new Scanner(System.in);
  
        System.out.println("Do you want to open a file?(y/n)");
        operation=o.nextLine();
        switch(operation){
            case "y":
                System.out.println("Enter file name: For example: C:\\Users\\Adel\\Desktop\\test.txt");
                String pathToFile = oF.nextLine();
                File f = new File(pathToFile);
                // check if the file exists, then read data from it.
                if (f.exists()) {
                    try {
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        // Line1 is the key, and line2 is the value
                        String line1, line2;
                        // Read two lines at a time, the first line is the key, the second line is the value
                        while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null) {
                            // call insert method to implement the insertion logic
                            t.insert(line1, line2);
                        }
                        // close FileReader, and BufferedReader
                        fr.close();
                        br.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else{ // Otherwise, inform the user, the file doesn't exist
                    try {
                         System.out.println("File name doesn't exists!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        
                break;
            case "n":
                break;
}
        /*While loop will constantly show the menu to the user, the condition to exit the loop
        is to select "q"*/
        while (operation != "q") {
            System.out.println("Add a name (n)");
            System.out.println("Look up a name (l)");
            System.out.println("Update address (u)");
            System.out.println("Delete an entry (d)");
            System.out.println("Display all entries (a)");
            System.out.println("Save (s)");
            System.out.println("Quit (q)");
            operation = o.nextLine();

            // Switch used to show the meanu to the user 
            switch (operation) {
                // if the user select 'n'----> case "n" will execute
                case "n":
                    System.out.println("Enter the name:");
              
                    // Take the input from the user and store it in name variable, and address variable
                    name = n.nextLine();
                    System.out.println("Enter the Address:");
                    address=n.nextLine();
                    // Call insert method
                    t.insert(name, address);
                    break;
                /* This case is used to search for specific address associate with a name*/
                case "l":
                    System.out.println("Enter a name: ");
                    name=n.nextLine();
                    // Call the lookUp method to see if the key is exists
                    address=t.lookUp(name);
                    // if key was found, then show its value to the user
                    if(address!=null){
                        System.out.println("The address associated with "+name+" is "+ address);
                    }else{// otherwise, the key doesn't exists
                        System.out.println("Name doesn't exist!");
                    }

                    break;
                case "u": // this case is to update the value associated with a key if the key was found
                    System.out.println("Enter Name: ");
                    name=n.nextLine();
                    System.out.println("Enter Address: ");
                    address=n.nextLine();
                    t.update(name, address);
                    
                    break;
                case "d": // this case is to delete a node if it is exists
                    
                    String nameToDelete;
                    System.out.println("Please enter a name to delete:");
                    nameToDelete = n.nextLine();
                    t.delete(nameToDelete);
                    break;
                case "a":
                    // if the user select "a", then we need to print all nodes and their associate values 
                    // Also, the size of BST will be printed

                    int size=t.displayAll();
                    System.out.println("total Number: "+size);
                    break;
                   
                case "s": // This case to save BST data to either a new file or existing file
                    // The path of the file and extension must be written.
                    String path;
                    Scanner s = new Scanner(System.in);
                    System.out.println("Enter the file name: For example: C:\\Users\\Adel\\Desktop\\test.txt ");
                    path = s.nextLine();
                    t.save(path);
                    break;
                case "q":
                    // this case is used to exit the program
                    operation = "q";
                    break;

            }
        }
    }
    
}
