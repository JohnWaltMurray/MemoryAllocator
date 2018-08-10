import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MemoryAllocator {

	public static void main(String[] args) {
		ArrayList<Process> processList = new ArrayList<Process>();
		ArrayList<MemSlot> slotList = new ArrayList<MemSlot>();
		int numSlots = 0;
		int numProcesses = 0;
		try {
			FileReader reader = new FileReader("Minput.data");
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			try {
				
				String next;
				numSlots = Integer.parseInt(bufferedReader.readLine());
				for(int i = 0; i < numSlots; i++) {
					next = bufferedReader.readLine();
					String[] splitString = next.split(" ");
					slotList.add(new MemSlot(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1])));
				}
				numProcesses = Integer.parseInt(bufferedReader.readLine());
				for(int i = 0; i < numProcesses; i++) {
					next = bufferedReader.readLine();
					String[] splitString = next.split(" ");
					processList.add(new Process(Integer.parseInt(splitString[1]), Integer.parseInt(splitString[0])));
				}
				
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		
		try {
			PrintWriter writer = new PrintWriter("FFoutput.data", "UTF-8");
			boolean found = false;
			String Allocated = "-";
			int temp = 0;
			ArrayList<MemSlot> ffSlot = new ArrayList<MemSlot>(slotList);
			
			//First Fit
			
			for(int j = 0; j < numProcesses; j++) {
				for(int h = 0; h < numSlots; h++) {
					if(ffSlot.get(h).getSize() > processList.get(j).getSize() && found == false) {
						temp = ffSlot.get(h).getStart()+processList.get(j).getSize();
						writer.println(ffSlot.get(h).getStart()+" "
													+temp+" "
													+processList.get(j).getProcessID());
						temp = 0;
						found = true;
						ffSlot.set(h, new MemSlot(ffSlot.get(h).getStart()+processList.get(j).getSize(), ffSlot.get(h).getEnd()));
					}
					if((h+1) == numSlots&&found == false) {
						temp = j+1;
						Allocated += temp+" ";
						temp = 0;
					}
				}
				found = false;
			}
			if(Allocated.equals("-")) {
				writer.println("-0");
			} else {
				writer.println(Allocated);
				Allocated = "-";
			}
			writer.close();
			
			writer = new PrintWriter("BFoutput.data", "UTF-8");
			
			//Best Fit
			
			MemSlot max = new MemSlot(0, 99999);
			MemSlot min = max;
			ArrayList<MemSlot> bfSlot = new ArrayList<MemSlot>(slotList);
			int minID = 0;
			for(int j = 0; j < numProcesses; j++) {
				for(int h = 0; h < numSlots; h++) {
					if(bfSlot.get(h).getSize() > processList.get(j).getSize()) {
						if(bfSlot.get(h).getSize() < min.getSize()) {
							min = bfSlot.get(h);
							minID = h;
						}
					}
				}
				if(min.getSize() < 90000) {
					temp = min.getStart()+processList.get(j).getSize();
					writer.println(min.getStart()+" "
												+temp+" "
												+processList.get(j).getProcessID());
					temp = 0;
					bfSlot.set(minID, new MemSlot(bfSlot.get(minID).getStart()+processList.get(j).getSize(), bfSlot.get(minID).getEnd()));
				} else {
					temp = j+1;
					Allocated += temp+" ";
					temp = 0;
				}
				min = max;
				found = false;
			}
			if(Allocated.equals("-")) {
				writer.println("-0");
			} else {
				writer.println(Allocated);
			}
			writer.close();
			
			writer = new PrintWriter("WFoutput.data", "UTF-8");
			
			//Worst Fit
			
			MemSlot worstMin = new MemSlot(0, 0);
			MemSlot worstMax = worstMin;
			
			ArrayList<MemSlot> wfSlot = new ArrayList<MemSlot>(slotList);
			int maxID = 0;
			for(int j = 0; j < numProcesses; j++) {
				for(int h = 0; h < numSlots; h++) {
					if(wfSlot.get(h).getSize() > processList.get(j).getSize()) {
						if(wfSlot.get(h).getSize() > worstMax.getSize()) {
							worstMax = wfSlot.get(h);
							maxID = h;
						}
					}
				}
				if(worstMax.getSize() > 0) {
					temp = worstMax.getStart()+processList.get(j).getSize();
					writer.println(worstMax.getStart()+" "
												+temp+" "
												+processList.get(j).getProcessID());
					temp = 0;
					wfSlot.set(maxID, new MemSlot(wfSlot.get(maxID).getStart()+processList.get(j).getSize(), wfSlot.get(maxID).getEnd()));
				} else {
					temp = j+1;
					Allocated += temp+" ";
					temp = 0;
				}
				worstMax = worstMin;
				found = false;
			}
			if(Allocated.equals("-")) {
				writer.println("-0");
			} else {
				writer.println(Allocated);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
