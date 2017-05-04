// Karen Lopez

import java.text.NumberFormat;

public class Computer {
	
	//instance variables are created
	private String brandName;
	private CPU Cpu;
	private int Memory;
	private double Price;
	
	//This calls the getCurrency method on the NumberFormat package and assigns it to the variable fmt.
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	//Constructor assigns values to instance variables
	public Computer()
	{
		brandName = "?";
		Cpu = new CPU();
		Memory = 0;
		Price = 0.0;
	}
	
	//gets brandName and returns it as a String.
	public String getBrandName()
	{
		return brandName;
	}
	
	//gets Cpu from the CPU class and returns it.
	public CPU getCPU()
	{
		return Cpu;
	}
	
	//gets memory and returns it as an integer.
	public int getMemory()
	{
		return Memory;
	}
	
	//gets price and returns it as a double.
	public double getPrice()
	{
		return Price;
	}
	
	//this method gets a String called BrandName and assigns it to the variable brandName.
	public void setBrandName(String BrandName)
	{
		brandName = BrandName;
	}
	
	//this method gets a String and an integer and assigns them to the methods setType 
	//and setSpeed on the CPU class.
	public void setCPU(String cpuType, int cpuSpeed)
	{
		Cpu.setType(cpuType);
		Cpu.setSpeed(cpuSpeed);
	}
	
	//this method gets an integer called memoryAmount and assigns it to the variable Memory.
	public void setMemory(int memoryAmount)
	{
		Memory = memoryAmount;
	}
	
	//This method gets a double called price and assigns it to the variable Price.
	public void setPrice(double price)
	{
		Price = price;
	}
	
	//creates string called computer which gives back the information from the previous methods. 
	public String toString()
	{
		String computer = "\nBrandName:\t" + brandName + 
				"\nCPU:\t\t" + Cpu.toString() + "\nMemory:\t\t" + 
				Memory + "M" + "\nPrice:\t\t" + fmt.format(Price) + "\n\n";
		return computer;
	}
}
