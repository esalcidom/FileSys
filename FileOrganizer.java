/*
 * FileOrganizer.java
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
 * These class is the manager of the write and read operations under the memory of a specific file
 */


public class FileOrganizer{
	
	public static FileOrganizer fileOrganizer;
	private MemoryTable memoryTable;
	
	private FileOrganizer(){
		memoryTable = MemoryTable.getMemoryTable();
	}
	
	public static FileOrganizer getFileOrganizer(){
		if(fileOrganizer == null){
			this.fileOrganizer = new FileOrganizer();
		}
		return FileOrganizer;
	}
	
	public void writeData(MFile file, byte[] data){
		//For design simplicity, every time we are going to write data for a file, we need to search
		//first if the file has data already
		//if no data assigned to a file, we can directly asign blocks of memory and set blocks to the asignation file table
		//and the meta data of the file.
		//if the file is not empty, we need to rewrite blocks of memory 
		//(clear all the blocks already assigned to the file and search for new blocks)
	}
	
	public byte[] readData(MFile file){
		//We need to read all the data that is saved on the file, so we need to iterate from the start block memory assigned
		//to the file and then jump block to block index from the asignation file table
		//we need to concatenate all the blocks of memory and create a single array of bytes
	}
	
	public boolean deleteData(MFile file){
		//We itereate over the asignation file table of the file an start to delete all the data from the blocks
		//then we need to refresh all the values of the asignation file table
	}
}

