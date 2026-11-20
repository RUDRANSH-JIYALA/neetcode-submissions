class LFUCache {

    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast() {
            if (size == 0) return null;

            Node node = tail.prev;
            remove(node);

            return node;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    private int capacity;
    private int minFreq;

    private HashMap<Integer, Node> keyToNode;
    private HashMap<Integer, DoublyLinkedList> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
    }

    private void updateFrequency(Node node) {

        int oldFreq = node.freq;

        DoublyLinkedList oldList = freqToList.get(oldFreq);
        oldList.remove(node);

        if (oldFreq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList newList = freqToList.computeIfAbsent(
            node.freq, k -> new DoublyLinkedList()
        );

        newList.addFirst(node);
    }

    public int get(int key) {

        if (!keyToNode.containsKey(key)) {
            return -1;
        }

        Node node = keyToNode.get(key);

        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {

            Node node = keyToNode.get(key);

            node.value = value;

            updateFrequency(node);

            return;
        }

        if (keyToNode.size() == capacity) {

            DoublyLinkedList list = freqToList.get(minFreq);

            Node lruNode = list.removeLast();

            keyToNode.remove(lruNode.key);
        }

        Node newNode = new Node(key, value);

        DoublyLinkedList list = freqToList.computeIfAbsent(
            1, k -> new DoublyLinkedList()
        );

        list.addFirst(newNode);

        keyToNode.put(key, newNode);

        minFreq = 1;
    }
}
