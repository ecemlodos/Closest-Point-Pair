
import java.awt.*;
import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /**
     * Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points) {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /**
     * Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair() {
        return this.closestPointPair;
    }

    /**
     * Main method for calculate and return closest point pair
     *
     * @param p          --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex  --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex) {
        PointPair pointPair = new PointPair(p[startIndex], p[lastIndex]);

        int n=lastIndex-startIndex+1;		//size of subarray
        if(n<=3) return getClosestPointPair(pointPair, pointPair);

        int mid=startIndex+(lastIndex-startIndex)/2;
        Point2D.Double midPoint=p[mid];
        PointPair dL = calculateClosestPointPair(p, startIndex, mid);
        PointPair dR = calculateClosestPointPair(p, mid+1, lastIndex);
        PointPair d = getClosestPointPair(dL, dR);

        /*Build an array strip[] that contains points close to the line passing throught the middle point*/
        Point2D.Double[] strip=new Point2D.Double[n];
        int j=0;
        for(int i=startIndex; i<=lastIndex; i++)
            if(Math.abs(p[i].x - midPoint.x) < d.getDistance()){
                strip[j++]=p[i];
            }
        return getClosestPointPair(d, stripClosest(strip, j, d));
    }

    /**
     * calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair pair1 = new PointPair(p1, p2);
        PointPair pair2 = new PointPair(p1, p3);
        PointPair pair3 = new PointPair(p2, p3);

        double pair1Dist = pair1.getDistance();
        double pair2Dist = pair2.getDistance();
        double pair3Dist = pair3.getDistance();

        this.closestPointPair = pair1;
        if (pair2Dist < pair1Dist) {
            this.closestPointPair = pair2;
        } else if (pair3Dist < pair1Dist) {
            this.closestPointPair = pair3;
        }
        return this.closestPointPair;
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2) {
        return p1.getDistance() < p2.getDistance() ? p1 : p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip        --> point array
     * @param size         --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double[] strip, int size, PointPair shortestLine) {
        PointPair min = shortestLine;  // Initialize the minimum distance as d

        // Pick all points one by one and try the next points till the difference
        // between y coordinates is smaller than d.
        // This is a proven fact that this loop runs at most 6 times
        for (int i = 0; i < size; i++){
            for (int j = i+1; j < size && (strip[j].y - strip[i].y) < min.getDistance(); j++) {
                PointPair pointPair = new PointPair(strip[i], strip[j]);
                if (pointPair.getDistance() < min.getDistance())
                    min = pointPair;
            }
        }
        return min;
    }
}