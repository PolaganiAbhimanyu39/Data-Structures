public class CircularQueue
{
    static class CQueue
    {
        static int[] arr;
        static int size;
        static int rear;
        static int front;

        public CQueue(int n)
        {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty()
        {
            return rear==-1 && front==-1;
        }

        public static boolean isFull()
        {
            return (rear+1)%size==front;
        }

        // add
        public static void add(int data)
        {
            if(isFull())
            {
                System.out.println("Circular Queue is empty");
                return;
            }

            // add 1st element
            if(front==-1)
            {
                front = 0;
            }

            rear = (rear+1)%size;
            arr[rear] = data;
        }

        public static int remove()
        {
            if(isEmpty())
            {
                System.out.println("Circular Queue is empty");
                return -1;
            }

            int val = arr[front];

            // last element deleted
            if(rear==front)
            {
                rear = front = -1;
            }
            else
            {
                front = (front+1)%size;
            }

            return val;
        }
        
        public static int peek()
        {
            if(isEmpty())
            {
                System.out.println("Circular Queue is empty");
                return -1;
            }
            return arr[front];
        }

    }

    public static void main(String[] args) 
    {
        CQueue q = new CQueue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        while(!q.isEmpty())
        {
            System.out.println(q.remove());
        }
    }    
}
