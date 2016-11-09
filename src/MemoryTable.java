
import java.util.Arrays;

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
            Arrays.fill(this.asignationFileTable,(short)-1);
    }

    public static MemoryTable getMemoryTable(){
        if(memoryTable == null){
            memoryTable = new MemoryTable();
        }
        return memoryTable;
    }
        
  public void delete(short firstBlock){
      //delete all the data from the firstBlock index and update the assignation file table
      short index = firstBlock;
      while(index!=-1){
      	table[index] = new MemoryBlock();
      	index = asignationFileTable[index];
      	asignationFileTable[index] = -1;
      }
  }
	
    public short write(byte[] data, int numBlocks){
            //To write first we need to assigne all the blocks to the memory table and then start to write
      short[] indexBlocks = new short[numBlocks];
      for(int i=0;i<numBlocks;i++){
          short indexBlock = getBlock();
          if(indexBlock == -1){
              //no all free blocks was found, return a negative value to send the message that memory is full
              return -1;
          }
          else{
              indexBlocks[i] = indexBlock;
          }
      }
      //write data into blocks
      int indexData = 0;
      for(int i=0;i<indexBlocks.length;i++){
          byte[] subArray;
          if((indexData + 512) > data.length){
              subArray = Arrays.copyOfRange(data, indexData, data.length);
          }
          else{
              subArray = Arrays.copyOfRange(data, indexData, indexData + 512);
          }
          table[i].setBlock(subArray);
      }
      //set the assignation file table
      for(int i=1;i<indexBlocks.length;i++){
          asignationFileTable[indexBlocks[i-1]] = indexBlocks[i++];
      }
      return indexBlocks[0];
    }

    private short getBlock(){
            //search a single free block to write
            short index = -1;
            for(short i=0;i<table.length;i++){
                    if(table[i]==null){
                            index = i;
                            break;
                    }
            }
            return index;
    }
	
}

