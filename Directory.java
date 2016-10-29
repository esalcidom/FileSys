/*
 * Directory.java
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

public class Directory{
	
	public static Directory directory;
	private ArrayList<MFile> files;
	private ArrayList<MFolder> folders;
	private MFolder currentFolder;
	private final MFolder rootFolder;
	
	private Directory(){
		//So currently I dont know how to get the directory that is going to 
		rootFolder = new MFolder("root");
		currentFolder = rootFolder;
	}
	
	public static Directory getDirectory(){
		if(directory == null){
			directory = new Directory();
		}
		return directory;
	}
	
	public void createFolder(String name){
		//If we need to create a folder then the assignation is going to be on the current path
		currentFolder.addFolder(new MFolder(name));
	}
	
	public MFile searchFile(String path){
		//Search for the file with the absolute path with every name.
		//detect if the name is for a file or a directory to serach on the correct list
		//if is a directory now the pointer moves if found
		String[] absolutePath = path.split("/");
		MFolder pointer = rootFolder;
		for(String fileName : absolutePath){
			if(fileName.contains(".")){
				return pointer.searchFile(fileName);
			}
			else{
				pointer = pointer.searchFolder(fileName);
				if(pointer==null){
					return null;
				}
			}
		}
		return null;
	}
	
	
}

