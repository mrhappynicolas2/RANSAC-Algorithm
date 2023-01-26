
//Name: Nicolas Berube
//Student ID: 300239551



class Plane3D {
    //constructor
    public Point3D p1;
    public Point3D p2;
    public Point3D p3;

    //Builds the plane if given 3 points
    public Plane3D(Point3D p1, Point3D p2, Point3D p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    //Builds the plane if only given 4 variables
    public Plane3D(double a, double b, double c, double d){
        this.p1 = new Point3D(a, b, c);
        this.p2 = new Point3D(b, c, d);
        this.p3 = new Point3D(c, d, a);
        
    }
    
    //gets the distance frmo the plane to a point
    public double getDistance (Point3D point){ 
        double a = (p2.getY() - p1.getY()) * (p3.getZ() - p1.getZ()) - (p3.getY() - p1.getY()) * (p2.getZ() - p1.getZ());
        double b = (p2.getZ() - p1.getZ()) * (p3.getX() - p1.getX()) - (p3.getZ() - p1.getZ()) * (p2.getX() - p1.getX());
        double c = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
        double d = - (a * p1.getX() + b * p1.getY() + c * p1.getZ());
        return Math.abs(a * point.getX() + b * point.getY() + c * point.getZ() + d) / Math.sqrt(a * a + b * b + c * c);
    }
}