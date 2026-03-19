import java.util.List;

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

        // depth first
        System.out.println("Preorder");
        tree.preorder();
        System.out.println(""); 
        System.out.println("Inorder");
        tree.inorder();
        System.out.println(""); 
        System.out.println("Postorder");
        tree.postorder();
        System.out.println(""); 

        // breadth first
        System.out.println("Breadth first");
        tree.breadth();
        System.out.println(""); 

        int count = tree.nodeCounter(a);
        System.out.print("Numero di nodi nell'albero: ");
        System.out.println(count);

        int leaves = tree.leavesCounter(a);
        System.out.print("Numero di foglie nell'albero: ");
        System.out.println(leaves);

        boolean found = tree.searchNode(a, 'G');
        System.out.print("Cerco la lettera G: ");
        System.out.println(found);

        int depth = tree.depth(a);
        System.out.print("Profondità: ");
        System.out.println(depth);

        int level = tree.getLevel(d);
        System.out.print("Livello di D: ");
        System.out.println(level);

        System.out.print("Genitore di G: ");
        Node parent = tree.getAncestor(g);
        System.out.println(parent);

        System.out.println("Percorso tra A e F: ");
        System.out.println("discesa: " + tree.getPathList(a, f));
        System.out.println("salita: " + tree.getPathList(f, a));
    }

}
