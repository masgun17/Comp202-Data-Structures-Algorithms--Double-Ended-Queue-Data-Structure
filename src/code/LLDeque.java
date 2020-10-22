package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  Mertcan Asgun
 * Class : LLDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the linked list yourself
 * Note that it should be a doubly linked list
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Iterator;
import given.Util;

//If you have been following the class, it should be obvious by now how to implement a Deque wth a doubly linked list
public class LLDeque<E> implements iDeque<E> {
  
  //Use sentinel nodes. See slides if needed
  private Node<E> header;
  private Node<E> trailer;
  
  /*
   * ADD FIELDS IF NEEDED
   */

  private int size;
  
  // The nested node class, provided for your convenience. Feel free to modify
  private class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> prev;
    /*
     * ADD FIELDS IF NEEDED
     */
    
    Node(T d, Node<T> n, Node<T> p) {
      element = d;
      next = n;
      prev = p;
    }

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
    
    /*
     * ADD METHODS IF NEEDED
     */
  }
  
  public LLDeque() {
    //Remember how we initialized the sentinel nodes
    header  = new Node<E>(null, null, header);
    trailer = new Node<E>(null, trailer, header);
    header.next = trailer;
    
    /*
     * ADD CODE IF NEEDED
     */
    size=0;
  }
  
  public String toString() {
    if(isEmpty())
      return "";
    StringBuilder sb = new StringBuilder(1000);
    sb.append("[");
    Node<E> tmp = header.next;
    while(tmp.next != trailer) {
      sb.append(tmp.element.toString());
      sb.append(", ");
      tmp = tmp.next;
    }
    sb.append(tmp.element.toString());
    sb.append("]");
    return sb.toString();
  }
  
  /*
   * ADD METHODS IF NEEDED
   */
  
  /*
   * Below are the interface methods to be overriden
   */

  @Override
  public int size() {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  return size;
    //return 0;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  return size==0;
    //return false;
  }

  @Override
  public void addFront(E o) {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  Node<E> node;
	  node  = new Node<E>(o, header.getNext(), header);
	  header.setNext(node);
	  node.getNext().setPrev(node);
	  size++;
  }

  @Override
  public E removeFront() {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  if(isEmpty()) {
		  return null;
	  }
	  E obj=header.getNext().getElement();
	  header.setNext(header.getNext().getNext());
	  header.getNext().setPrev(header);
	  size--;
	  return obj;
    //return null;
  }

  @Override
  public E front() {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  return header.getNext().getElement();
    //return null;
  }

  @Override
  public void addBehind(E o) {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  Node<E> node;
	  node  = new Node<E>(o, trailer, trailer.getPrev());
	  trailer.setPrev(node);
	  node.getPrev().setNext(node);
	  size++;
  }

  @Override
  public E removeBehind() {
    // TODO Auto-generated method stub
    //Util.NotImplementedYetSoft();
	  if(isEmpty()) {
		  return null;
	  }
	  E obj=trailer.getPrev().getElement();
	  trailer.setPrev(trailer.getPrev().getPrev());
	  trailer.getPrev().setNext(trailer);
	  size--;
	  return obj;
    //return null;
  }

  @Override
  public E behind() {
    // TODO Auto-generated method stub
//    Util.NotImplementedYetSoft();
	  return trailer.getPrev().getElement();
//    return null;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    header.setNext(trailer);
    trailer.setPrev(header);
    size=0;
  }
  
  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    //Hint: Fill in the LLDequeIterator given below and return a new instance of it
	  LLDequeIterator iter=new LLDequeIterator();
	  return iter;
    //return null;
  }
  
  private final class LLDequeIterator implements Iterator<E> {
    
	  private Node<E> current=header.getNext();
    /*
     * 
     * ADD A CONSTRUCTOR IF NEEDED
     * Note that you can freely access everything about the outer class!
     * 
     */

	  
    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
    	if(current.getElement()==null) {
			return false;
		} return true;
      //return false;
    }

    @Override
    public E next() {
      // TODO Auto-generated method stub
    	if(!hasNext()) {
    		return null;
    	}
    	E obj = current.getElement();
        current = current.getNext();
        return obj;
      //return null;
    }        
  }
  
}
