

package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import javax.swing.plaf.synth.SynthStyle;

public class test {
public static void main(String[] args) {


String s="^2+3x3+765";
Polynom p=new Polynom(s);
String s1="-20*x^2+3*x+6";
Polynom p1=new Polynom(s1);
String s2="-20*x^2+3*x";
Polynom p2=new Polynom(s2);
Polynom p3=new Polynom("-x^2-1x");
//multiply
p2.multiply(p1);
System.out.println("the result of mult is:"+" "+p2.toString());
//add
p.add(p1);
System.out.println("the sum is:"+" "+p.toString());
//isZero
System.out.println("is the polynom empty?"+" "+p.isZero());
//subtract
p2=new Polynom("-20*x^2+3*x");
p2.substract(p1);
System.out.println("the result of sub is:"+" "+p2.toString());
//area
Polynom parea=new Polynom("3x^2+6x+2");
System.out.println("the area is:"+parea.area(0,2,0.5));
//root
Polynom proot=new Polynom("x^3-x^2+2");
System.out.println("the root of the Polynom x^3-x^2+2 is:"+proot.root(-200,300,0.01));
//value at x
double answer=p3.f(2);
System.out.println("the value of x=2 is:"+" "+answer);
//equals
Polynom p4=new Polynom("2x-1-x^2");
Polynom p6=new Polynom("-x^2+2x-1");
System.out.println("are the polynoms equals?"+" "+p4.equals(p6));
System.out.println("are the polynoms equals?"+" "+p3.equals(p4));
//derivative
Polynom p5=new Polynom("-1.5x^2+6-8.5x");
p5.derivative();
System.out.println("the derivative of polynom p5 is:"+p5.derivative().toString());

Polynom p111=new Polynom("3x^2-x^3+x-1");
String ss1=p111.toString();
Polynom p222=new Polynom("x^2+x+1");

p111.derivative();
System.out.println(p111);
Polynom expected2=new Polynom("-3.0*x^2+6.0*x+1.0");
System.out.println(p111.equals(expected2));

Polynom expected=(Polynom) p111.copy();
System.out.println(expected.toString());
String s33=expected.toString();
String s44=p111.toString();
System.out.println(s33.equals(s44));


	


	


}
}