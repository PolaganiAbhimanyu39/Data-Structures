public class Stack
{

    static Node head = null;

    static class Node
    {
        int data;
        Node next = null;

        public Node(int data)
        {
            this.data = data;
        }
    }

    static class StackB
    {

        public boolean isEmpty()
        {
            return head==null;
        }

        // push
        public void push(int data)
        {
            Node newNode = new Node(data);
            if(isEmpty())
            {
                head = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }

        // pop
        public int pop()
        {
            if(isEmpty())
            {
                return -1;
            }
            int val = head.data;
            head = head.next;
            return val;
        }

        //peek
        public int peek()
        {
            if(isEmpty())
            {
                return -1;
            }
            return head.data;
        }

        public static void main(String[] args)
        {
            StackB s = new StackB();
            s.push(1);
            s.push(2);
            s.push(3);
            while(!s.isEmpty())
            {
                System.out.println(s.pop());
            }
        }

    }
}
