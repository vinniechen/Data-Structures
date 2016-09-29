package hw6;
import java.io.*;
import java.util.Scanner;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * Runs the text file and performs tasks according to each line of the file
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

public class EDF {

	/**
	 * Print method prints if adding, busy with, or done with task
	 * @param choice 1 = adding, 2 = busy with 3 = done with
	 * @param current_time current time of running
	 * @param r Record to be printed
	 */
	public static void print(int choice,long current_time, Record r)
	{
		if(choice==1)
			System.out.println( current_time + ": adding " +
					r.toString() );
		else if(choice==2)
			System.out.println( current_time + ": busy with " +
					r.toString() );
		else
			System.out.println( current_time +  ": done with " +
					r.toString( current_time ) );
	}

	/** Main method runs the txt file entered and performs tasks */
	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.err.println("Incorrect number of arguments: "+args.length);
			System.err.println("java hw6.EDF <input-file>");
			System.exit(1); 
		}	
		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time=0;
		long timeNeeded;
		long timeAvail;
		long timeLeft;
		long endTime;
		String type;
		Record task;
		Record leftOver;

		try{
			Scanner read = new Scanner(file);

			while (read.hasNext()) { // while lines to read
				type = read.next();

				if (type.equals("schedule")) {
					String process = read.next(); // process
					long deadline = read.nextLong(); // deadline time
					long duration = read.nextLong(); // duration time
					// create new task based on schedule
					Record newRecord = new Record(process, deadline, duration);

					queue.add(newRecord); // add to priority queue
					print(1, current_time, newRecord); // prints add msg					
				}

				else if(type.equals("run")) { // if command is run
					endTime = read.nextLong();
					// while there is still time to run
					while (current_time < endTime) { 
						task = queue.poll();
						print(2, current_time, task);
						timeAvail = endTime - current_time;
						timeNeeded = task.GetDuration();

						if (timeNeeded > timeAvail) { // if task not completed
							timeLeft = timeNeeded - timeAvail;
							leftOver = new Record(task, timeLeft);
							queue.add(leftOver);
							current_time = endTime; // add back to queue
							print(1, current_time, leftOver);
						}
						// if task completed before run time is over
						else if (timeNeeded < timeAvail) { 
							current_time += timeNeeded;
							print(3, current_time, task);
						}
						// if task completed at same time run time is over
						else if (timeNeeded == timeAvail) {
							current_time = endTime;
							print(3, current_time, task);
						}
					}
				}

			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);

	}

}
