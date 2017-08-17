/**
 * @author Robert Kalish
 * CMSC 256 Data Structures and Object Programming
 * Spring 2017
 * Programming Project 4
 * Request Class
 */


public class Request implements Comparable<Request>{
	private int idNum;
	private int execTime;
	private int entryTime;
	private int priority;
	private int remExecTime;
	private int enterInputQTime = 100;
	private int exitInputQTime = 100;
	private int doneTime = 100;
	
	public Request(int idNum, int execTime, int entryTime, int priority) {
		this.idNum = idNum;
		this.execTime = execTime;
		this.entryTime = entryTime;
		this.priority = priority;
		setRemExecTime(execTime);
		
	}

	/**
	 * @return the idNum
	 */
	public int getIdNum() {
		return idNum;
	}

	/**
	 * @param idNum the idNum to set
	 */
	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	/**
	 * @return the execTime
	 */
	public int getExecTime() {
		return execTime;
	}

	/**
	 * @param execTime the execTime to set
	 */
	public void setExecTime(int execTime) {
		this.execTime = execTime;
	}

	/**
	 * @return the entryTime
	 */
	public int getEntryTime() {
		return entryTime;
	}

	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(int entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the remExecTime
	 */
	public int getRemExecTime() {
		return remExecTime;
	}

	/**
	 * @param remExecTime the remExecTime to set
	 */
	public void setRemExecTime(int remExecTime) {
		this.remExecTime = remExecTime;
	}

	/**
	 * @return the enterInputQTime
	 */
	public int getEnterInputQTime() {
		return enterInputQTime;
	}

	/**
	 * @param enterInputQTime the enterInputQTime to set
	 */
	public void setEnterInputQTime(int enterInputQTime) {
		this.enterInputQTime = enterInputQTime;
	}

	/**
	 * @return the exitInputQTime
	 */
	public int getExitInputQTime() {
		return exitInputQTime;
	}

	/**
	 * @param exitInputQTime the exitInputQTime to set
	 */
	public void setExitInputQTime(int exitInputQTime) {
		this.exitInputQTime = exitInputQTime;
	}

	/**
	 * @return the doneTime
	 */
	public int getDoneTime() {
		return doneTime;
	}

	/**
	 * @param doneTime the doneTime to set
	 */
	public void setDoneTime(int doneTime) {
		this.doneTime = doneTime;
	}

	public int compareTo(Request other) {
		// TODO Auto-generated method stub
		return ((Integer)(this.priority)).compareTo((other).priority);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Request [idNum=" + idNum + ", execTime=" + execTime + ", entryTime=" + entryTime + ", priority="
				+ priority + ", remExecTime=" + remExecTime + ", enterInputQTime=" + enterInputQTime
				+ ", exitInputQTime=" + exitInputQTime + ", doneTime=" + doneTime + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Request))
			return false;
		Request other = (Request) obj;
		if (doneTime != other.doneTime)
			return false;
		if (enterInputQTime != other.enterInputQTime)
			return false;
		if (entryTime != other.entryTime)
			return false;
		if (execTime != other.execTime)
			return false;
		if (exitInputQTime != other.exitInputQTime)
			return false;
		if (idNum != other.idNum)
			return false;
		if (priority != other.priority)
			return false;
		if (remExecTime != other.remExecTime)
			return false;
		return true;
	}
	
}
