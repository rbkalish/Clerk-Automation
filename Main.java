/**
 * @author Robert Kalish
 * CMSC 256 Data Structures and Object Programming
 * Spring 2017
 * Programming Project 4
 * Project4 Main
 * Reads in input file and computes statistics
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Project4 {
	
	public Project4(){
		
	}
	
	public static void main(String[] args){
		String fileName;
		Scanner input = new Scanner(System.in);
		if (args[0] != null){
			fileName = args[0];
		}
		else {fileName = promptForFileName(input);}
		Scanner fileReader = openFile(fileName, input);
		ArrayList<Request> totalJobs = readFile(fileReader);
		ArrayList<Request> futureJobs = new ArrayList<Request>(totalJobs);
		Clerk clerk = new Clerk();
		File outFile = new File("kalishOutput.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do{
			futureJobs = clerk.addRequest(futureJobs);
			clerk.selectNextRequest();
			clerk.processRequest();
			clerk.incrementTime();
		}while(clerk.getTime() < 100);//!clerk.isDone() || !futureJobs.isEmpty());
		for (Request req : totalJobs){
			System.out.println(req);
		}
		ArrayList<Request> reqList = new ArrayList<Request>(totalJobs);
		printHeading(writer);
		reportStatistics(reqList, writer);
		writer.println("Average Time in Queue: " + avgInQTime(totalJobs));
		writer.println("Average Time in Stack: " + avgStackTime(totalJobs));
		writer.close();
	}
	
	public static String promptForFileName(Scanner input){
		System.out.println("A file name was not entered or it was entered incorrectly... Please enter the neame of your input file.");
		String fileName = input.next();
		return fileName;
		
	}
	
	public static Scanner openFile(String str, Scanner scanner){
		File inFile = new File(str);
		while(!inFile.exists()){
			str = promptForFileName(scanner);
			inFile = new File(str);
		}
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileReader;
		
	}
	
	public static ArrayList<Request> readFile(Scanner fileReader){
		ArrayList<Request> reqList = new ArrayList<>();
		while(fileReader.hasNextLine()){
			String line = fileReader.nextLine();
			String[] tabs = line.split("\t");
			Request newReq = new Request(Integer.parseInt(tabs[0]), Integer.parseInt(tabs[1]), Integer.parseInt(tabs[2]), Integer.parseInt(tabs[3]));
			reqList.add(newReq);
		}	
		return reqList;
	}
	public static void reportStatistics(ArrayList<Request> reqList, PrintWriter out){
		ArrayList<Request> removed = new ArrayList<Request>();
		for (int time = 0; !reqList.isEmpty(); time++){
			out.println(time + ":");
			for (Request job : reqList){
				if (time == job.getEntryTime()){
					out.println("Request: #" + job.getIdNum() + " arrives");
				}
				if (time == job.getExitInputQTime()){
					out.println("Request: #" + job.getIdNum() + " begins processing");
				}
				if (time == job.getDoneTime()){
					out.println("Request: #" + job.getIdNum() + " finished processing");
					removed.add(job);
				}
			}
			for (Request remove : removed){
				reqList.remove(remove);
			}
		}
	}
	
	public static double avgInQTime(ArrayList<Request> totalJobs){
		double totalQtime = 0;
		for (Request job : totalJobs){
			totalQtime = totalQtime + (job.getExitInputQTime() - job.getEnterInputQTime());
		}
		return (totalQtime/(double)totalJobs.size());
	}
	
	public static double avgStackTime(ArrayList<Request> totalJobs){
		double totalStackTime = 0;
		for (Request job : totalJobs){
			totalStackTime = totalStackTime + (job.getDoneTime() - job.getExitInputQTime());
		}
		return (totalStackTime/(double)totalJobs.size());
	}
	
	private static void printHeading(PrintWriter writer){
		writer.println("Robert Kalish");
		writer.println("Spring 2017");
		writer.println("CMSC 256");
		writer.println("Programming Project 4");
		writer.println("Output File");
		writer.println("Clerk Procressing Statistics\n\n");
	}
}
