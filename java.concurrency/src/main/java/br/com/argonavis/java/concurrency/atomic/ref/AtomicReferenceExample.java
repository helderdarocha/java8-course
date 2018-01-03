package br.com.argonavis.java.concurrency.atomic.ref;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

public class AtomicReferenceExample {
	
	static class Point {
		Point(int x, int y) {
			this.x = x; 
			this.y = y;
		}
		int x, y;
		@Override public boolean equals(Object o) {
			if (o instanceof Point) {
				Point p = (Point)o;
				return p.x == x && p.y == y;
			}
			return false;
		}
	}
	
	public static void updateX(AtomicReference<Point> ref, int x) {
	    Point currentPoint = ref.get();
	    while (!ref.compareAndSet(currentPoint, new Point(x, currentPoint.y))) {
	        currentPoint = ref.get();
	    }
	}

	public static void updateY(AtomicReference<Point> ref, int y) {
	    Point currentPoint = ref.get();
	    while (!ref.compareAndSet(currentPoint, new Point(currentPoint.x, y))) {
	        currentPoint = ref.get();
	    }
	}
	
	public static void updatePoint(AtomicReference<Point> ref, int x, int y) {
	    Point currentPoint = ref.get();
	    while (!ref.compareAndSet(currentPoint, new Point(x, y))) {
	        currentPoint = ref.get();
	    }
	}

	public static void main(String[] args) {
		
		System.out.println("*** AtomicReference String ***");
		AtomicReference<String> ref = new AtomicReference<>();
		System.out.println("Reference: " + ref);
		ref.set("");
		IntStream.range('A', 'Z'+1).forEach(c->ref.getAndAccumulate("" + (char)c, (acc, ch) -> acc+ch));
		System.out.println("Reference: " + ref);
		
		Point p1 = new Point(1,2);
		Point p2 = new Point(1,2);
		
		System.out.println("\n*** AtomicReference Point ***");
		System.out.println(p1.equals(p2) ? "p1 equals p2" : "p1 is not equal to p2");
		System.out.println(p1 == p2 ? "p1 == p2" : "p1 != p2");
		
		AtomicReference<Point> ref2 = new AtomicReference<>();
		System.out.println("Reference: " + ref2);
		ref2.set(p1);
		System.out.println(p1 == ref2.get() ? "p1 == ref2.get()" : "p1 != ref2.get()");
		System.out.println(p2 == ref2.get() ? "p2 == ref2.get()" : "p2 != ref2.get()");
		
		System.out.println("Reference: (" + ref2.get().x +","+ ref2.get().y +")");
		updateY(ref2, 6);
		System.out.println("Reference: (" + ref2.get().x +","+ ref2.get().y +")");
		updateX(ref2, 12);
		System.out.println("Reference: (" + ref2.get().x +","+ ref2.get().y +")");
		updatePoint(ref2, 5, 7);
		System.out.println("Reference: (" + ref2.get().x +","+ ref2.get().y +")");
	
		System.out.println("\n*** AtomicMarkableReference (Solves ABA problem if B occurs once - ex: marked as deleted) ***");
        AtomicMarkableReference<String> booRef = new AtomicMarkableReference<>("Initial Value", false);
        System.out.println("Reference: " + booRef.getReference() + ", marker: " + booRef.isMarked());
        
        System.out.println("Change marker to true if text is 'Initial Value'");
        booRef.attemptMark("Initial Value", true);
        System.out.println("Reference: " + booRef.getReference() + ", marker: " + booRef.isMarked());
        // Exercise: demonstrate ABA problem and solution with AtomicMarkableReference
        
        System.out.println("Change value to 'Second Value' with marker false if reference is 'Initial Value' and marker is true");
        booRef.compareAndSet("Initial Value", "Second Value", true, false);
        boolean[] marker = new boolean[1];
        System.out.println("Reference: " + booRef.get(marker) + ", marker: " + marker[0]);
        
        System.out.println("\n*** AtomicStampedReference (Solves ABA problem for multiple occurrences of B - ex: versioning) ***");
        AtomicStampedReference<String>  numRef = new AtomicStampedReference<>("Stamped Reference", 100);
        System.out.println("Reference: " + numRef.getReference() + ", stamp: " + numRef.getStamp());
        
        System.out.println("Change stamp to 101 if text is 'Stamped Reference'");
        numRef.attemptStamp("Stamped Reference", 101);
        System.out.println("Reference: " + numRef.getReference() + ", stamp: " + numRef.getStamp());
        
        System.out.println("Change value to 'Second Reference' with stamp 102 if reference is 'Stamped Reference' and stamp is 101");
        numRef.compareAndSet("Stamped Reference", "Second Reference", 101, 102);
        int[] stamp = new int[1];
        System.out.println("Reference: " + numRef.get(stamp) + ", marker: " + stamp[0]);
     // Exercise: demonstrate ABA problem and solution with AtomicStampedReference

	}

}
