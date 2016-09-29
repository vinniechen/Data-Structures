package hw6;

/* NAME: Vinnie Chen
 * PID: A12148745
 * LOGIN: cs12sau
 */

/**
 * Record class holds the process, deadline, and duration info of each task
 * @version 1.0
 * @author Vinnie Chen
 * @since 5-10-16
 */

public class Record implements Comparable<Record> {

	public String process;
	public long deadline; 
	private long duration; 

	// constructor to create a new record
	// given the name of the process,
	// deadline and duration
	Record (String process, long deadline, long duration)
	{
		this.process = process;
		this.deadline = deadline;
		this.duration = duration;
	}

	// constructor to create a new record
	// from the existing record and new
	// duration
	Record (Record r, long duration)
	{
		this.process=r.process;
		this.deadline=r.deadline;
		this.duration=duration;
	}

	public long GetDuration()
	{
		return duration;
	}

	/** 
	 * ToString method to print if task completed on time or late
	 */
	public String toString()
	{
		return process+" with deadline "+deadline+" and duration "+duration;
	}
	public String toString(long current_time)
	{
		if(current_time > deadline) return process + " (late)";
		return process;
	}

	/**
	 * Compares the deadline of the object for the min heap
	 * @param record to be compared to
	 */
	@Override
	public int compareTo(Record o) 
	{
		return (int)(this.deadline - o.deadline);
	}
}
