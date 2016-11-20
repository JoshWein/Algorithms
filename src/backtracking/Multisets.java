package backtracking;

/**
 * Created by Josh Wein on 11/20/2016.
 * Problem Statement: Multisets are allowed to have repeated elements. A multiset of n items may thus have fewer than n! distinct permutations. For example, {1,1,2,2} has only six different permutations: {1,1,2,2}, {1,2,1,2}, {1,2,2,1}, {2,1,1,2}, {2,1,2,1}, and {2,2,1,1}. multiset Design and implement an efficient algorithm for constructing all permutations of a multiset.
 */
public class Multisets {

    /**
     * Java Implementation of Aaron William's "LOOPLESS GENERATION OF MULTISET PERMUTATIONS BY PREFIX SHIFTS" - http://webhome.csc.uvic.ca/~haron/CoolMulti.pdf
     * This implementation generates the permutation starting in increasing order instead of decreasing order
     * @param multiSet
     */
    public static void multiSetPermutations(Integer[] multiSet) {
        Node[] nodes = setUpLinks(multiSet);
        // First node
        Node head = nodes[0];
        // Second to last node
        Node i = nodes[nodes.length - 2];
        // Last node
        Node j = nodes[nodes.length - 1];
        Node s, t;
        processSolution(head);
        while (j.next != null || j.value > head.value) {
            if (j.next != null && i.value <= j.next.value) {
                s = j;
            } else {
                s = i;
            }
            t = s.next;
            s.next = t.next;
            t.next = head;
            if (t.value > head.value) {
                i = t;
            }
            j = i.next;
            head = t;
            processSolution(head);
        }
    }

    private static Node[] setUpLinks(Integer[] multiSet) {
        Node[] nodes = new Node[multiSet.length];
        for (int i = 0; i < multiSet.length - 1; i++) {
            if (i == 0)
                nodes[i] = new Node(multiSet[i]);
            nodes[i + 1] = new Node(multiSet[i + 1]);
            nodes[i].next = nodes[i + 1];
        }
        return nodes;
    }

    private static void processSolution(Node head) {
        System.out.print("[");
        while(head.next != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println(head.value + "]");
    }

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Integer[] multiSet = {1, 1, 2, 2};
        multiSetPermutations(multiSet);
    }
}
