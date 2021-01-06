package Homework3;

import java.util.*;
public class DList<E> implements Iterable<E>,Cloneable{
		
	private static class DListNode<E> {		
		public E data;
		public DListNode<E> next;
		public DListNode<E> prev;
		
	}
	
	private DListNode<E> nil;
	
	public DList() {
		nil = new DListNode<E>();
		//initialize nil to be the head & tail 
		nil.next = nil;
		nil.prev = nil;
		nil.data = null;			
	}
	
	public void addFirst(E elem) {
		
		DListNode<E> newNode = new DListNode<E>();
		
		newNode.data = elem;  //set the data
		newNode.prev = nil; //make the new nodes previous nil
		newNode.next = nil.next; //set the new nodes next to point to nil.next
	
		nil.next.prev = newNode; //make nil.nexts previous point to new node 	
		nil.next = newNode; //make the newnode nil.next
	}
	
	public void addLast(E elem) {
		
		DListNode<E> newNode = new DListNode<E>();
		
		newNode.data = elem; //set data
		newNode.next = nil; //make the new node point to nil 
		
		newNode.prev = nil.prev; //newNodes previous is the "tail"
		
		nil.prev.next = newNode; //link it 
		nil.prev = newNode;
		
		
		
	}
	public E getFirst() {
		
		if(nil.next == nil){  //if the head is equal to nil you don't have a head you just have nil so nothing to return  
			throw new NoSuchElementException();
		}	
		return nil.next.data; //return data 
		
	}
	
	public E getLast() {
		
		if(nil.prev == nil)
			throw new NoSuchElementException(); //if empty list 
		
		return nil.prev.data; //return data 
	}
	
	public E removeFirst() {
		
		if(nil.next == nil) //if empty list 
			throw new NoSuchElementException();
		
		E old = nil.next.data; //set the data to be removed to old 
		
		nil.next = nil.next.next; //connect the nodes 
		nil.next.prev = nil;
		
		return old;
	}
	
	public E removeLast() {
		
		if(nil.prev == nil) //if empty list 
			throw new NoSuchElementException();
		
		E old;
		
		old= nil.prev.data; //old data
				
		nil.prev = nil.prev.prev; //set nodes 
		nil.prev.next = nil;
		
		return old;
	}
	
	public E get(int index) {
				
		int count =0;
		
		DListNode<E> iter = nil.next;
		
		while(count < index){ 
			iter = iter.next;
			count++;			//count until you hit the index searched
		}
		return iter.data;
		
	}
	
	
	public E set(int index, E value) {
		
		DListNode<E> iter = nil.next;
		
		if(index<0 || index >=size())
			throw new IndexOutOfBoundsException();
		
		
		int count =0;
		while(count < index){
			count++;
			iter = iter.next;	//find position 					
		}
		E old = iter.data;
		iter.data = value; //set data 
		
		return old;			
	}
	
	
	public boolean Contains(Object obj){
		
		DListNode<E> iter = nil.next;
		while(iter != nil) {
			if(iter.data == obj) //if iter is == obj 
				return true;
			iter = iter.next;
		}
		return false;		
	}
	
	public int size() {
		
		int numElements =0;
		
		DListNode<E> iter = nil.next;
		while(iter != nil) {
			iter = iter.next;
			numElements++; //count until hit nil 
		}
		
		return numElements;
		
	}
	
	
	public int indexOf(Object obj){
		
		DListNode<E> node = new DListNode<E>();
		node = nil.next;
		
		int count=0;
		while(node != nil) {		
			if(node.data == obj) {
				return count;	//if found the object return pos 
			}
			else {
			node = node.next; //else move forward 1
			count++;
			}
		}
		
		
		return -1; //not found
	}
	
	
	public Iterator<E> iterator(){
		
		return new DListIterator();
	}
	
	private class DListIterator implements Iterator<E>{
		
		private DListNode<E> pointer;
		
		public DListIterator() {
			
			pointer = nil.next; //set the pointer 
		}
		
		public boolean hasNext() {
			
			return pointer != nil; //true until it is nil 
		
			
		}
		
		public E next() {
			
			pointer = pointer.next;
			return pointer.prev.data;
			}
			
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	
	//EXTRA CREDIT
	
	public Object clone() {
		
		DList<E> copyList = new DList<E>();
	
		for(E e: this) {
			copyList.addLast(e);
			
		}
		
		return copyList;
	
}
	public boolean add(E e) {
		
		DListNode<E> newNode = new DListNode<E>();
		
		newNode.data = e;
		newNode.next = nil;
		
		newNode.prev = nil.prev;
		
		nil.prev.next = newNode;
		nil.prev = newNode;
		
		return true;
			
	}
	
	public void add(int index, E element) {
		
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		DListNode<E> newNode = new DListNode<E>();		
		DListNode<E> pointer = nil;
		
		int count =0;
		while(count < index) {
			//System.out.println(1);
			count++;
			pointer = pointer.next;			
		}
		
		if(count == 0) {
			addFirst(element);
		}
		else if(count == size()) {
			addLast(element);
		}
		else {
			
			newNode.data = element;
				
			newNode.next = pointer.next;
			pointer.next.prev = newNode;
		
			pointer.next = newNode;
			newNode.prev = pointer;
			
		}
	}
	
	public void clear() {
		
		DListNode<E> iter = nil.next;
		
		
		while(iter != nil) {
			
			nil.next = nil.next.next;
			iter = iter.next;
			
		}
		
	}
	

	public E Element() {
		
		return getFirst();
		
	}
	
	public int lastIndexOf(Object o) {
		
		DListNode<E> iter = nil.prev;
		
		int count =size();
		
		while(iter != nil) {
			if(iter.data .equals(o))
				return count;
			else {
			iter = iter.prev;
		    count--;
			}
		}
		return -1;
	}
	
	public boolean offer(E e){
		
		 addLast(e);
		 return true;
		
	}
	
	public boolean offerFirst(E e) {
		
		addFirst(e);
		
		return true;
	}
		
	
		public boolean offerLast(E e) {
			
			addLast(e);
			return true;
		}
		
		public E peek(){
			
			return getFirst();
		}
		
		public E peekFirst() {
			
			return getFirst();
		}
		
		public E peekLast() {
			
			return getLast();
		}
		
		public E poll() {
			
			return removeFirst();
			
			
		}
		
		public E  pollFirst() {
			
			return getFirst();
		}
		
		public E polllast() {
			
			return getLast();
			
		}
		
		public E remove() {
			
			return removeFirst();
		}
	
		public E remove(int index) {
			
			if(index < 0 || index >= size()) {
				throw new IndexOutOfBoundsException("Error out of bounds");
			}
			
			DListNode<E> iter = nil.next;
			
			if(iter == nil) {
				return null;
			}
			
			int count =0;
			while(count != index) {
					iter = iter.next;
					count++;
			}			
				
			iter.prev.next = iter.next;
			iter.next = iter.prev;
			
			return iter.data;	

		}
		
		public boolean remove(Object o) {
			
			DListNode<E> delete = nil.next;
			
			while(delete != nil) {
				if(delete.data == o) {
					delete.prev.next = delete.next;
					delete.next.prev = delete.prev;
					return true;
				}
				else {
					delete = delete.next;
				}
			}
			return false;
			
		}
		
		public boolean removeFirstOccurence(Object o) {
			
			return remove(o);
		}
		
		public boolean removeLastOccurence(Object o) {
			
			DListNode<E> delete = nil.prev;
			
			while(delete != nil) {
				if(delete.data == o) {
					delete.prev.next = delete.next;
					delete.next.prev = delete.prev;
					return true;
				}
				else {
					delete = delete.prev;
				}
			}
			return false;
		
		}
		
		public E pop() {
			
			return removeLast();
		}
		
		public void push(E e) {
			
			add(e);
		}
		
		
		public Iterator<E> descendingIterator(){
			
			return new ReverseIterator();				
		}
		
		private class ReverseIterator implements Iterator<E>{
			
			private DListNode<E> pointer;
			
			public ReverseIterator() {
				this.pointer = nil.prev;
			}
							
			public E next() {
				
				pointer = pointer.prev;
				return pointer.next.data;				
			}
			
			public boolean hasNext() {
				
				return pointer != nil;
				
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
					
		}
		
		
		
		
		public Object[] toArray() {
			
			Object[] arr = new Object[size()];
			 int i=0;
			 
			 
			 for(E e: this) {
				 arr[i] =e;
				 i++;
			 }
			 return arr;
		
		}
		
		public <T> T[] toArray(T[] a) {
			
			if(a.length < size()) {
				T[] arr = (T[]) a[size()];
				int i =0;
				for(E e: this) {
					arr[i] = (T)e;
					i++;
				}
				return arr;
			}
			else {
				int i=0;
				for(E e: this) {
					a[i] = (T)e;
					i++;
				}
				
			}
			return a;

		}
	}


