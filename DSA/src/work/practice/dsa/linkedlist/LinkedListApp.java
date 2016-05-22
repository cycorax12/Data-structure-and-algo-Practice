package work.practice.dsa.linkedlist;

/**
 * Single Linked List
 * 
 * @author Virendra
 * 
 */
public class LinkedListApp {

	private static int choice = 3;

	// choice 1 = for cycle/loop in linkedlist
	// choice 0 = simple linkedlist insert and display
	// choice 2 = merge/join point for two linked list
	// choice 3 = merge two list

	public static void main(String[] args) {
		if (choice != 3) {
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
				// nodel2.next = node2; -- merge point, uncomment to add merge
				// point
				list1.printList(list1.getHead());
				System.out.println();
				list1.printList(list2.getHead());
				Node mergePoint = new LinkedListUtil().getMergePoint(list1,
						list2);
				System.out
						.println(mergePoint == null ? "No merge point for lists"
								: "Merge point for two list is"
										+ mergePoint.toString());
				break;
			default:
				System.out.println("Invalid Choice. Should be between (0-2)");
				break;
			}
		} else {
			LinkedList list1 = new LinkedList();
			LinkedList list2 = new LinkedList();
			Node n1 = new Node(3);
			Node n2 = new Node(7);
			Node n3 = new Node(11);
			Node n4 = new Node(33);
			Node n5 = new Node(4);
			Node n6 = new Node(12);
			Node n7 = new Node(21);
			Node n8 = new Node(29);
			list1.insert(n1);
			list1.insert(n2);
			list1.insert(n3);
			list1.insert(n4);

			list2.insert(n5);
			list2.insert(n6);
			list2.insert(n7);
			list2.insert(n8);

			System.out.println("List1: ");
			list1.printList(list1.getHead());
			System.out.println("\nList2: ");
			list1.printList(list2.getHead());
			System.out.println("\nFinal List:");
			LinkedList list = new LinkedList();
			list.setHead(new LinkedListUtil().mergeTwoList(list1.getHead(),
					list2.getHead()));
			list.printList(list.getHead());

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
		if (getHead() == null) {
			setHead(node);
			return;
		}
		Node temp = getHead();
		setHead(node);
		node.next = temp;

	}

	/**
	 * Inserts new node to end of list.
	 * 
	 * @param node
	 */
	public void insert(Node node) {
		if (getHead() == null) {
			setHead(node);
			return;
		}
		Node temp = getHead();
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
		if (getHead() == null) {
			return;
		}
		if (node == getHead()) {
			setHead(node.next);
		} else {
			Node temp = getHead();
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

	public void setHead(Node head) {
		this.head = head;
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
		if (temp1 == null || temp2 == null) {
			return null;
		}

		while (temp1 != null) {
			temp2 = list2.getHead();
			while (temp2 != null) {
				if (temp1 == temp2) {
					return temp2;
				} else {
					temp2 = temp2.next;
				}
			}
			temp1 = temp1.next;
		}

		return null;
	}

	/**
	 * This method merges two sorted linked list to create one list.
	 * 
	 * @return
	 */
	public Node mergeTwoList(Node list1, Node list2) {

		Node newHead = null;

		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		if (list1.data <= list2.data) {
			newHead = list1;
			newHead.next = mergeTwoList(list1.next, list2);
		} else {
			newHead = list2;
			newHead.next = mergeTwoList(list1, list2.next);

		}

		return newHead;
	}

}