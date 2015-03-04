public class QNode {

    int x, y;
    QNode NE; //1
    QNode SE; //2
    QNode SW; //3
    QNode NW; //4

    public QNode(int a, int b) {
        x = a;
        y = b;
    }

    public int getQuadrant(int a, int b) {
        if (a == x && b == y) return -1;
        if (a > x) {
            if (b > y) {
                return 2;
            } else {
                return 1;
            }
        } else {
            if (b > y) {
                return 3;
            } else {
                return 4;
            }
        }
    }

    public QNode add(int a, int b) {
        int q = getQuadrant(a, b);
        switch (q) {
            case 2:
                if (SE == null){

                    SE = new QNode(a, b);
                    return SE;

                }

                else return SE.add(a, b);
            case 1:
                if (NE == null){
                    NE = new QNode(a, b);
                    return NE;
                }
                else return NE.add(a, b);
            case 3:

                if (SW == null)          {
                    SW = new QNode(a, b);
                    return SW;
                }
                else return SW.add(a, b);
            case 4:
                if (NW == null)   {
                    NW = new QNode(a, b);
                    return NW;

                }
                else return NW.add(a, b);
        }
        return null;
    }

    public QNode findClosestNode(double dist, QNode closest, int a, int b) {
        double tmpdst = dist(a, b);
        if (tmpdst < dist){
            dist = tmpdst;
            closest = this;
        }
        int q = getQuadrant(a, b);
        switch (q) {
            case 2:
                if (SE == null)
                    return this;
                else return SE.findClosestNode(dist,closest,a,b);
            case 1:
                if (NE == null)
                    return this;
                else return NE.findClosestNode(dist,closest,a,b);
            case 3:

                if (SW == null)
                    return this;
                else return SW.findClosestNode(dist,closest,a,b);
            case 4:
                if (NW == null)
                    return this;
                else return NW.findClosestNode(dist,closest,a,b);
        }
        return null;
    }
    public double findClosest(double dist, int a, int b) {
        double tmpdst = dist(a, b);
        if (tmpdst < dist){
            dist = tmpdst;
        }
        int q = getQuadrant(a, b);
        switch (q) {
            case 2:
                if (SE == null)
                    return dist;
                else return SE.findClosest(dist,a,b);
            case 1:
                if (NE == null)
                    return dist;
                else return NE.findClosest(dist,a,b);
            case 3:

                if (SW == null)
                    return dist;
                else return SW.findClosest(dist,a,b);
            case 4:
                if (NW == null)
                    return dist;
                else return NW.findClosest(dist,a,b);
        }
        return -1;
    }

    public int size(){
        int s = 1;
        if(NE != null) s += NE.size();
        if(NW != null) s += NW.size();
        if(SE != null) s += SE.size();
        if(SW != null) s += SW.size();
        return s;
    }

    public double dist(int a, int b) {
        return Math.sqrt(Math.pow(a - x, 2) + Math.pow(b - y, 2));
    }

}
