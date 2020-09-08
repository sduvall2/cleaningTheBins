import java.util.*;
import java.io.*;

public class Bins{

	private static final String WORST_FIT_DECREASING = "worst-fit decreasing";
	private static final String WORST_FIT = "worst-fit";
	private static final double GIG = 1000000.0;
	private static final String DEFAULT_FILE_NAME = "sizes.txt";

	/*
	 * Main algorithm for the program:
	
	// Read the data file
	// Do the first disc experiment
	// Print results
	// Do second experiment
	// Print results
	 * 
	 * 
	 */
	public void runDiskExperiment() {
		List<Integer> fileSizes = makeDataFromFile(DEFAULT_FILE_NAME);
		Collection<Disk> disks = this.calculatedNeededDisksForFiles(fileSizes);
		this.printDiscsCreated(disks, WORST_FIT);
		// Reverse the order for the second experiment
		Collections.sort(fileSizes, Collections.reverseOrder());
		disks = this.calculatedNeededDisksForFiles(fileSizes);
		this.printDiscsCreated(disks, WORST_FIT_DECREASING);		
	}
	
	
	/*
	 * Given a filename, open the file and return
	 * a list of integers from the file
	 * Assumes: filename exists and has integers in it
	 */
	private List<Integer> makeDataFromFile(String filename){
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
			// It's better to return an empty structure than a null one.
			return new ArrayList<Integer>();
		}
	}

	/*
	 * Uses a greedy approach to save the given files onto disks.
	 */
	private Collection<Disk> calculatedNeededDisksForFiles(List<Integer> fileSizes){

		PriorityQueue<Disk> pq = new PriorityQueue<Disk>();
		pq.add(new Disk(0));
		int diskId = 1;
		for (Integer oneFileSize : fileSizes){
			Disk d = pq.peek();
			if (d.freeSpace() >= oneFileSize){
				pq.poll();
				d.add(oneFileSize);
				pq.add(d);
			}
			else{
				Disk d2 = new Disk(diskId);
				diskId++;
				d2.add(oneFileSize);
				pq.add(d2);
			}
		}
		return pq;
	} 

	/*
	 * Prints to the console various statistics about a collection
	 * of disks created with the given algorithm.
	 */
	public void printDiscsCreated(Collection<Disk> usedDisks, String algorithm) {
		System.out.println("total size = " + totalMemoryUsed(usedDisks) / GIG + "GB");
		System.out.println();
		System.out.println(algorithm);
		System.out.println("number of disks used: " + usedDisks.size());
		for(Disk disk: usedDisks) {
			System.out.println(disk);
		}
		System.out.println();
	}

	
	/*
	 * Compute the total amount of used file space for a 
	 * collection of Disks
	 */
	public int totalMemoryUsed(Collection<Disk> allDisks) {
		int total = 0;
		for(Disk disk: allDisks) {
			total += disk.getSize();
		}
		return total;
	}
}
