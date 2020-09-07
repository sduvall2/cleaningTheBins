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
    private int myId;
    private int mySize;
    private int myCapacity;
    private List<Integer> myFiles;


    public Disk(){
        mySize = 0;
        myCapacity = 1000000;
        myFiles = new ArrayList<Integer>();
    }

    public Disk (int id){
    	mySize = 0;
        myCapacity = 1000000;
        myFiles = new ArrayList<Integer>();
        myId = id;
    }

    // returns amount of free space available on disk
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

    public boolean equals(Disk other){
        if (other != null) return myId == other.myId;
        else  return false;
    }

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
