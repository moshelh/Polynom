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
 * LinePlotTest gets a string,makes polynom and plot the graph of the function.
 * source:GRAL
 *
 */
public class LinePlotTest extends JFrame {
    public LinePlotTest(String s) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        //making a polynom.
        Polynom p=new Polynom(s);
       DataTable data = new DataTable(Double.class, Double.class);
       //data1 for saving the vertex.
       DataTable data1 = new DataTable(Double.class, Double.class);
       
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
       
        XYPlot plot = new XYPlot(data,data1);  //plot with data and data1.
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
       Color color = new Color(0.0f, 0.3f, 1.0f);
       plot.getPointRenderers(data).get(0).setColor(color.RED);
       //setting color for data1,the vertex ,to black.
       plot.getPointRenderers(data1).get(0).setColor(color.BLACK);
       plot.getLineRenderers(data).get(0).setColor(color);  
    }

}


            

