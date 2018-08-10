
public class Process {

	private int size;
	private int processID;
	
	public Process(int s, int p) {
		size = s;
		processID = p;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
}
