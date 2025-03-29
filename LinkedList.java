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

    public Node midNode(Node temp)
    {
        Node slow,fast;
        slow = fast = temp;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome()
    {
        // Make a copy of linked list
        Node temporary = new Node(head.data);
        Node ll = head.next;
        Node temp = temporary;
        while(ll!=null)
        {
            Node tem = new Node(ll.data);
            temporary.next = tem;
            temporary = temporary.next;
            ll = ll.next;
        }
        temporary.next = null;
        if(temp==null || temp.next==null)
        {
            return true;
        }
        // Step-1: Find the middle node
        Node mid = midNode(temp);
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

    public Node getMid(Node head)
    {
        Node slow,fast;
        slow = head;
        fast = head.next;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node mergesort(Node head)
    {
        if(head==null || head.next==null)
        {
            return head;
        }
        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;
        Node leftHead = mergesort(head);
        Node rightHead = mergesort(right);
        return sort(leftHead,rightHead);
    }

    public Node sort(Node leftHead,Node rightHead)
    {
        Node temp = new Node(-1);
        Node sortedLL = temp;
        while(leftHead!=null && rightHead!=null)
        {
            if(leftHead.data<=rightHead.data)
            {
                temp.next = leftHead;
                leftHead = leftHead.next;
                temp = temp.next;
            }
            else
            {
                temp.next = rightHead;
                rightHead = rightHead.next;
                temp = temp.next;
            }
        }
        while(leftHead!=null)
        {
            temp.next = leftHead;
            leftHead = leftHead.next;
            temp = temp.next;            
        }
        while(rightHead!=null)
        {
            temp.next = rightHead;
            rightHead = rightHead.next;
            temp = temp.next;            
        }
        return sortedLL.next;
    }

    public void zigzag()
    {
        // find mid
        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;

        // reverse 2nd half
        Node next,prev,curr;
        curr = right;
        prev = null;
        while(curr!=null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // alternative merge - zig-zag
        right = prev;
        Node left = head,nextLeft,nextRight;
        while(left!=null&&right!=null)
        {
            nextLeft = left.next;
            left.next = right;
            nextRight = right.next;
            right.next = nextLeft;

            left = nextLeft;
            right = nextRight;
        }
    }

    public static void main(String[] args)
    {
        LinkedList ls = new LinkedList();
        ls.addFirst(1);
        ls.addLast(2);
        ls.addLast(3);
        ls.addLast(4);
        ls.addLast(5);
        ls.addLast(6);
        ls.addLast(7);
        ls.addLast(8);
        ls.print();
        ls.zigzag();
        ls.print();
    }
}