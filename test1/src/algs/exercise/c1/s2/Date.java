package algs.exercise.c1.s2;

public class Date {
	private final int day;
	private final int month;
	private final int year;
	
	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(String date)
	{
		String []fields = date.split("/");
		month = Integer.parseInt(fields[0]);
		day = Integer.parseInt(fields[1]);
		year = Integer.parseInt(fields[2]);
	}
	public int day()
	{
		return day;
	}
	
	public int month()
	{
		return month;
	}
	
	public int year()
	{
		return year;
	}
	
	public boolean equals(Object that)
	{
		if(that == this) return true;
		if(that == null) return false;
		if(this.getClass()!=that.getClass()) return false;
		Date date = (Date)that;
		if(this.day != date.day) 		return false;
		if(this.month != date.month) 	return false;
		if(this.year != date.year) 		return false;
		return true;
	}
	public String toString()
	{
		return month()+"/"+day()+"/"+year();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
