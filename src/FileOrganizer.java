

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
                fileOrganizer = new FileOrganizer();
        }
        return fileOrganizer;
    }

    public void writeData(MFile file, byte[] data)throws Exception{
        //For design simplicity, every time we are going to write data for a file, we need to search
        //first if the file has data already
        //if no data assigned to a file, we can directly asign blocks of memory and set blocks to the asignation file table
        //and the meta data of the file.
        //if the file is not empty, we need to rewrite blocks of memory 
        //(clear all the blocks already assigned to the file and search for new blocks)
        if(file.getLocation()==-1){
            double blocks = Math.ceil((double)data.length / 512.0);
            //send the data and the number of blocks we need to write on
            short firstBlockToWrite = memoryTable.write(data, (int)blocks);
            if(firstBlockToWrite==-1){
                //we cannot store the file memory full
                throw new Exception("Cannot store file, memory full");
            }
            else{
                //update the file location
                file.setLocation(firstBlockToWrite);
            }	
        }
        else{
            //delete all the blocks from the file and rewrite all the blocks
            memoryTable.delete(file.getLocation());
             double blocks = Math.ceil((double)data.length / 512.0);
            //send the data and the number of blocks we need to write on
            short firstBlockToWrite = memoryTable.write(data, (int)blocks);
            if(firstBlockToWrite==-1){
                //we cannot store the file memory full
                throw new Exception("Cannot store file, memory full");
            }
            else{
                //update the file location
                file.setLocation(firstBlockToWrite);
            }
        }
    }

    public byte[] readData(MFile file)throws Exception{
            //We need to read all the data that is saved on the file, so we need to iterate from the start block memory assigned
            //to the file and then jump block to block index from the asignation file table
            //we need to concatenate all the blocks of memory and create a single array of bytes
            
            short index = file.getLocation();
            if(index==-1){
                throw new Exception("File is blank");
            }
            else{
                //read from index of file
                return memoryTable.read(index);
            }
    }

    public boolean deleteData(MFile file)throws Exception{
            //We itereate over the asignation file table of the file an start to delete all the data from the blocks
            //then we need to refresh all the values of the asignation file table
            short index = file.getLocation();
            if(index==-1){
                throw new Exception("File is blank");
            }
            else{
                memoryTable.delete(index);
                return true;
            }
    }
}

