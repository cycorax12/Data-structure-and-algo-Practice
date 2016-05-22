package work.practice.dsa.linkedlist;

/**
 * Single Linked List
 * 
 * @author Virendra
 * 
 */
public class LinkedListApp {

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList();
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);

		list1.insert(node1);
		list1.insert(node3);
		list1.insert(node2);
		list1.insertFirst(node4);
		list1.insert(node5);
		node5.next = node2;
		System.out.println();
		System.out.println("Does list1 has cycle? :  "
				+ (new LinkedListUtil().isCycle(list1) ? "Yes" : "No"));

	}

}

class LinkedList {
	private Node head;

	/**
	 * Returns head of list.
	 * 
	 * @return
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Prints list
	 * 
	 * @param head
	 */
	public void printList(Node head) {
		if (head == null) {
			return;
		}
		System.out.printf("%d\t", head.data);
		printList(head.next);
	}

	/**
	 * Inserts new node to start of list
	 * 
	 * @param node
	 */
	public void insertFirst(Node node) {
		if (head == null) {
			head = node;
			return;
		}
		Node temp = head;
		head = node;
		node.next = temp;

	}

	/**
	 * Inserts new node to end of list.
	 * 
	 * @param node
	 */
	public void insert(Node node) {
		if (head == null) {
			head = node;
			return;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
	}

	/**
	 * Deletes node provided.
	 * 
	 * @param node
	 */
	public void delete(Node node) {
		if (head == null) {
			return;
		}
		if (node == head) {
			head = node.next;
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if (node == temp) {
					prev.next = temp.next;
					break;
				} else {
					prev = temp;
					temp = temp.next;

				}
			}
		}
	}
}

/**
 * Class to hold Node of linkedlist
 * 
 * @author Virendra
 * 
 */
class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
	}
}

/**
 * Util class related to linked list
 * 
 * @author Virendra
 * 
 */
class LinkedListUtil {

	/**
	 * Checks if Linked List has loop/cycle
	 * 
	 * @param list1
	 * @return
	 */
	public boolean isCycle(LinkedList list1) {
		Node head = list1.getHead();
		if (head == null || head.next == null) {
			return false;
		}
		Node singleTraverse = head;
		Node doubleTraverse = head.next.next;
		while (singleTraverse != null && doubleTraverse != null
				&& doubleTraverse.next != null
				&& doubleTraverse.next.next != null) {
			if (singleTraverse == doubleTraverse) {
				return true;
			} else {
				singleTraverse = singleTraverse.next;
				doubleTraverse = doubleTraverse.next.next;
			}
		}

		return false;
	}

}