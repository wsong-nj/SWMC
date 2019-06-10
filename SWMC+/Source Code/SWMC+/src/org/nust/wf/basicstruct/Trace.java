package org.nust.wf.basicstruct;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * 类名：Trace
 * 用途：路径的基本类
 *
 */
public class Trace {

	/**
	 * Trace中的结点集合
	 */
	private String fileName;

	private ArrayList<String> events;
	
	private HashMap<String,Integer> eventFrequency;	
	

	public Trace() {
		fileName = "";
		events = new ArrayList<String>();
	}
	
	public Trace(ArrayList<String> events){
		this.events = events;
		
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<String> getEvents() {
		return events;
	}
	
	public void addEvent(String event){
		events.add(event);
	}
	public void setEvents(ArrayList<String> events) {
		this.events = events;
	}

	public HashMap<String, Integer> getEventFrequency() {
		return eventFrequency;
	}
	
	public void setEventFrequency(HashMap<String, Integer> eventFrequency) {
		this.eventFrequency = eventFrequency;
	}
	
	public void countFrequency(){
		eventFrequency = new HashMap<String,Integer>();
		for(String s:getEvents()){
			Integer freq = eventFrequency.get(s);
			eventFrequency.put(s,freq == null?1:freq+1);
		}
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Trace))
			return false;
		Trace t = (Trace)obj;
		return this.getEvents().equals(t.getEvents());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return events.toString();
	}

	@Override
	public Trace clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Trace clone = new Trace();
		clone.setEvents(this.events);
		return clone;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hash = 0;
		for(String s:getEvents())
			hash+= s.hashCode();
		return hash;
	}
}
