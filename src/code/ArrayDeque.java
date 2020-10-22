package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  Mertcan Asgun
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Arrays;
import java.util.Iterator;

import given.Util;


public class ArrayDeque<E> implements iDeque<E> {

	private E[] A; //Do not change this name!

	/*
	 * ADD FIELDS IF NEEDED
	 */
	private int size; // number of elements
	private int capacity;  // capacity of the array
	private int front;  // first element
	private int behind; // last element

	public ArrayDeque() {
		this(1000);
		/*
		 * ADD CODE IF NEEDED
		 */
		size=0;
		capacity=1000;
		front=0;
	}

	public ArrayDeque(int initialCapacity) {
		if(initialCapacity < 1)
			throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);

		/*
		 * ADD CODE IF NEEDED
		 */
		size=0;
		capacity=initialCapacity;
		front=0;
	}

	// This is given to you for your convenience since creating arrays of generics is not straightforward in Java
	@SuppressWarnings({"unchecked" })
	private E[] createNewArrayWithSize(int size) {
		return (E[]) new Object[size];
	}

	//Bonus: Modify this such that the dequeue prints from front to back!
	//Hint, after you implement the iterator, use that!
	public String toString() {
		if(size==0) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Iterator<E> iter = iterator();
		while(iter.hasNext()) {
			E e = iter.next();
			if(e == null)
				continue;
			sb.append(e);
			if(!iter.hasNext())
				sb.append("]");
			else
				sb.append(", ");
		}
		return sb.toString();
		}
		
	}


	/*
	 * ADD METHODS IF NEEDED
	 */

	private void resizeStorage() {
		E[] B= createNewArrayWithSize(capacity*2);
		for(int i=front; i<front+size;i++) {
			B[i-front]=A[i%capacity];
		}
		A=B;
		front=0;
		capacity=A.length;
		//behind= (front+size-1)%capacity;
	}

	public int capacity() {
		return A.length;
	}

	/*
	 * Below are the interface methods to be overriden
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		return this.size;
		//return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		return this.size==0;
		//return false;
	}

	@Override
	public void addFront(E o) {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		if(size==capacity) {
			resizeStorage();
		}
		if(front>0) {
			front=front-1;
		} else front= front-1+capacity;
		A[front]=o;
		size++;
	}

	@Override
	public E removeFront() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		if(isEmpty()) {
			return null;
		}
		E object=A[front];
		A[front]=null;
		if(front>=capacity-1) {
			front=(front+1)-capacity;
		} else front=(front+1);
		size--;
		return object;
		//return null;
	}

	@Override
	public E front() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		}
		return A[front];
		//return null;
	}

	@Override
	public void addBehind(E o) {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		if(size==capacity) {
			resizeStorage();
		} 
		behind= (front+size-1)%capacity;
		if (behind>=capacity-1) {
			behind=behind+1-capacity;
		} else behind=behind+1;
		A[behind]=o;
		size++;
	}

	@Override
	public E removeBehind() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		behind= (front+size-1)%capacity;
		if(isEmpty()) {
			return null;
		}
		E object=A[behind];
		A[behind]=null;
		if(behind>0) {
			behind=behind-1;
		}else behind=behind-1+capacity;
		size--;
		return object;
		//return null;
	}

	@Override
	public E behind() {
		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		behind= (front+size-1)%capacity;
		if(isEmpty()) {
			return null;
		} return A[behind];
		//return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i= front; i<front+size; i++) {
			A[i%capacity]=null;
		}
		size=0;
	}

	//Must print from front to back
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		//Hint: Fill in the ArrayDequeIterator given below and return a new instance of it
		ArrayDequeIterator iter=new ArrayDequeIterator();
		return iter;
		//return null;
	}

	private final class ArrayDequeIterator implements Iterator<E> {

		private int counter;

		/*
		 * 
		 * ADD A CONSTRUCTOR IF NEEDED
		 * Note that you can freely access everything about the outer class!
		 * 
		 */

		public ArrayDequeIterator() {
			counter=front;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(A[counter]==null) {
				return false;
			} return true;
			//return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			//if(!hasNext()) return null;
			E obj=A[counter];
			counter=(counter+1)%capacity;
			return obj;
			//return null;
		}        
	}
}
