package com.example.controller;

import java.util.LinkedList;
import java.util.ListIterator;

public class NearestArea {
	private String next;
	private String previous;
	private LinkedList<String> allloc;
	public LinkedList<String> nearest(String loc,LinkedList<String> list) {
        LinkedList<String> link = list;
        ListIterator<String> it = link.listIterator();
        boolean found = false;

        while (it.hasNext()) {
            if (it.next().equals(loc)) {
                found = true;
                if (it.hasNext()) {
                	next=it.next();
//                    System.out.println("Next location: " + it.next());
                    it.previous(); 
                } else {
                	next=null;
//                    System.out.println("No next location.");
                }

               
                if (it.hasPrevious()) {
                    it.previous();
                }

                if (it.hasPrevious()) {
                	previous=it.previous();
//                    System.out.println("Previous location: " + it.previous());
                } else {
                	previous=null;
                    System.out.println("No previous location.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Location not found in the list.");
        }
        return link;
	}
	public String next(String area,LinkedList<String> list) {
		nearest(area,list);
		return next;
	}
	public String previous(String area,LinkedList<String> list) {
		nearest(area,list);
		return previous;
	}
}
