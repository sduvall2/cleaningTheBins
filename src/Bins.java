import java.util.*;
import java.io.*;

public class Bins{

	private static final double GIG = 1000000.0;
	private static final String DEFAULT_FILE_NAME = "sizes.txt";

	// Read the data file
	// Do the first disc experiment
	// Print results
	// Do second experiment
	// Print results
	public void runDiscExperiment() {
		List<Integer> fileSizes = makeDataFromFile(DEFAULT_FILE_NAME);
		PriorityQueue<Disk> disks = this.calculatedNeededDisksForFiles(fileSizes);
		this.printDiscsCreated(disks, "worst-fit");
		Collections.sort(fileSizes, Collections.reverseOrder());
		disks = this.calculatedNeededDisksForFiles(fileSizes);
		this.printDiscsCreated(disks, "worst-fit decreasing");		
	}
	/*
	 * Given a filename, open the file and return
	 * a list of integers from the file
	 * Assumes: filename exists and has integers in it
	 */
	public List<Integer> makeDataFromFile(String filename){
		Scanner scannerForFile;
		try {
			scannerForFile = new Scanner(new File(filename));

			List<Integer> data = new ArrayList<Integer>();

			while (scannerForFile.hasNext()){
				data.add(scannerForFile.nextInt());
			}
			scannerForFile.close();
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PriorityQueue<Disk> calculatedNeededDisksForFiles(List<Integer> files){

		PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
		pq.add(new Disk(0));
		int diskId = 1;
		for (Integer size : files){
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
		return pq;
	}

	public void printDiscsCreated(PriorityQueue<Disk> pq, String algorithm) {
		//System.out.println("total size = " + total / GIG + "GB");
		System.out.println();
		System.out.println(algorithm);
		System.out.println("number of disks used: " + pq.size());
		while (! pq.isEmpty()){
			System.out.println(pq.poll());
		}
		System.out.println();

	}

	/*public static void main (String args[]){
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
			System.out.println("total size = " + total / 1000000.0 + "GB");
			System.out.println();
			System.out.println("worst-fit decreasing method");
			System.out.println("number of disks used: " + pq.size());
			while (! pq.isEmpty()){
				System.out.println(pq.poll());
			}
			System.out.println();

			//************
			System.out.println("total size = " + total / 1000000.0 + "GB");
			System.out.println();
			System.out.println("worst-fit method");
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
	*/
}
