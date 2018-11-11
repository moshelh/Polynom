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
public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable data1 = new DataTable(Double.class, Double.class);
        Polynom p=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
        for (double x = -2.0; x <= 6.0; x+=0.25) {
            double y=p.f(x);
            if ((p.derivative().f(x)>0)&&(p.derivative().f(x)<0.5)) {
            	data1.add(x,y);
            	
            	System.out.println(x+" "+"+"+" "+y);
            }else {
            data.add(x, y);
            System.out.println(data.getColumnCount()+" "+data.getRowCount());
           
            }
        }
       // System.out.println(data.getColumn(1).get(10)+" "+data.getRow(10).get(1));
       
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
       Color color = new Color(0.0f, 0.3f, 1.0f);
       plot.getPointRenderers(data).get(0).setColor(color.RED);
       plot.getLineRenderers(data).get(0).setColor(color);  
    }
    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}
