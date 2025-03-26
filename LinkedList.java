public class LinkedList
{
    public static class Node
    {
        int data;
        Node next;
        public Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data)
    {
        //Step-1: create new node
        Node newNode = new Node(data);
        size++;

        if(head==null)
        {
            head = tail = newNode;
            return;
        }

        //Step-2: Assign newNode's next as head of Linked List
        newNode.next = head;

        //Step-3: Assign newNode as head
        head = newNode;
    }

    public void addLast(int data)
    {
        // Step-1: create a newNode
        Node newNode = new Node(data);
        size++;

        if(tail==null)
        {
            head = tail = newNode;
            return;
        }

        // Step-2: Assign tail's next as newNode
        tail.next = newNode;

        // Step-3: Assign newNode as tail
        tail = newNode;

    }

    public void print()
    {

        // O(n)

        Node temp = head;
        while(temp!=null)
        {
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void add(int data, int idx)
    {
        if(idx==0)
        {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while(i<idx-1)
        {
            temp = temp.next;
            i++;
        }

        // i = idx-1; temp->prev

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst()
    {
        if(size==0)
        {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size==1)
        {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast()
    {
        if(size==0)
        {
            System.out.println("Linked List is empty");
            return Integer.MIN_VALUE;
        }else if(size==1)
        {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        // prev : i = size-2

        Node prev = head;
        int i = 0;
        while(i<size-2)
        {
            prev = prev.next;
            i++;
        }
        int val = prev.next.data; // tail.data
        prev.next = null;
        tail = null;
        size--;
        return val;
    }

    public static void main(String[] args)
    {
        LinkedList ll = new LinkedList();
        ll.addFirst(1);
        ll.print();
        ll.removeLast();
        ll.print();
        System.out.println(ll.size);
    }
}