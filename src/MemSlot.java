
public class MemSlot {

	private int start;
	private int end;
	private int size;
	
	public MemSlot(int start, int end) {
		this.setStart(start);
		this.setEnd(end);
		setSize(end-start);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
		setSize(end-start);
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
		setSize(end-start);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
