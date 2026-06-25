class Solution {
    public Node copyRandomList(Node head) {

        if (head == null)
            return null;

        HashMap<Node, Node> map = new HashMap<>();

        Node curr = head;

        // Pass 1: Create copy nodes
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;

        // Pass 2: Connect next and random
        while (curr != null) {

            Node copy = map.get(curr);

            copy.next = map.get(curr.next);

            copy.random = map.get(curr.random);

            curr = curr.next;
        }

        return map.get(head);
    }
}