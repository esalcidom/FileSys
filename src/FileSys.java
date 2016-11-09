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
	
	private static OpenFileTable openTable;
	private static Directory directory;
	private static FileOrganizer fileOrganizer;
	
	public static void main (String args[]) {
            //parse String to byte and byte to String
            /*
            String s = "Hello world!";
            String result = "";
            try{
                byte[] b = s.getBytes("UTF-8");
                result = new String(b,"UTF-8");
            }
            catch(Exception e){
                
            }
            System.out.println(result);
            ///////////////////////
            ByteBuffer buffer = ByteBuffer.allocate(100);
            String s = "Hello world!";
            String s2 = "What?";

            String result = "";
            try{
                byte[] b = s.getBytes("UTF-8");
                byte[] b2 = s2.getBytes("UTF-8");
                buffer.put(b);
                buffer.put(b2);
                result = new String(buffer.array(),"UTF-8");
            }
            catch(Exception e){

            }
            System.out.println(result);
            //////////////////////
            */
	}
	
	public static MFile open(String path, short blockIndex){
		//Check first on the open file table if the file is alredy open and take the instance
		//If not in the table, check the path on the directory to see if file exists
		try{
			init();
			MFile file = openTable.getFile(IFile.getNameFromPath(path));
			//if the file is not on the open file table now we search on the directory to open it
			if(file == null){
				//search on the directory
				file = directory.searchFile(path);
				if(file == null){
					throw new Exception("The file is not on the directory");
				}
				else{
					openTable.addFile(file);
					return file;
				}
			}
			//if the file is already on the open file table we send the message
			else{
				throw new Exception("The file is already on table");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void create(String path){
		//search on the directory with the path if the file is already created
		//If is not created now we can created.
		//We need to find space to create the file when we find the space now we can create the instance and specify
		//the block of space the name and the size (all metadata)
		//Then we add the file to our directory (tree)
		try{
			init();
			MFile file = openTable.getFile(IFile.getNameFromPath(path));
			//if the file is not in the open file table we now search on the directory
			if(file == null){
				//search if the file is already exist or not on the directory
				file = directory.searchFile(path);
				//if not exist now add the file
				if(file == null){
					//add the new file
					//NOTE: We need to set the blocks of memory set on the file
					directory.addFileToPath(path);
				}
				else{
					throw new Exception("the file already exist");
				}
			}
			//if the file is in the open file talbe we send the message
			else{
				throw new Exception("File is alredy created and open");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void write(String path, byte[] data){
            //Need to search on the open file table if the file is open, we need the file open to write the data on it
            //With the MFile instance we get the block we are going to write and set the data on the specific block of memory
            try{
                init();
                MFile file = openTable.getFile(IFile.getNameFromPath(path));
                if(file != null){
                    //If the file is open now we can write the data.
                    fileOrganizer.writeData(file, data);
                }
                else{
                    throw new Exception("File not opened");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
	}
	
	public static byte[] read(String path){
            //The read method need the instance of the File from the open file table and then read the content from a 
            //specific blockIndex.
            try{
                init();
                MFile file = openTable.getFile(IFile.getNameFromPath(path));
                if(file != null){
                    //If the file is open now we can write the data.
                    fileOrganizer.readData(file);
                }
                else{
                    throw new Exception("File not opened");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }    
	}
	
	public static boolean delete(String path){
		//Check if the file is close on the open file table so we can delete the file
		//Search on the directory the file and then delete all the data on the blocks asign to the file to make them
		//available and delete the instance from the directory
		//return true if the operation was success or false if cannot delete the file

	}
	
	private static void init(){
		if(openTable == null){
			openTable = OpenFileTable.getTable();
		}
		if(directory == null){
			directory = Directory.getDirectory();
		}
		
		if(fileOrganizer == null){
			fileOrganizer = FileOrganizer.getFileOrganizer();
		}
		
	}
	
}

