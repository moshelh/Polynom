package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Moshe&&Arial
 * @version 1.0
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> arrMonom;
	private final Monom_Comperator comp=new Monom_Comperator();
	/**
	 * the function return the arrayList which represent the polynom.
	 * @return the arrayList of the monom
	 */
	public ArrayList<Monom> getArr1() {  //the arrayList of monoms which represent the polynom.
		return arrMonom;
	}
//construction
	/**
	 * the function gets a string and covert the string into polynom.
	 * @param s string of polynom.
	 */
	public Polynom(String s) {
		try {
		arrMonom = new ArrayList<Monom>();
		s=s.replaceAll("-", "+-");
		if(s.contains(" ")){         //in case there are unnecessary spaces.
			s=s.replaceAll(" ", "");
		}
		if(s.charAt(0)=='+')
		{
			s=s.substring(1);
		}
		if(s.contains("*")) {
			s=s.replaceAll("\\*", "");
		}
		String []Monoms=s.split("\\+");
		for(int i=0;i<Monoms.length;i++) {
			double coefficient;
			int power;

			if(!Monoms[i].contains("^"))  //means the monom can be a number or a coefficient.
			{
				if(!Monoms[i].contains("x"))  //a number.
				{
					coefficient=Double.parseDouble(Monoms[i]);
					power=0;
				}
				else {
					if(Monoms[i].length()>1) {
						String[]splitByX=Monoms[i].split("x");
						if((splitByX[0].length()==1)&&(splitByX[0].charAt(0)=='-')){  //means "-x"
							coefficient=-1;
							power=1;
						}
						else {               //means "ax",a is a coefficient.
							power=1;
							coefficient=Double.parseDouble(splitByX[0]);
						}
					}

					else {                   //means "x"
						coefficient=1;
						power=1;
					}
				}
			
			}
			else {                          //means it can be :ax^b,x^b.
				String[]splitByPower=Monoms[i].split("\\^");
				if(splitByPower[0].length()>1) {
					String[]splitByX=splitByPower[0].split("x");
					if((splitByX[0].length()==1)&&(splitByX[0].charAt(0)=='-')){ //means"-x^b"
						coefficient=-1;
						power=Integer.parseInt(splitByPower[1]);
					}
					else {                                                       //means:"ax^b"
						coefficient=Double.parseDouble(splitByX[0]);
						power=Integer.parseInt(splitByPower[1]);
					}
				}
				else {                                                           //means:"x^b
					coefficient=1;
					power=Integer.parseInt(splitByPower[1]);
				}

			}
			Monom newMonom=new Monom(coefficient,power);                         //create the new monom and add to the array list.
			arrMonom.add(i,newMonom);
		}
	this.red();         
	//in the end,send to the red function to reduce and organize the polynom.
		}
		catch(NumberFormatException e) {
			throw new RuntimeException("please enter a new polynom ");
		}
	}
		
		
	public Polynom() {
		arrMonom = new ArrayList<Monom>();
	}
	/**
	 * create a copy
	 * @param p is a polynom
	 */
	public Polynom (Polynom p)
	//deep copy
	{
		arrMonom = new ArrayList<Monom>();
		int counter=0;
		Iterator<Monom> itArrMonom = p.getArr1().iterator();
		while (itArrMonom.hasNext())
		{
			Monom pointerArrMonom=itArrMonom.next();
			Monom m = new Monom(pointerArrMonom.get_coefficient(),pointerArrMonom.get_power());
			arrMonom.add(counter,m);
			counter ++;
		}
	}
	/**
	 * the function calculate the value of x
	 * @return sum ,the value of x
	 */
    public double f(double x) {
		double sum=0;   //sum the values of x in each monom in the array by calling the f function of monom.
		Iterator<Monom> itArrMonom = arrMonom.iterator();
		while (itArrMonom.hasNext())
		{
			Monom pointerArrMonom=itArrMonom.next();
			sum=sum+pointerArrMonom.f(x);
		}
		return sum;
	}
    /**
     * the function add polynom able to this polynom.
     * @param p1 Polynom able
     */
    public void add(Polynom_able p1) {
    	//add to the array the polynom ,monom by monom and send to red function ,where the addaing operation is done.
		Iterator<Monom> itp1 = p1.iteretor();
		while(itp1.hasNext())
		{
			Monom pointerP1=itp1.next();
			this.arrMonom.add(pointerP1);
		}
		this.red();

	}
    /**
     * the function add monom to the polynom.
     * @param m1 is a monom.
     */
    public void add(myMath.Monom m1) {
	arrMonom.add(m1);
	this.red();    //after adding the monom to the array,send to red function in order to perform the adding operation and checking if there are 2 monom which can be added to each other..
	}
    /**
     * the function subtract p1 from this polynom 
     * @param p1 is a polynom able
     */
    public void substract(Polynom_able p1) {
		//multiple each monom in the polynom by -1,than send the array to the red function in order to subtract and organzie.
		Monom minus=new Monom(-1,0);
		Iterator<Monom> itP1 = p1.iteretor();
		while(itP1.hasNext())
		{
			Monom pointerP1=itP1.next();
			pointerP1.mult(minus);
			this.arrMonom.add(pointerP1);
		}
		this.red();	
	}
    /**
     * the function multiply p1 by this polynom
     * @param p1 is a polynom able
     */
	public void multiply(Polynom_able p1) {
		//building a new array to save the results of each multiplication
		Iterator<Monom> itP1 = p1.iteretor();
		Iterator<Monom> itArrMonom = arrMonom.iterator();
		ArrayList<Monom> newArray = new ArrayList<Monom>();
		while(itArrMonom.hasNext())
		{
			Monom pointerArrMonom=itArrMonom.next();
			while(itP1.hasNext()) {                       //multiply every monom in the origin monom by all the monoms in p1 .
				                                          //build a new monom to save its value in order to multiply it by every monom in p1.
				Monom temp= new Monom (pointerArrMonom);
				Monom pointerP1=itP1.next();
				temp.mult(pointerP1);                     //using the mult function which was defined in the Monom class.
				newArray.add(temp);
			}
			itP1 = p1.iteretor();
		}
		arrMonom.removeAll(arrMonom);                    //after finishing multiply ,remove all the monoms from the origin array and make a deep copy from the copy array, which holds the result, to the origin one.
		for (int i=0;i<newArray.size();i++)
		{
			arrMonom.add(newArray.get(i));
		}
		this.red();                                      //send to red function in order to organize the array.
	}
	/**
	 * the function equals p1 to this polynom
	 * @return true if they are equals or false othewise.
	 */
	public boolean equals(Polynom_able p1) {
//subtract the origin polynom from p1 ,checking if the result is zero ,if it is ,means they are equal.
		this.substract(p1);
		if(this.isZero())
			return true;
		return false;
		
		
	}

/**
 * the function checks if the polynom is the zero polynom
 * @return true if the araayList is empty or false otherwise.
 */
	public boolean isZero() {
	//if the array is empty means is the zero polynom.
		if (arrMonom.isEmpty())
			return true;
		return false;

	}
	
	@Override
	/**
	 * the function claculate the root of the polynom
	 * @return the root
	 */
	//source:https://www.geeksforgeeks.org/program-for-bisection-method
	public double root(double x0, double x1, double eps) {
		double mid = x0;
		while((x1-x0)>=eps)    //as long as the middle deviation is bigger than eps, continue reduce the range,by define a new edges.
		{
			mid=(x0+x1)/2;
			if (this.f(mid)==0)     //means the root was found.
				return mid;
			else if (this.f(x0)*this.f(mid)<0){    //the new edges have to be in a different sizes of the x axis. 
				x1=mid;
			}else {
				x0=mid;
			}
		}
		return mid;
	}
/**
 * the function copy this polynom to polynom able.
 * @return the new polynom able.
 */
	public Polynom_able copy() {
		//copy the values of the ArrMonom into a new array of Polynom able.
		//using for loop for a deep copy.
		Polynom_able NewPolynomAble=new Polynom();
		for (int i=0 ;i< this.getArr1().size();i++)
		{
			NewPolynomAble.add(this.getArr1().get(i));
		}
		return NewPolynomAble;
	}

	/**
	 * the function calculate the derivatuve of this polynom
	 * @return polynom able which holds the result of the derivation.
	 */
	public Polynom_able derivative() {
		//the function calculate the derivative of the polynom by creating a new polynom_able which holds the answer.
		Polynom_able NewPolynomAble=new Polynom();
		Iterator<Monom> itArrMonom = this.iteretor();	
		Monom pointerArrMonom;
		//running as long the array is not empty and has a next monom .using the derivative function of monom in each loop.
		while(itArrMonom.hasNext())
		{
			Monom m= itArrMonom.next();
			pointerArrMonom=new Monom(m);
			pointerArrMonom.derivative();
			NewPolynomAble.add(pointerArrMonom);
		}
		return NewPolynomAble;
	}
	/**
	 * the function calculate the area by using riemann's integral.
	 * @return the area.
	 */
	
	public double area(double x0, double x1, double eps) {
		//sum the area of each part,every time go forward by eps,using Riemann's Integral.
		double sum=0;
		for(double i = x0;i<x1;i+=eps)
		{
			sum= sum+this.f(i)*eps;
		}
		return sum;
	}
	
	public Iterator<myMath.Monom> iteretor() {
		Iterator<Monom> m =arrMonom.iterator();
		return m;
	}
	/**
	 * the function make the polynom a string in order to print the polynom
	 * @return s the final stirng which represents the polynom
	 * 
	 */
	public String toString() {
		//using the toString function of the monom class.
		String polynomS="";
       for(int i=0;i<arrMonom.size();i++)
		{ 
    	   if(i<arrMonom.size()-1)
    	   {
    		   if(arrMonom.get(i+1).toString().charAt(0)=='-')
    			   polynomS+=""+arrMonom.get(i).toString();
    		   else
    			   polynomS+=""+arrMonom.get(i).toString()+"+";
    	   }
    	   else
    		   polynomS+=""+arrMonom.get(i).toString();
		}
		
		return polynomS;
	}
	
	/**
	 * reduction function-the function reorganize the polynom.
	 * so in the end every power will be performed only 1 time,after reduction.
	 * 
	 */
	
	private void red(){
		boolean ifaddMonom=false;
		ArrayList<Monom> copyArray = new ArrayList<Monom>(); //new copy array.
		Iterator<Monom> itOriginArray = arrMonom.iterator(); // iterator for this array.
		Monom pointerOriginArray=itOriginArray.next(); // pointer for the first object.
		copyArray.add(pointerOriginArray); // add the first object to the copy array.
		Iterator<Monom> itCopyArray = copyArray.iterator(); // iterator for the copy array.
		itOriginArray.remove(); // remove the first object from the this copy.
		Monom pointerCopyArray;
		while (!arrMonom.isEmpty()) { // keep running as long as the array is not empty.
			pointerOriginArray=itOriginArray.next();	// continue to the next object
			while((itCopyArray.hasNext())&&(!(ifaddMonom))) { //keep running as long as the copy array is not empty and the monom was not added to another monom .
				pointerCopyArray=itCopyArray.next();
				if (pointerOriginArray.get_power()==pointerCopyArray.get_power())  //if the powers are equals ,add.in addition, check if the sum is zero,if it is ,remove the monom from the two arrays.
				{  
					pointerCopyArray.add(pointerOriginArray);
					if ((pointerCopyArray.get_coefficient()==0)&&(pointerCopyArray.get_power()==0))
					{
						itCopyArray.remove();
						ifaddMonom=true;
						itOriginArray.remove();
					}else {
						ifaddMonom=true;
						itOriginArray.remove();
					}
				}
			}
			if (!ifaddMonom) {      //means there was not an equal power to this monom,add the monom to the array.
				copyArray.add(pointerOriginArray);
				itOriginArray.remove();
			}
			ifaddMonom=false;        
			itCopyArray = copyArray.iterator();
		}
		if (!(copyArray.isEmpty())) {  //if the array is not empty,do a deep copy of the organized copy array to the origin array .

			for (int i=0;i<copyArray.size();i++)
			{
				arrMonom.add(copyArray.get(i));
			}
			arrMonom.sort(comp);       //sort by comp which was already defined in the monom comperator.
		}
	}
}