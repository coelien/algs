package algs.exercise.c1.s2;

import edu.princeton.cs.algs4.StdOut;

public class Transaction {
	private Date date;
	private String who;
	private final double amount;
	
	public Transaction(String who, Date date, double amount)
	{
		this.who = who;
		this.date = date;
		this.amount = amount;
	}
	public Transaction(String transac)
	{
		String [] fields = transac.split("\\s+");
		who = fields[0];
		date = new Date(fields[1]);
		amount = Integer.parseInt(fields[2]);
	}
	public String who()
	{
		return who;
	}
	
	public Date when()
	{
		return date;
	}
	
	public double amount()
	{
		return amount;
	}
	
	public boolean equals(Object that)
	{
		if(that == this) return true;
		if(that == null) return false;
		if(this.getClass()!=that.getClass()) return false;
		Transaction tr = (Transaction)that;
		if(!this.who.equals(tr.who)) 		return false;
		if(!this.date.equals(tr.date)) 		return false;
		if(this.amount != tr.amount) 		return false;
		return true;
	}
	
	public String toString()
	{
		return who()+" "+when()+" "+amount();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
