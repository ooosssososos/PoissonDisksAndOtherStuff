public class QuadTree {

    QNode root;

    public QNode addNode(int x, int y){
        if(root == null){
            root = new QNode(x,y);
            return root;
        }
        else return root.add(x,y);
    }

    public QNode getClosestNode(int x, int y){
        return root.findClosestNode(Double.MAX_VALUE, null, x, y);
    }

    public double getClosest(int x, int y){
        return root.findClosest(Double.MAX_VALUE, x, y);
    }

    public int size(){
        if(root == null) return 0;
        return root.size();
    }

}
