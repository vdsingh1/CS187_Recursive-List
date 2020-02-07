package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveList<T> implements ListInterface<T>{
  private int size = 0;
  LLNode<T> head;

  @Override
  public Iterator<T> iterator() {
    return new RecursiveListIterator<T>(head);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public ListInterface<T> insertFirst(T elem) {
    if (elem == null) throw new NullPointerException();
    size++;
    if (isEmpty()) {
      head = new LLNode<T>(null, elem);
      return this;
    }
    LLNode<T> newNode = new LLNode<T>(head, elem);
    head = newNode;
    return this;
  }

  @Override
  public ListInterface<T> insertLast(T elem) {
    if (elem == null) throw new NullPointerException();
    if (isEmpty())insertFirst(elem);
    else insertAt(size, elem);
    return this;
  }

  @Override
  public ListInterface<T> insertAt(int index, T elem) {
    if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    if (elem == null) throw new NullPointerException("the element you attempted to insert was null");
    if (index == 0) {
      insertFirst(elem);
    }else {
      LLNode<T> newNode = new LLNode<T>(null, elem);
      insertAt(head, newNode, index);
    }
    return this;
  }
  
  private void insertAt(LLNode<T> currentNode, LLNode<T> newNode, int index){
    if (index - 1 == 0) {//the element before the target
      if (index != size) {
        //System.out.println("tried to at.: " + newNode.data);

        LLNode<T> prevNext = currentNode.next;
        currentNode.next = newNode;
        newNode.next = prevNext;
      }else {
        //System.out.println("tried to insertLast.: " + newNode.data);
        currentNode.next = newNode;
      }
      size++;
    }else {
      insertAt(currentNode.next, newNode, --index);
    }
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) throw new IllegalStateException();
    return removeAt(0);
  }

  @Override
  public T removeLast() {
    if (isEmpty()) throw new IllegalStateException();
    return removeAt(size - 1);
  }

  @Override
  public T removeAt(int i) {
    if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    return removeAt(head, i);
  }
  
  private T removeAt(LLNode<T> currentNode, int i) {
    if (i == 0) {
      T data = head.data;
      head = head.next;
      size--;
      return data;
    }
    if (i == 1) {//node before the target node
      T data = currentNode.next.data;
      currentNode.next = currentNode.next.next;
      size--;
      return data; 
    }
    return removeAt(currentNode.next, --i);
  }

  @Override
  public T getFirst() {
    if (isEmpty()) throw new IllegalStateException();
    return head.data;
  }

  @Override
  public T getLast() {
    if (isEmpty()) throw new IllegalStateException();
    return get(size - 1);
  }

  @Override
  public T get(int i) {
    if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    return get(head, i);
  }

  private T get(LLNode<T> current, int i){  
    if (i == 0) {
      return current.data;
    }
    return get(current.next, --i);
  }

  @Override
  public boolean remove(T elem) {
    if (elem == null) throw new NullPointerException();
    if (elem.equals(head.data)) {
      head = head.next;
      size--;
      return true;
    }
    return remove(head, new LLNode<T>(null, elem));
  }
  
  private boolean remove(LLNode<T> current, LLNode<T> target) {
    if (current.next == null) {
      return false;
    }
    if (current.next.data.equals(target.data)){
      //remove the element
      current.next = current.next.next;
      size--;
      return true;
    }
    return remove(current.next, target);
  }

  @Override
  public int indexOf(T elem) {
    if (elem == null) throw new NullPointerException();
    return indexOf(head, elem, 0);
  }
  private int indexOf(LLNode<T> current, T elem, int index) {
    if (current == null) {
      return -1;
    }
    if (current.data.equals(elem)) {
      return index;
    }
    return indexOf(current.next, elem, ++index);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

}
class LLNode<T>{
  LLNode<T> next;
  T data;

  public LLNode(LLNode<T> next, T data) {
    this.next = next;
    this.data = data;
  }

}

class RecursiveListIterator<T> implements Iterator<T> {
  LLNode<T> head;
  LLNode<T> currentNode = head;

  // Constructors
  public RecursiveListIterator(LLNode<T> head) {
    this.head = head;
    currentNode = this.head;
  }

  @Override
  public boolean hasNext() {
    return (currentNode != null);
  }

  @Override
  public T next() {
    if (currentNode == null) {
      throw new NoSuchElementException();
    }
    T data = currentNode.data;
    if (hasNext())
      currentNode = currentNode.next;
    return data;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
