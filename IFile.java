/*
 * IFile.java
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

public abstract class IFile{
	
	String name;
	String type;
	short location;
	short size;
	Date date;
	String protection;
	
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getType();
	public abstract void setType(String type);
	public abstract short getLocation();
	public abstract void setLocation(short location);
	public abstract short getSize();
	public abstract void setSize(short size);
	public abstract Date getDate();
	public abstract void setDate(Date date);
	public abstract String getProtection();
	public abstract void setProtection(String protection);
	
	public static String getNameFromPath(String path){
		String[] values = path.split("/");
		return values[values.length-1];
	}
}

