package com.Tanks;

import com.Tanks.Util;

import java.io.*;

public class Settings
{
	/**
	* This class holds all the settings for the game. Graphics, keys and sound settings.
	* It can load from a file, or save the current settings.
	*/
	//All settings here are defaults
	int display_width = 800;
	int display_height = 600;
	boolean display_full = false;
	boolean display_vsync = false;
	
	/**
	 * Loads the settings from a file. Returns a value to indicate success
	 * Returned values:
	 * 0 - Success
	 * 1 - Failure - Could not read from file
	 * 2 - Failure - Settings file does not exist
	*/
	public int loadFromFile()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("settings"));
			
			String a = "";
			
			while ((a=in.readLine()) != null)
			{
				String[] args = a.split("=");
				if (args.length == 2)
				{
					
					try
					{
						if (args[0].equals("display_width"))
							this.display_width=Integer.parseInt(args[1]);
						if (args[0].equals("display_height"))
							this.display_height=Integer.parseInt(args[1]);
						if (args[0].equals("display_full"))
							this.display_full=Boolean.parseBoolean(args[1].trim());
						if (args[0].equals("display_vsync"))
							this.display_vsync=Boolean.parseBoolean(args[1].trim());
					}
					catch (NumberFormatException nfe)
					{
						Util.PrintText(2,"Error in settings file at "+args[0]);
					}
				}
			}
		}
		catch (FileNotFoundException fnfe)
		{
			//Can be thrown by FileReader's constructor
			//This probably means the settings file hasn't been created, or has been deleted. Nevermind, just tell the user
			fnfe.printStackTrace();
			return 2;
		}
		catch (IOException ioe)
		{
			//Can be thrown by BufferedReader's constructor
			//This isn't a nice error. There is no indication of what is wrong
			ioe.printStackTrace();
			return 1;
		}		
		

		return 0;
	}
	
	public void printSettings()
	{
		Util.PrintText(2,"Starting with settings:");
		Util.PrintText(2,"Display Width    :"+ this.display_width);
		Util.PrintText(2,"Display Height   :"+ this.display_height);
		Util.PrintText(2,"Display Full     :"+ this.display_full);
		Util.PrintText(2,"Display VSync    :"+ this.display_vsync);
		Util.PrintText(2,"\n");
	}
	
	/**
	 * Save the current settings to the settings file. Returns a value to indicate success.
	 * Returned values:
	 * 0 - Success
	 * 1 - Failure
	*/
	public int saveToFile()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter("settings"));
			out.write("display_width=" + display_width + "\r\n");
			out.write("display_height=" + display_height + "\r\n");
			out.write("display_full=" + display_full + "\r\n");
			out.write("display_vsync=" + display_vsync + "\r\n");
			out.close();
		}
		catch (IOException ioe)
		{
			//Can be thrown by FileWriter's constructor
			ioe.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int getDisplayWidth(){
		return display_width;
	}
	public int getDisplayHeight(){
		return display_height;
	}
	public boolean getDisplayFull(){
		return display_full;
	}
	public boolean getDisplayVsync(){
		return display_vsync;
	}
	
	
	public void setDisplayWidth(int w){
		display_width = w;
	}
	public void setDisplayHeight(int h){
		display_height = h;
	}
	public void setDisplayFull(boolean f){
		display_full = f;
	}
	public void setDisplayVsync(boolean v){
		display_vsync = v;
	}
}