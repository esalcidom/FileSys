/*
 * MFile.java
 * 
 * Copyright 2016 Emmanuel <emmanuel.salcido.maldonado@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.util.*;

public class MFile extends IFile{
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public short getLocation(){
		return this.location;
	}
	
	public void setLocation(short location){
		
		this.location = location;
	}
	
	public short getSize(){
		return this.size;
	}
	
	public void setSize(short size){
		//need to get all the content on bytes
		this.size = size;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date date){
		//maybe the args are the year, month, day and time
		this.date = date;
	}
	
	public String getProtection(){
		return this.protection;
	}
	
	public void setProtection(String protection){
		this.protection = protection;
	}
}


