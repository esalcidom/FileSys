/*
 * MemoryTable.java
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
 * We are going to use an asignation file table to link all the blocks that belongs to a file seting the index of the next block
 * asigned.
 * 
 * We need to add a method to write and read a signle block of memory or a set of continuos block of a file.
 * 
 * 
 */


public class MemoryTable{
	
	public static MemoryTable memoryTable;
	private MemoryBlock[] table;
	private short[] asignationFileTable;
	
	private MemoryTable(){
		this.table = new MemoryBlock[5000];
		this.asignationFileTable = new short[5000];
	}
	
	public static MemoryTable getMemoryTable(){
		if(this.memoryTable == null){
			memoryTable = new MemoryTable();
		}
		return this.memoryTable;
	}
	
	public short write(byte[] data, double numBlocks){
		//To write first we need to assigne all the blocks to the memory table and then start to write
	}
	
	private short getBlock(){
		//search a single free block to write
		short index;
		for(short i=0;i<table.length;i++){
			if(table[i]==null){
				index = i;
				break;
			}
		}
		return index;
	}
	
}

