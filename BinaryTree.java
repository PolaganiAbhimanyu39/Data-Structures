import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree
{
    static class Node
    {
        int data;
        Node left = null;
        Node right = null;

        public Node(int data)
        {
            this.data = data;
        }
    }

    static class Info
    {
        Node node;
        int hd;

        public Info(Node node,int hd)
        {
            this.node = node;
            this.hd = hd;
        }
    }

    static class BinaryTrees
    {
        static int idx = -1;
        public static Node buildTree(int[] nodes)
        {
            idx++;
            if(nodes[idx]==-1)
            {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public static void preorder(Node root)
        {
            if(root==null)
            {
                System.out.print("-1 ");
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void levelorder(Node root)
        {
            if(root==null)
            {
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty())
            {
                Node curr = q.remove();
                if(curr==null)
                {
                    System.out.println();
                    if(q.isEmpty())
                    {
                        break;
                    }
                    q.add(null);
                }
                else
                {
                    System.out.print(curr.data+" ");
                    if(curr.left!=null)
                    {
                        q.add(curr.left);
                    }
                    if(curr.right!=null)
                    {
                        q.add(curr.right);
                    }
                }
            }
        }

        public static int height(Node root)
        {
            if(root==null)
            {
                return 0;
            }
            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(lh,rh)+1;
        }

        public static void inorder(Node root)
        {
            if(root==null)
            {
                return;
            }
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }

        public static void postorder(Node root)
        {
            if(root==null)
            {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }

        public static int count(Node root)
        {
            if(root==null)
            {
                return 0;
            }
            int lc = count(root.left);
            int rc = count(root.right);
            return lc+rc+1;
        }

        public static int sum(Node root)
        {
            if(root==null)
            {
                return 0;
            }
            int lc = sum(root.left);
            int rc = sum(root.right);
            return lc+rc+root.data;
        }

        public static int diameter(Node root)
        {
            if(root==null)
            {
                return 0;
            }

            int ld = diameter(root.left);
            int rd = diameter(root.right);
            int lh = height(root.left);
            int rh = height(root.right);

            int self = lh+rh+1;

            return Math.max(ld,Math.max(rd,self));
        }

        public static boolean isSubtree(Node root,Node subroot)
        {
            if(root == null)
            {
                return false;
            }

            if(root.data==subroot.data)
            {
                if(isIdentical(root,subroot))
                {
                    return true;
                }
            }

            return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
        }

        public static void topView(Node root)
        {
            Queue<Info> q = new LinkedList<>();
            HashMap<Integer,Node> map = new HashMap<>();
            if(root==null) return;
            q.add(new Info(root, 0));
            int min = 0,max = 0;
            while(!q.isEmpty())
            {
                Info curr = q.remove();
                if(!map.containsKey(curr.hd))
                {
                    map.put(curr.hd,curr.node);
                }
                if(curr.node.left!=null)
                {
                    q.add(new Info(curr.node.left, curr.hd-1));
                    min = Math.min(min,curr.hd-1);
                }
                if(curr.node.right!=null)
                {
                    q.add(new Info(curr.node.right, curr.hd+1));
                    max = Math.max(max, curr.hd+1);
                }
            }

            for(int i = min;i<=max;i++)
            {
                System.out.print(map.get(i).data+" ");
            }
            System.out.println();
        }

        public static boolean isIdentical(Node node, Node subroot)
        {
            if(node==null && subroot==null)
            {
                return true;
            }
            else if(node==null || subroot==null || node.data!=subroot.data)
            {
                return false;
            }

            if(!isIdentical(node.left, subroot.left))
            {
                return false;
            }
            if(!isIdentical(node.right, subroot.right))
            {
                return false;
            }

            return true;
        }
    }

    public static void main(String[] args)
    {
        BinaryTrees tree = new BinaryTrees();
        // int[]  nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        int[] nodes = {1,2,-1,4,-1,5,-1,6,-1,-1,3,-1,-1};
        Node root = tree.buildTree(nodes);
        tree.topView(root);

            
        // Node root = new Node(1);
        // root.left = new Node(2);
        // root.right = new Node(3);
        // root.left.left = new Node(4);
        // root.left.right = new Node(5);
        // root.right.left = new Node(6);
        // root.right.right = new Node(7);


        // Node subRoot = new Node(2);
        // subRoot.left = new Node(4);
        // // subRoot.right = new Node(5);

        // System.out.println(tree.isSubtree(root,subRoot));
    }
}