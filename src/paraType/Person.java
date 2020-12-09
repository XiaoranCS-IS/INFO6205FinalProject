package paraType;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

public class Person {
	private int isinfected; //0 - safe 1 - infected 2- dead
	private Person[] childPerson;
	private int day;
	
	public Person() {
		super();
	}

	public Person(int isinfected, Person[] childPerson) {
		super();
		this.isinfected = isinfected;
		this.childPerson = childPerson;
	}

	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getIsinfected() {
		return isinfected;
	}

	public void setIsinfected(int isinfected) {
		this.isinfected = isinfected;
	}

	public Person[] getChildPerson() {
		return childPerson;
	}

	public void setChildPerson(Person[] childPerson) {
		this.childPerson = childPerson;
	}
	
	
}
