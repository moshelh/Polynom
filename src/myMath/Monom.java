package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{
	private double _coefficient; 
	private int _power;
	/**
	 * 
	 * @param a is the coefficient
	 * @param b is the power of the monom.
	 * @exception in a case the power is negative.
	 */
	public Monom(double a, int b){
		if (b<0) throw new RuntimeException(); //the power can not be smaller than zero.
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * creating a new monom with the same value of the origin monom
	 * @param ot monom
	 * 
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * 
	 * @return the power of the monom
	 */
	public int get_power() {
	
		return _power  ;
	}
	/**
	 * 
	 * @return the coefficient
	 */
	public double get_coefficient() {
		
		return _coefficient;
	}
	/**
	 * the function set the coefficient
	 * @param a the new coefficient
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * the function set the power
	 * @param p the new power
	 */
	private void set_power(int p) {
		this._power = p;
	}
	/**
	 * the function adding new monom to the origin one
	 * @param a new monom
	 */
	public void add(Monom a) {
	      if(a.get_power()==this.get_power()) { //the power must be equals ,otherwise the monom a can not be added to the origin monom.
			if (a.get_coefficient()+this.get_coefficient()==0)
			{
				this.set_coefficient(0);
				this.set_power(0);
			}else
		this.set_coefficient(a.get_coefficient()+this.get_coefficient());
		}
	}
	/**
	 * the function multiply the new monom by the origin one
	 * @param a new monom
	 */
	public void mult(Monom a) {  
		if ((a.get_coefficient()==0)||(this.get_coefficient()==0)) //if one of the coefficient is zero the monom is reset.
		{
			this.set_coefficient(0);
			this.set_power(0);
			
		}else
		this.set_coefficient(a.get_coefficient()*this.get_coefficient());
		this.set_power(a.get_power()+this.get_power());
	}
	/**
	 * the function calculate the derivative on this polynom
	 * 
	 */
	public void derivative()
	{  
	//calculate the derivative by multiplying the coefficient by the power,and subtract 1 from the power.
		if (this.get_power()==0) 
			this.set_coefficient(0);
		else
		{
			this.set_coefficient(get_power()*get_coefficient());
			this.set_power(get_power()-1);
		}
	}
	/**
	 * the function calculate the value of x
	 * @return the value
	 */
	public double f(double x) {
	//return value of x 	
		return this._coefficient*Math.pow(x,this._power);
	} 
	/**
	 * the function make the monom to a string in order to print it.
	 * @return s a string which represent the monom
	 */
	public String toString()
	{   //check all the possibilities of the monom and print correctly.
		String monomS="";
		if(this.get_coefficient()!=1&&this.get_power()!=1&&this.get_coefficient()!=-1)
			monomS=""+this.get_coefficient()+"*x^"+this.get_power();
		if(this.get_power()==0&&this.get_coefficient()!=0)
		        monomS=""+this.get_coefficient();
		if(this.get_power()==1&&this.get_coefficient()!=1)
				monomS=""+this.get_coefficient()+"*x";
		if(this.get_coefficient()==1&&this.get_power()==1)
			    monomS=""+"x";
		if(this.get_coefficient()==-1&&this.get_power()==1)
		    monomS=""+"-x";
		if(this.get_coefficient()==1&&this.get_power()!=1&&this.get_power()!=0)
			monomS=""+"x^"+this.get_power();
		if(this.get_coefficient()==-1&&this.get_power()!=1&&this.get_power()!=0)
			monomS=""+"-x^"+this.get_power();
		return monomS;
		
		}
	
	public boolean equals(Monom m1) {
		if((this.get_coefficient()==m1.get_coefficient())&&(this.get_power()==m1.get_power()))
			return true;
		return false;
	}
	}
	
