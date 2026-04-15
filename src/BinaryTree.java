import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    
    private Node root;

    public BinaryTree() {

        this.root = null;
    }

    public BinaryTree(Node n) {

        this.root = n;
    }

    // depth-first
    public void preorder() {
        preorder(root);
    }

    /**
     * I nodi genitori sono visitati prima dei nodi figli
     * @param n nodo di partenza
     */
    private void preorder(Node n) {

        // exit clause
        if (n == null) return;

        System.out.print(n.getData()); 
        preorder(n.getLeft());
        preorder(n.getRight()); 
    }

    public void inorder() {
        inorder(root);
    }

    // in-order
    private void inorder(Node n) {

        // exit clause
        if (n == null) return;

        inorder(n.getLeft());
        System.out.print(n.getData()); 
        inorder(n.getRight()); 
    }

    public void postorder() {
        postorder(root);
    }

    // post-order
    private void postorder(Node n) {

        // exit clause
        if (n == null) return;

        postorder(n.getLeft());
        postorder(n.getRight()); 
        System.out.print(n.getData()); 
    }

    public void breadth() {
        breadth(root); 
    }

    private void breadth(Node root) {
        if (root == null) return;

        // bisogna raccogliere tutti i nodi sullo stesso livello
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // toglie il primo nodo dalla coda
            Node cursor = queue.poll();
            System.out.print(cursor.getData());

            // aggiunge i figli del nodo corrente in coda
            if (cursor.getLeft() != null) {
                queue.add(cursor.getLeft());
            }
            if (cursor.getRight() != null) {
                queue.add(cursor.getRight());
            }
        }
    }

    /**
     * Conta quanti nodi sono presenti nel sotto-albero in input
     * @param root nodo iniziale del sotto-albero
     * @return numero di nodi contenuti
     */
    public int nodeCounter(Node root) {
        
        // exit clause + caso base
        if (root == null) return 0; 

        // chiamata ricorsiva
        return nodeCounter(root.getLeft()) + nodeCounter(root.getRight()) + 1;
    }

    /**
     * Conta quanti nodi foglia sono contenuti nel sotto-albero
     * @param root nodo iniziale del sotto-albero
     * @return numero di nodi foglia presenti
     */
    public int leavesCounter(Node root) {

        // exit clause
        if (root == null) return 0;
        
        // se è foglia, la conto
        if (root.getLeft() == null && root.getRight() == null) return 1;

        // chiamata ricorsiva
        return leavesCounter(root.getLeft()) + leavesCounter(root.getRight()); 
    }

    /**
     * Conta quanti nodi genitore sono contenuti nel sotto-albero
     * @param root nodo iniziale del sotto-albero
     * @return numero di nodi genitore presenti
     */
    public int parentCounter(Node root) {

        // exit clause
        if (root == null) return 0;
        
        // se è foglia, la salto
        if (root.getLeft() == null && root.getRight() == null) return 0;

        // chiamata ricorsiva
        return parentCounter(root.getLeft()) + parentCounter(root.getRight()) + 1; 
    }

    /**
     * Cerca se una lettera specifica è presente nell'albero
     * @param root nodo iniziale del sotto-albero
     * @param letter lettera da cercare
     * @return esito della ricerca
     */
    public boolean searchNode(Node root, char letter) {

        // exit clause
        if (root == null) return false;
        if (root.getData() == letter) return true;

        // chiamata ricorsiva
        return searchNode(root.getLeft(), letter) || searchNode(root.getRight(), letter);
    }

    /**
     * Calcola la profondità dell'albero (il percorso più lungo)
     * @param root nodo di partenza dell'albero
     * @return la lunghezza del percorso più lungo
     */
    public int depth(Node root) {

        // exit clause
        if (root == null) return 0;
        if (root.getLeft() == null && root.getRight() == null) return 0;    // foglia

        // chiamata ricorsiva
        int leftDepth = depth(root.getLeft());
        int rightDepth = depth(root.getRight());
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Calcola il livello del nodo cercato nell'albero
     * @param n nodo da cercare
     * @return livello del nodo nell'albero
     */
    public int getLevel(Node n) {
        return getLevel(root, n, 1);
    }

    private int getLevel(Node current, Node target, int level) {

        // exit clause
        if (target == null || current == null) return 0;
        if (target == current) return level; 
        
        // chiamata ricorsiva, un ramo alla volta
        int leftLevel = getLevel(current.getLeft(), target, level + 1);
        if (leftLevel > 0) return leftLevel;

        int rightLevel = getLevel(current.getRight(), target, level + 1);
        return rightLevel;
    }

    /**
     * Trova il genitore del nodo cercato
     * @param n nodo da cercare
     * @return nodo genitore, se presente
     */
    public Node getAncestor(Node n) {
        return getAncestor(root, n);
    }

    private Node getAncestor(Node current, Node n) {

        // exit clause
        if (current == null || n == null) return null;
        if (n == current) return null;

        // ho trovato il figlio
        if (current.getLeft() == n || current.getRight() == n) return current;

        // chiamata ricorsiva
        Node left = getAncestor(current.getLeft(), n);
        if (left != null) return left;

        Node right = getAncestor(current.getRight(), n);
        return right;
    }

    /**
     * Produce la lista dei nodi che formano il percorso (unidirezionale) tra due nodi
     * @param start nodo iniziale
     * @param end nodo finale
     * @return lista dei nodi del percorso
     */
    public List<Node> getPathList(Node start, Node end) {

        // istanzio la lista dei nodi
        ArrayList<Node> list = new ArrayList<>();
        if (start == null || end == null) return list;
        
        boolean reverse = false;
        int startLevel = getLevel(start);
        int endLevel = getLevel(end);

        // casi base
        if (startLevel == 0 || endLevel == 0) return list;
        if (start != end && startLevel == endLevel) return list;

        if (startLevel < endLevel)
            getPathList(start, end, list); 
        else {
            getPathList(end, start, list);
            reverse = true;
        }

        // controllo se la ricerca era in salita o discesa
        return reverse ? list : list.reversed();
    }

    private void getPathList(Node start, Node end, List<Node> list) {

        // exit clause
        if (start == end) {
            list.add(start);
            return;
        }

        // non ho incontrato il nodo end risalendo
        if (end == root) {
            list.clear();
            return;
        }

        // chiamata ricorsiva
        list.add(end);
        getPathList(start, getAncestor(end), list);
    }
}
