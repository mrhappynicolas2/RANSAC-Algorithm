
//Name: Nicolas Berube
//Student ID: 300239551

public class PlaneRANSAC {
    private double eps;
    private PointCloud pc;
    private double confidence;
    private double percentageOfPointsOnPlane;
    public PlaneRANSAC(PointCloud pc) {
        this.eps = 0.1;
        this.pc = pc;
        this.confidence=confidence;
        this.percentageOfPointsOnPlane=percentageOfPointsOnPlane;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getEps() {
        return this.eps;
    }

    //a method that returns the estimated number of iterations required to obatain a good estimate of the plane
    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane) {
        this.confidence = confidence;
        this.percentageOfPointsOnPlane = percentageOfPointsOnPlane;
        double part2 = 1 - Math.pow(percentageOfPointsOnPlane, 3);
        
        double part1 = 1 - confidence;
        double k = Math.log(part1) / Math.log(part2);
        return (int) Math.ceil(k); //it says to return a int, but you get a double, so I used ceiling
    }

    //a method that runs the RANSAC algorithm for identifying a plane in a point cloud (only 1 plane)
    public void run (int numberOfIterations, String filename){
        //lnliers are the supports
        //im going to guess tat numberOfIteration will use getNumberOfIterations
        //the filename will be used to create a new point cloud
        
        int maxSupport = 0;
        Plane3D bestPlane = null;
        for (int i = 0; i < numberOfIterations; i++){
            Point3D p1 = pc.getPoint();
            Point3D p2 = pc.getPoint();
            Point3D p3 = pc.getPoint();
            Plane3D plane = new Plane3D(p1, p2, p3); //end of 2
            int supports = 0;
            for (Point3D point : pc.getPointList()){
                double distance = plane.getDistance(point); //3
                if (distance < eps){
                    supports = supports + 1;
                    //System.out.println(distance);
                }
            }
            if (supports > maxSupport){
                System.out.println(supports);
                maxSupport = supports;
                bestPlane = plane;
                
            }
        }
        int supports = 0;
        PointCloud pc1 = new PointCloud();

        for (Point3D point : pc.getPointList()){
            double distance = bestPlane.getDistance(point); //3
            if (distance < eps){
                supports++;
                pc1.addPoint(point);
                //pc.deletePoint(point);

            }
        }
        System.out.println("THE SIZE OF IT IS "+pc1.getPointList().size());
        for(int i = 0; i<pc1.getPointList().size(); i++){
           pc.deletePoint(pc1.getPointList().get(i));;
        }
        //System.out.println(inliers);
        pc1.save(filename);
        String[] filenameTest = filename.split("_");
        pc.save(filenameTest[0] + "_p2.xyz");

        System.out.println("The best plane is: " + bestPlane);
        System.out.println("The number of inliers is: " + maxSupport);
        System.out.println("The percentage of inliers is: " + (double) maxSupport / pc.getPointList().size());
        System.out.println("The percentage of outliers is: " + (double) (pc.getPointList().size() - maxSupport) / pc.getPointList().size());
        System.out.println("The number of outliers is: " + (pc.getPointList().size() - maxSupport));
        System.out.println("The number of points is: " + pc.getPointList().size());
        System.out.println("The number of iterations is: " + numberOfIterations);
        System.out.println("The epsilon is: " + eps);
        System.out.println("The confidence is: " + confidence);
        System.out.println("The percentage of points on the plane is: " + percentageOfPointsOnPlane);
        System.out.println("The number of points on the plane is: " + (int) (pc.getPointList().size() * percentageOfPointsOnPlane));
        System.out.println("The number of points not on the plane is: " + (int) (pc.getPointList().size() * (1 - percentageOfPointsOnPlane)));
        System.out.println();   //This was just for testing  
        }

        public static void main(String[] args) {
            PointCloud pc = new PointCloud("PointCloud1.xyz");
            PlaneRANSAC pr = new PlaneRANSAC(pc);
            //use the getNumberOfIterations method to get the number of iterations
            int numberOfIter = pr.getNumberOfIterations(0.99, 0.3);
            //use the run method to run the algorithm
            pr.run(numberOfIter, "PointCloud1_p1.xyz");
            System.out.println("");
            System.out.println("");
            
             
            //runs code with cloud2
            //PointCloud pc2 = new PointCloud("PointCloud1.xyz");
            PlaneRANSAC pr2 = new PlaneRANSAC(pc);
            int numberOfIter2 = pr2.getNumberOfIterations(0.99, 0.3);
            pr2.run(numberOfIter2, "PointCloud2_p1.xyz");
            System.out.println("");
            System.out.println("");

            //runs code with cloud3
            //PointCloud pc3 = new PointCloud("PointCloud1.xyz");
            PlaneRANSAC pr3 = new PlaneRANSAC(pc);
            int numberOfIter3 = pr3.getNumberOfIterations(0.99, 0.3);
            pr3.run(numberOfIter3, "PointCloud3_p1.xyz");
            System.out.println("");
            System.out.println("");
            


        }
}
