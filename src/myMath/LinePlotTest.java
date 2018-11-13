package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.plots.colors.*;
/**
 * plotting  polynom graph and in addition prints the area under x axis.
 * source:http://trac.erichseifert.de/gral/wiki/Tutorials/XYPlot
 *
 */
public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable data1 = new DataTable(Double.class, Double.class);
        //testing with polynom.
        Polynom p=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
        System.out.println("the vertex are:");
  
        for (double x = -2.0; x <= 6.0; x+=0.25) {
            double y=p.f(x);
            //finding the vertex
            if ((p.derivative().f(x)>0)&&(p.derivative().f(x)<0.5)) {
            	data1.add(x,y); //adding the vertex to data 1 and the other points to data.
            	
            	System.out.println("x="+x+","+"y="+y);
            }else {
            data.add(x, y); 
            }
        }
        //print the area under the x axis ,using the function from the polynom.
       System.out.println("the area under x axis is:"+p.areaUnderXaxis(-0.94064,4.83104 , 0.01));
        XYPlot plot = new XYPlot(data,data1);  //plot with data and data1.
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
       Color color = new Color(0.0f, 0.3f, 1.0f);
       Color color1 = new Color(0.0f, 0.3f, 1.0f);
       plot.getPointRenderers(data).get(0).setColor(color.RED);
       //setting color for data1,the vertex ,to black.
       plot.getPointRenderers(data1).get(0).setColor(color.BLACK);
       plot.getLineRenderers(data).get(0).setColor(color);  
    }
    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}
