/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1.v2;

/**
 *
 * @author adel
 */
/** This class implements a linked list of key/value pairs of strings
* */
public class Table {
    /**
     * Current node
     */
    private Node mark;
    /**
     * First node in the list
     */
    private Node first;
    /* Method insert used to create new node and insert it at the beginning
       of the list if the list is empty. otherwise, it will insert node
       at the end of the list after traverse the list.*/
    public boolean insert(String key, String value) {
        Node node = new Node(key, value);
       

        //you need to check if the list is empty
        if (first == null) {
            first = node;
            return true;
        } else {// you need to set the new node to head to start from the first location, then traverse the LS to the last node, and then insert after it
            Node n = first;
            while (n.getNext() != null) {
             n=n.getNext();
            }
            // when n point to null this means it is the last node, now we can make n.next point to the new node, which means we add the new node at the end of the LS
            n.setNext(node);
            return true; 
        }
    }
    /* LooUp method receives parameter key which represent name.
       return the address value that associated with the key 
    */
    public String lookUp(String key) {
        if(mark.getKey().equals(key))
            return mark.getValue();
        else
            return null;
    }
    
    /*Method delete has one parameter (key) which is the name.
      it traverse the Linked List looking for a node contains key
      if key found at a node, then delte that node, and return ture.
    otherwise, return false
    */
    public boolean delete(String key) {
        // to set mark=first
        markToStart();
        // this reference used to point to the node just before mark(current) 
        Node prev=null;
        // first check if the list is empty
        if(!markToStart()){
            System.out.println("The Address book is empty!");
            //then, check if the list contains  only one node
        }else if(mark.getNext()==null){
            // then, check if that node contains the key, if so, delete the node
            if(mark.getKey().equals(key)){
                mark=null;
                first=null;
                System.out.println(key+" has been deleted!");
                return true;
                // if the list contains only one node, and that node does not contain the key
                // then, the key(name) does not exist in the list
            }else{
                System.out.println(key+" doesn't exist!");
                
            }
           // travers the list and in each iteration, check if the key found
           // if the key found at the current node(mark), then delete it
           // by setting the previous.next to mark.next
           } else{
               // prev is only a reference  to the node before current node (mark)
               prev=mark;
               while(advanceMark()){
                   // if the lookUp method return a string, this means that the name if found
                   // at the current node
                   if(lookUp(key)!=null){
                       //setting the previous.next to point to mark.next
                       prev.setNext(mark.getNext());
                       System.out.println(key+" has been deleted!");
                       mark=null;
                       return true;
                       
                   }
                   else{
                          prev=mark;
                 
                   }
               }
               
           }return true;
        }
        
    /*Update method
    Parameters: key, newValue
    key is the name
    newValue is the new address.
    update method will search for the key in the linked list, if the key found, then the old address
    will be replaced by newValue,and return true. 
    Otherwise, it will prompt the user will the appropriate message, and return false.
    */
    public boolean update(String key, String newValue) {
        if(lookUp(key)!=null){
            mark.setValue(newValue);
            return true;
        }else{
            return false;
        }
    }
    /*
    This method will set the mark to equal to first if the list not empty, then return true
    otherwise, it will only return false
    */
    public boolean markToStart() {
        if(first!=null){
            mark=first;
            return true;
        }
        else{
            return false;
        }
          
    }
     /*This method will advance the mark one step if the mark has a next node, and return ture
    otherwise, it will only return false
    */
    public boolean advanceMark() {
        if(mark.getNext()!= null){
             mark=mark.getNext();
             return true;
        }
       else { 
        return false;
        }
    }
    /*This method will return the key associated with current node(mark)
    */
    public String keyAtMark() {
        return mark.getKey();
    }
    /*This method will return the value associated with current node(mark)
    */
    public String valueAtMark() {
        return mark.getValue();
    }
     /*This method will displays all nodes in the list
   
    */
    public int displayAll() {
        // to print the values of LS, you have to travel through the LS, and to do that you have to create node and assign it to head to start from the begining
        Node n = first;
        int size=0;
        while (n.getNext() != null) {
            System.out.println(n.toString());

            // after print the data of the current node, then you need to advance the node
            n=n.getNext();
            size++;
        }
        // for the last element you have to print its value because the advance will stop at the last node but its value will not printed because the condition of the while loop
        System.out.println(n.toString());
        
        return size;
    }
}
