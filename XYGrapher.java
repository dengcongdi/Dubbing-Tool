import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.image.BufferedImage;
public class XYGrapher {
    private double theWidth;
    private Coordinate[] coordinatesForGraph;
    public XYGrapher(double width, Coordinate[] myCoords){theWidth=width; coordinatesForGraph=myCoords;}
    public Coordinate xyStart(){return new Coordinate(0.0,-50.0);}
    public double xRange(){return theWidth;}
    public double yRange(){return 100.0;}

    public Coordinate getPoint(int pointNum){
	if(pointNum>=coordinatesForGraph.length){
	    return null;
	}else{return coordinatesForGraph[pointNum];}
    }

    public BufferedImage drawGraph(int xPixelStart, int yPixelStart, int pixelsWide, int pixelsHigh){
        
	BufferedImage theImage=new BufferedImage(100,(int)xRange(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = theImage.createGraphics();

	
	Coordinate xy=xyStart();
	double x=xRange();
	double y=yRange();
	int i=1;
	Coordinate firstPoint=getPoint(0);
	double xZero = xPixelStart + (firstPoint.getX()-xy.getX())*(pixelsWide/x);
	double yZero = yPixelStart + (xy.getY()+y-firstPoint.getY())*(pixelsHigh/y);
	while(getPoint(i)!=null){
	    Coordinate thePoint=getPoint(i);
	    double xPixel = xPixelStart + (thePoint.getX()-xy.getX())*(pixelsWide/x);
	    double yPixel = yPixelStart + (xy.getY()+y-thePoint.getY())*(pixelsHigh/y);

	   
	    g2.drawLine(xZero,yZero,xPixel,yPixel);
	    xZero=xPixel;
	    yZero=yPixel;
	    i++;
	    
	}
	return theImage;
    }
}