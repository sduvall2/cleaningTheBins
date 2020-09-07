import java.util.*;
import java.io.*;

public class Bins{

    public static void main (String args[]){
        try{
            Scanner input = new Scanner(new File("sizes.txt"));
            List<Integer> data = new ArrayList<Integer>();

            while (input.hasNext()){
                data.add(input.nextInt());
            }

            PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
            pq.add(new Disk(0));

            int diskId = 1;
            int total = 0;
            for (Integer size : data){
                Disk d = pq.peek();
                if (d.freeSpace() >= size){
                    pq.poll();
                    d.add(size);
                    pq.add(d);
                }
                else{
                    Disk d2 = new Disk(diskId);
                    diskId++;
                    d2.add(size);
                    pq.add(d2);
                }
                total += size;
            }

            System.out.println("total size = " + total / 1000000.0 + "GB");
            System.out.println();
            System.out.println("worst-fit method");
            System.out.println("number of disks used: " + pq.size());
            while (! pq.isEmpty()){
                System.out.println(pq.poll());
            }
            System.out.println();

            Collections.sort(data, Collections.reverseOrder());
            pq.add(new Disk(0));

            diskId = 1;
            for (Integer size : data){
                Disk d = pq.peek();
                if (d.freeSpace() >= size){
                    pq.poll();
                    d.add(size);
                    pq.add(d);
                }
                else{
                    Disk d2 = new Disk(diskId);
                    diskId++;
                    d2.add(size);
                    pq.add(d2);
                }
            }

            System.out.println();
            System.out.println("worst-fit decreasing method");
            System.out.println("number of disks used: " + pq.size());
            while (! pq.isEmpty()){
                System.out.println(pq.poll());
            }
            System.out.println();
        }
        catch (FileNotFoundException e){
            System.err.println("Could not open file: " + args[0]);
        }
    }
}
