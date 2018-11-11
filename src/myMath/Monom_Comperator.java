package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	/**
	 * 
	 * @param  arg0 ,arg1,compared to each other.
	 * @return 0,if they are equals.
	 * -1,if arg0>arg1
	 * 1,if arg0<arg1
	 */
	public int compare(Monom arg0, Monom arg1) {
		if(arg0.get_power()==arg1.get_power())
		{
			if(arg0.get_coefficient()==arg1.get_coefficient())
				return 0;
			else
				if(arg0.get_coefficient()<arg1.get_coefficient())
					return 1;
				else
					return -1;
		}
		else
		{
			if(arg0.get_power()<arg1.get_power())
				return 1;
			else
				return -1;
		}
		
	}

	// ******** add your code below *********

}
