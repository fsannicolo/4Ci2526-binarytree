public class App {
    public static void main(String[] args) throws Exception {
        
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');
        Node i = new Node('I');

        BinaryTree tree = new BinaryTree(a);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        e.setLeft(g);
        e.setRight(h);
        c.setRight(f);
        f.setLeft(i); 

        System.out.println("Preorder");
        tree.preorder();
        System.out.println(""); 
        System.out.println("Inorder");
        tree.inorder();
        System.out.println(""); 
        System.out.println("Postorder");
        tree.postorder();
        System.out.println(""); 

        System.out.print("Numero di nodi nell'albero: ");
        int count = tree.nodeCounter(a);
        System.out.println(count);

        System.out.print("Numero di foglie nell'albero: ");
        int leaves = tree.leavesCounter(a);
        System.out.println(leaves);

        System.out.print("Cerco la lettera G: ");
        boolean found = tree.searchNode(a, 'G');
        System.out.println(found);

        System.out.print("Profondità: ");
        int depth = tree.depth(a);
        System.out.println(depth);
    }

}
