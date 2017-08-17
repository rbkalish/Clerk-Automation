/**
 * @author Robert Kalish
 * CMSC 256 Data Structures and Object Programming
 * Spring 2017
 * Programming Project 4
 * Clerk Class
 */


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Clerk {
	private int time = 0;
	private PriorityQueue<Request> queue = new PriorityQueue<>();
	private Stack<Request> stack = new Stack<>();
	private Request currentReq;
	
	
	public Clerk(){
	}
	
	public boolean isDone(){
		return (queue.isEmpty() && stack.isEmpty());
	}
	
	public void processRequest(){
		if (currentReq == null){ return; }
		currentReq.setRemExecTime((currentReq.getRemExecTime()-1));
		stack.push(currentReq);
	}
	
	public void incrementTime(){
		currentReq = null;
		time = time + 1;
	}
	
	public int getTime(){
		return time;
	}
	
	public ArrayList<Request> addRequest(ArrayList<Request> reqList){
		ArrayList<Request> removed = new ArrayList<Request>();
		for (Request request : reqList){
			if (request.getEntryTime() == time){
				queue.add(request);
				request.setEnterInputQTime(time);
				removed.add(request);
			}
		}
		for (Request remove : removed){
			reqList.remove(remove);
		}
		return reqList;
	}
	
	public void selectNextRequest(){
		if (!stack.isEmpty()){
			if (stack.peek().getRemExecTime() == 0){
				stack.pop().setDoneTime(time);
				//selectNextRequest();
			}
		}
		if (stack.isEmpty() && queue.isEmpty()){
			currentReq = null;
			return;
		}
		else if (!stack.isEmpty() && queue.isEmpty()){
			currentReq = stack.pop();
		}
		else if (stack.isEmpty() && !queue.isEmpty()){
			currentReq = queue.poll();
			currentReq.setExitInputQTime(time);
		}
		else{
			int pick = queue.peek().compareTo(stack.peek());
			if (pick < 0){
				currentReq = queue.poll();
				currentReq.setExitInputQTime(time);
			} else {currentReq = stack.pop();}
		}
	}
}
