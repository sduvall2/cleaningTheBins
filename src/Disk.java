import java.util.*;

/*
 * Disk.java
 * 
 * One disk has:
 *  an id number, so it can be distinguished from other disks, 
 *  a size: amount of memory used up on the disk
 *  capacity: amount of total memory available
 *  files: a list of file sizes.  Totaling the file sizes should give the disk size 
 */
public class Disk implements Comparable<Disk>{
    private static final int DEFAULT_START_SIZE = 1000000;
	private int myId;
    private int mySize;
    private int myCapacity;
    private List<Integer> myFiles;


    public Disk(){
        mySize = 0;
        myCapacity = DEFAULT_START_SIZE;
        myFiles = new ArrayList<Integer>();
    }

    public Disk (int id){
    	this();
        myId = id;
    }
    
    // returns amount of space used on disk
    public int getSize() {
    	return mySize;
    }

    // returns amount of unused memory available on disk
    public int freeSpace (){
        return myCapacity - mySize;
    }

    // adds new file to disk
    public void add (int filesize){
        myFiles.add(filesize);
        mySize += filesize;
    }

    public String toString (){
        String result = myId + "\t" + freeSpace() + ":\t";
        for (int k = 0; k < myFiles.size(); k++){
            result += " " + myFiles.get(k);
        }
        return result;
    }

    /*
     * Only standard of equivalency right now is the ID number
     */
    public boolean equals(Disk other){
        return other!= null && myId == other.myId;
    }

    /*
     * Compares based on amount of free space, then uses id number to 
     * break ties.
     */
    public int compareTo (Disk other){
        if (other != null){
            int result = other.freeSpace() - freeSpace();
            if (result == 0) return myId - other.myId;
            else return result;
        }
        else{
            return -1;
        }
    }
}
