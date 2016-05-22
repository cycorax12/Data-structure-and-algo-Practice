package work.practice.dsa.linkedlist;

/**
 * Single Linked List
 * 
 * @author Virendra
 * 
 */
public class LinkedListApp {

	private static int choice = 2;

	// choice 1 = for cycle/loop in linkedlist
	// choice 0 = simple linkedlist insert and display

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
		System.out.println();
		switch (choice) {
		case 0:
			list1.printList(list1.getHead());
			break;
		case 1:
			node5.next = node2;
			System.out.println("Does list1 has cycle? :  "
					+ (new LinkedListUtil().isCycle(list1) ? "Yes" : "No"));

			break;
		case 2:
			LinkedList list2 = new LinkedList();
			Node nodel1 = new Node(1);
			Node nodel2 = new Node(11);
			list2.insert(nodel1);
			list2.insert(nodel2);
//			nodel2.next = node2; -- merge point, uncomment to add merge point
			list1.printList(list1.getHead());
			System.out.println();
			list1.printList(list2.getHead());
			Node mergePoint = new LinkedListUtil().getMergePoint(list1, list2);
			System.out.println(mergePoint == null ? "No merge point for lists": "Merge point for two list is" + mergePoint.toString());
			break;
		default:
			System.out.println("Invalid Choice. Should be between (0-2)");
			break;
		}

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

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

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

	/**
	 * This method return common merge/common/join point for two linked list.
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public Node getMergePoint(LinkedList list1, LinkedList list2) {
		Node temp1 = list1.getHead();
		Node temp2 = list2.getHead();
		if(temp1 == null || temp2 == null){
			return null;
		}
		
		while(temp1 != null){
			temp2 = list2.getHead();
			while(temp2 != null){
				if(temp1 == temp2){
					return temp2;
				}else{
					temp2 = temp2.next;
				}
			}
			temp1 = temp1.next;
		}
		
		
		
		
		return null;
	}

}