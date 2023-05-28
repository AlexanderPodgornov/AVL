public class Main {
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        avl.add(1000);
        avl.add(923);
        avl.add(146);
        avl.add(1212);
        avl.add(145);
        avl.add(233);
        avl.add(144);
        avl.add(143);
       /* avl.add(960);
        avl.add(500);
        avl.add(956);
        avl.add(560);
        avl.add(928);
        avl.add(535);
        avl.add(3);
        avl.add(2);*/
        //avl.AVL();
        avl.print();
        System.out.println(avl.findMax());
        System.out.println(avl.findMin());
        avl.delete(99999);
        avl.print();
    }
}