/*
 * FileSys.java
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
 * Note of the file system
 * -The read and write operations will be only for one process to execute and only one user to use.
 */
import java.io.*;

public class FileSys{
	
	private OpenFileTable openTable;
	
	public static void main (String args[]) {
		
	}
	
	public static MFile open(String path, short blockIndex){
		//Check first on the open file table if the file is alredy open and take the instance
		//If not in the table, check the path on the directory to see if file exists
		if(openTable == null){
			openTable = OpenFileTable.getTable();
		}
		MFile file = openTable.getFile(MFile.getNameFromPath(path));
		if(file == null){
			//search on the directory
		}
		else{
			return file;
		}
	}
	
	public static MFile create(String path){
		//search on the directory with the path if the file is already created
		//If is not created now we can created.
		//We need to find space to create the file when we find the space now we can create the instance and specify
		//the block of space the name and the size (all metadata)
		//Then we add the file to our directory (tree)
	}
	
	public static MFile write(String path, FileOutputStream data){
		//Need to search on the open file table if the file is open, we need the file open to write the data on it
		//With the MFile instance we get the block we are going to write and set the data on the specific block of memory
		
	}
	
	public static FileInputStream read(String path){
		//The read method need the instance of the File from the open file table and then read the content from a 
		//specific blockIndex. 
	}
	
	public static boolean delete(String path){
		//Check if the file is close on the open file table so we can delete the file
		//Search on the directory the file and then delete all the data on the blocks asign to the file to make them
		//available and delete the instance from the directory
		//return true if the operation was success or false if cannot delete the file
	}
	
}

