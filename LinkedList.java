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

    public static void print()
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

    public int itrSearch(int key)
    {
        Node temp = head;
        int idx = 0;
        while(temp!=null)
        {
            if(temp.data==key) // key found
            {
                return idx;
            }
            temp = temp.next;
            idx++;
        }

        // key not found
        return -1;
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
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public Node midNode()
    {
        Node slow,fast;
        slow = head;fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome()
    {
        if(head==null || head.next==null)
        {
            return true;
        }
        // Step-1: Find the middle node
        Node mid = midNode();
        Node curr,next,prev;
        curr = mid;
        prev = null;
        // Step-2: Reverse nodes from middle node to last node
        while(curr!=null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // Step-3: Initialize left and right as their respective ends
        Node left,right;
        left = head;right = prev;
        while(left!=null && right!=null)
        {
            if(left.data!=right.data) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static boolean isCycle()
    {
        Node slow,fast;
        slow = head;fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
            {
                return true;// Cycle exists
            }
        }
        return false;// Cycle does not exist
    }

    // This code doesn't work only when there's a full cycle
    public static void removeCycle()
    {
        // detect cycle
        Node slow,fast;
        boolean cycle = false;
        slow = fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
            {
                cycle = true;
                break;
            }
        }
        if(cycle==false)
        {
            return;
        }
        // find meeting point
        Node prev = null;
        slow = head;
        while(slow!=fast)
        {
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }
        // remove cycle -> last.next = null
        prev.next = null;
    }

    public static void main(String[] args)
    {
        head = new Node(1);
        head.next = new Node(2);
        Node temp = new Node(3);
        head.next.next = temp;
        head.next.next.next = new Node(4);
        head.next.next.next.next = temp;
        System.out.println(isCycle());
        removeCycle();
        System.out.println(isCycle());
    }
}