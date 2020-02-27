package edu.smith.cs.csc212.spooky;

import java.util.Objects;

public class GameTime {
	int hour = 0;
	boolean nighttime;
	public GameTime(int hour) {
		this.hour = 0;
		this.nighttime = true;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void increaseHour() {
		hour = hour + 1;
		if (hour == 24) {
			hour = 0;
		}
	}
	
	public boolean isNightTime() {
		//8 PM to 6 AM -- "night time"
		if (hour >= 20 && hour <= 6) {
			return this.nighttime;
		}
		//12 PM 20
		return false;
	}
		
}
