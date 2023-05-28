import java.util.Stack;
public class  AVLTree implements AVLTR {
    Node root;
    public AVLTree() {
        root = null;
    }
    private int getHeight(Node node) {
        if (node == null)
            return -1;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    private int getBalance(Node node)
    {
        if (node == null)
            return 0;
        return getHeight(node.right)-getHeight(node.left);
    }
    private Node rightRotate(Node node)
    {
        Node x = node.left;// node.left = x;
        Node z = x.right;
        x.right = node;
        node.left = z;
        return x;
    }

    private Node leftRotate(Node node)
    {
        Node x = node.right;
        Node z = x.left;
        x.left = node;
        node.right = z;
        return x;
    }

    private Node changeBalance(Node node)
    {
        int balance = getBalance(node);
        if (balance > 1)
        {
            if (getHeight(node.right.right) > getHeight(node.right.left))
                node = leftRotate(node);
            else {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }
        else if (balance < -1)
        {
            if (getHeight(node.left.left) > getHeight(node.left.right))
                node = rightRotate(node);
            else
            {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        }
        return node;
    }

    private Node addnode(Node node, int value)
    {
        Node n = new Node();
        n.data = value;
        if (node == null)
            return n;
        else if (value < node.data)
            node.left = addnode(node.left, value);
        else if (value > node.data)
            node.right = addnode(node.right, value);
        else
            throw new NullPointerException("НЕЛЬЗЯ ДОБАВЛЯТЬ СУЩЕСТВУЮЩИЙ ЭЛЕМЕНТ");
        return changeBalance(node);
    }
    @Override
    public void add(int value)
    {
        root = addnode(root, value);
    }

    @Override
    public boolean search(int value) {
        if (root == null)
            throw new NullPointerException("БИНАРНОЕ ДЕРЕВО ПУСТО");
        Node temp = root;
        while (temp != null)
        {
            if (temp.data == value)
                return true;
            if (temp.data < value)
                temp = temp.right;
            else temp = temp.left;
        }
        return false;
    }


    @Override
    public void delete(int value)
    {
        root = delnode(root, value);
    }
    private Node delnode(Node node, int value) {
        if (node == null)
            return node;
        else if (value < node.data)
            node.left = delnode(node.left, value);
        else if (value > node.data)
            node.right = delnode(node.right, value);
        else {
            if (node.left == null || node.right == null) {
                if (node.left == null)
                    node = node.right;
                else
                    node = node.left;
            } else {
                Node temp = node.right;
                while (temp.left != null)
                {
                    temp = temp.left;
                }
                node.data = temp.data;
                node.right = delnode(node.right, node.data);
            }
        }
        if (node != null)
            node = changeBalance(node);
        return node;
    }

    @Override
    public int findMin() {
        if (root == null)
            throw new NullPointerException("БИНАРНОЕ ДЕРЕВО ПУСТО");
        Node temp = root;
        Node parent = root;
        while (temp != null)
        {
            parent = temp;
            temp = temp.left;
        }
        return parent.data;
    }

    @Override
    public int findMax() {
        if (root == null)
            throw new NullPointerException("БИНАРНОЕ ДЕРЕВО ПУСТО");
        Node temp = root;
        Node parent = root;
        while (temp != null)
        {
            parent = temp;
            temp = temp.right;
        }
        return parent.data;
    }
    @Override
    public Node Root()
    {
        return root;
    }
    @Override
    public void obhodP(Node root)
    {
        if(root != null)
        {
            System.out.print(root.data + " ");
            obhodP(root.left);
            obhodP(root.right);
        }
    }
    @Override
    public void obhodS(Node root)
    {
        if(root != null)
        {
            obhodS(root.left);
            System.out.print(root.data + "(" + getHeight(root)+") ");
            obhodS(root.right);
        }
    }
    @Override
    public void obhodO(Node root)
    {
        if(root != null)
        {
            obhodO(root.left);
            obhodO(root.right);
            System.out.print(root.data + " ");
        }
    }
    @Override
    public void print() {
        Stack<Node> globalStack = new Stack<>();
        globalStack.push(root);
        int space = (int) Math.pow(2, getHeight(root)+1);
        boolean isRowEmpty = false;
        System.out.println();
        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();
            isRowEmpty = true;
            for (int i = 0; i < space; i++) {
                System.out.print(' ');
            }
            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data+"("+getHeight(temp)+")("+getBalance(temp)+")");
                   // System.out.print(temp.data+" ");
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < space * 2 - 2; i++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            space /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        System.out.println();
    }
}