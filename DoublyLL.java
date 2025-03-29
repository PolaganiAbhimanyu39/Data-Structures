public class DoublyLL
{
    public static class Node
    {
        int data;
        Node next;
        Node prev;

        public Node(int data)
        {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data)
    {
        Node newNode = new Node(data);
        if(head==null)
        {
            head = tail = newNode;
            size++;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
    }

    public int removeFirst()
    {
        if(head==null)
        {
            System.out.println("Doubly Linked List is empty");
            return Integer.MIN_VALUE;
        }

        int val = head.data;

        if(size==1)
        {
            tail = head = head.next;
            size--;
            return val;
        }

        head = head.next;
        head.prev = null;
        size--;
        return val;

    }

    public int removeLast()
    {
        if(head==null)
        {
            System.out.println("Doubly Linked List is empty");
            return Integer.MIN_VALUE;
        }

        int val = tail.data;

        if(size==1)
        {
            tail = head = head.next;
            size--;
            return val;
        }

        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return val;
    }

    public void addLast(int data)
    {
        Node newNode = new Node(data);
        if(head==null)
        {
            head = tail = newNode;
            size++;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void reverse()
    {
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while(curr!=null)
        {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void print()
    {
        Node temp = head;
        while(temp!=null)
        {
            System.out.print(temp.data+"<->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void printReverse()
    {
        Node temp = tail;
        while(temp!=null)
        {
            System.out.print(temp.data+"<->");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    public static void main(String[] args)
    {
        DoublyLL dll = new DoublyLL();
        dll.addFirst(7);
        dll.addFirst(12);
        dll.addFirst(14);
        dll.addFirst(88);
        // dll.print();
        System.out.println(dll.removeFirst());
        // System.out.println(dll.removeLast());
        dll.print();
        dll.reverse();
        dll.print();
        System.out.println(size);
    }
}