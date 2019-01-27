/** 
 * A Temperature object represents temperature (with a value and scale)
 *
 * COMP 1006/1406
 * Summer 2018
 * Assignment 2
 */
 
public class Temperature{

  /** different scale names */
	public static String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};


	 
	/** Initializes a temperature object with given value in Celcius
	 *  
	 *  If the initial temperature is less than -273.15 then the temperature 
	 *  object will be initialized with -273.15C.
   *   
	 * @param temp is the initial temperature in Celsius.
	 */
	private double temper = 0;
	private char pscale;
  public Temperature(double temp){
	if (temp<-273.15) temp = -273.15;
	temper = temp;
	pscale = 'C';
  }

	
	/** Initializes a temperature object with given value using the specified scale
   * <par>
	 * If the temperature is lower than absolute zero, then set the temperature to
   * absolute zero (in whichever scale is specified).
   * <par>	 
 	 * Examples: new Temperature(12.3, "K")
   *           new Temperature(-90.2, "Celsius")
	 *
	 * @param temp is the initial temperature
	 * @param scale is the scale of initial temperature and must either be 
	 *        one of the Strings in the <code>scales</code> array, or 
	 *        the first letter (capitalized) of one of those strings.
	 */
  public Temperature(double temp, String scale){
	if (scale=="C"){
		if (temp < -273.15) temper = -273.15;
		temper = temp;
		pscale = 'C';
	}else if (scale=="K"){
		if (temp<0) temper = 0;
		temper = temp;
		pscale = 'K';
	}else if (scale=="F"){
		if (temp<-459.67) temper = -459.67;
		temper = temp;
		pscale = 'F';
	}
  }


	/** getter for the scale
	 *
	 * The output of this getter method must always be the first letter of one
	 * of the strings in the <code>scales</code> array, capitalized.
   *	 
	 * @return the current scale of the object as a single char (the first letter, 
	 *         capitalized of one of the strings from <code>scales</code>)
	 */
  public char getScale(){

  	return pscale;
  }

	/** getter for the temperature
	 *
	 * @return the temperature of the object using the current scale
	 */
  public double getTemp(){

  	return temper;
  }


  /** setter for scaled
	 *
	 * 
	 *
	 * @param scale is the new scale of the temperature and must either be 
	 *        one of the Strings in the <code>scales</code> array, or 
	 *        the first letter (capitalized) of one of those strings.
	 */
  public void setScale(String scale){
  	if (scale=="C"){
		if (pscale=='K') temper -= 273.14;
		if (pscale=='F') temper = (temper - 32) * 5/9;
		pscale = 'C';
	}else if (scale=="K"){
		if (pscale=='C') temper += 273.14;
		if (pscale=='F') temper = (temper + 459.67) * 5/9;
		pscale = 'K';
	}else if (scale=="F"){
  		if (pscale=='C') temper = temper * 9/5 + 32;
  		if (pscale=='K') temper = temper * 9/5 - 459.67;
  		pscale = 'F';
	}

  }


	/** setter for temperature
	 *
	 * @param temp is the new temperature (in the objects current scale)
	 */
  public void setTemp(double temp){
	  if (temp<-273.15) temp = -273.15;
	  temper = temp;
	  pscale = 'C';
  }

	/** setter for temperature
	 *
	 *
	 * @param temp is the new temperature
	 * @param scale is the scale of the new temperature. It must be 
   *        the first letter (capitalized) of one of the strings in
   *        the <code>scales</code> array.
   */	 
  public void setTemp(double temp, char scale){
	  if (scale=='C'){
		  if (temp < -273.15) temper = -273.15;
		  temper = temp;
		  pscale = scale;
	  }else if (scale=='K'){
		  if (temp<0) temper = 0;
		  temper = temp;
		  pscale = 'K';
	  }else if (scale=='F'){
		  if (temp<-459.67) temper = -459.67;
		  temper = temp;
		  pscale = 'F';
	  }
  }

  /** setter for temperature
	 *
	 * @param temp is the new temperature
	 * @param scale is the scale of the new temperature. It must be one
	 *        of the strings in the <code>scales</code> array, or the first letter
	 *        (capitalized) of one of those strings.
	 */
	public void setTemp(double temp, String scale){
		if (scale=="C"){
			if (temp < -273.15) temper = -273.15;
			temper = temp;
			pscale = 'C';
		}else if (scale=="K"){
			if (temp<0) temper = 0;
			temper = temp;
			pscale = 'K';
		}else if (scale=="F"){
			if (temp<-459.67) temper = -459.67;
			temper = temp;
			pscale = 'F';
		}
	}






	
	
	
	

	/* ------------------------------------------------- */
	/* ------------------------------------------------- */
  /* do not change anything below this                 */
  /* ------------------------------------------------- */
	/* ------------------------------------------------- */
	
	

  /** String representation of a temperature object    */
	@Override
  public String toString(){ return "" + this.getTemp() + this.getScale(); }

}
