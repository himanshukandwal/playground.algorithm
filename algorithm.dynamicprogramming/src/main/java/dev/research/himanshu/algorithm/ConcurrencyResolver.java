package dev.research.himanshu.algorithm;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * a class mimicking the behavior of 'wait-for' table that we have in database. 
 * This will be an actively running program intercepting and resolving deadlocking scenarios.
 *      
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class ConcurrencyResolver {
	
	// singleton instance
	private static ConcurrencyResolver instance = new ConcurrencyResolver();

	/* a set mimicking the behavior of 'wait-for' table that we have in database. This is needed to eliminate the scenarios leading to dead-lock. */
	private Set<WaitForEntry> waitingForResourcesSet = new ConcurrentSkipListSet<>();
	
	/**
	 * constructor.
	 */
	private ConcurrencyResolver() {
		// private hidden constructor. 
	}
		
	/**
	 * static access to the singleton instance.
	 * 
	 * @return
	 */
	public static ConcurrencyResolver getInstance() {
		return instance;
	}
	
	/**
	 * getter method for 'waitForSet' set.
	 * 
	 * @return
	 */
	public synchronized Set<WaitForEntry> getWaitingForResourcesSet() {
		// avoiding deadlocking (double locking)
		synchronized (this) {
			return waitingForResourcesSet;
		}
	}
	
	/**
	 * WaitForEntry class.  
	 * 
	 **/
	public class WaitForEntry implements Comparable<WaitForEntry> {
		
		private int requestingResourceId;
		private int requestedResourceId;
		
		/**
		 * constructor.
		 */
		public WaitForEntry(int requestingResourceId, int requestedResourceId) {
			this.requestingResourceId = requestingResourceId;
			this.requestedResourceId = requestedResourceId;
		}

		@Override
		public int compareTo(WaitForEntry otherWaitForEntry) {
			int result = -1;
			if ((otherWaitForEntry.requestingResourceId == requestingResourceId && otherWaitForEntry.requestedResourceId == requestedResourceId) 
					|| (otherWaitForEntry.requestingResourceId == requestedResourceId && otherWaitForEntry.requestedResourceId == requestingResourceId)) {
				result = 0; 
			}
			return result;
		}
		
		@Override
		public String toString() {
			return requestingResourceId + " : " + requestedResourceId;
		}
		
		@Override
		public int hashCode() {
			/* this is a very crude way of computing hash-code, however, resulting to this and 
			 * not using : Arrays.hashcode(new int [] {requestingResourceId, requestedResourceId}) as we have preserve the same value if 
			 * the values of requestingResourceId and requestedResourceId gets swapped. i.e (3,2) should be same as (2,3) 
			 * */
			return Integer.valueOf(requestingResourceId).hashCode() + Integer.valueOf(requestedResourceId).hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof WaitForEntry)) 
				return false;
			return (this.compareTo((WaitForEntry) obj) == 0 ? true : false);
		}
		
	}
	
	/**
	 * monitoring threading.
	 */
	private class WaitForCollectionMonitor implements Runnable {
		
		private boolean disable;
		
		public boolean isDisable() {
			return disable;
		}
		
		public void setDisable(boolean disable) {
			this.disable = disable;
		}
		
		@Override
		public void run() {
			while (!disable) {
//				getWaitingForResourcesSet()
			}
		}
		
	}
	
}
