
//Name: Nicolas Berube
//Student ID: 300239551

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class PointCloud {
    private List<Point3D> pointList;
    //constructs a point cloud from a file
    public PointCloud(String filename) {
        File file= new File(filename);

        // this gives you a list of a list of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner input;

        try{ //try to find the file and then add the lines to a array
            input = new Scanner(file);

            while(input.hasNext()){
                
                String line = input.next();
                //System.out.println(lines);
                String[] values = line.split(",");
                lines.add(Arrays.asList(values)); //add the line to the array
            }

            input.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }

        List<Point3D> pointArray = new ArrayList<>();
        for (int i = 3; i<lines.size(); i=i+3){
            Double x = Double.parseDouble(lines.get(i).get(0));
            Double y = Double.parseDouble(lines.get(i+1).get(0));
            Double z = Double.parseDouble(lines.get(i+2).get(0));

            ///create a new Point with the values that were given            
            Point3D point = new Point3D(x, y, z);
            pointArray.add(point); //add those new points to the array
            
        }
        this.pointList = pointArray;
    }
    
    //an empty constructor that constructs an empty point cloud
    public PointCloud() {
        List<Point3D> pointArray = new ArrayList<>();
        this.pointList = pointArray;
    }

    //adds a point to the point cloud
    public void addPoint(Point3D point) {
        this.pointList.add(point);
    }

    public void deletePoint(Point3D point) {
        this.pointList.remove(point);
    }

    //returns a random point from the cloud
    public Point3D getPoint() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(this.pointList.size());
        return this.pointList.get(randomIndex);
    }

    //an iterator method that returns an iterator to the points in the cloud
    public Iterator<Point3D> iterator() {
        return this.pointList.iterator();
    
    }

    //a save method that saves the point cloud to file
    public void save(String filename) {
        try{
            PrintWriter writer = new PrintWriter(filename);
            writer.println("x,y,z");

            //for loop that goes through the array and prints the values into a file
            for(int l = 0; l<pointList.size(); l++){
                Point3D newVal = pointList.get(l);
                writer.println(String.valueOf(newVal.getX())+ " " + String.valueOf(newVal.getY()) +" "+ String.valueOf(newVal.getZ()));
            }
            writer.close(); //stops writing
        }
        catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
    }

    public List<Point3D> getPointList() {
        return pointList;
    }
}
