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
	
	public MFolder getCurrentFolder(){
		return this.currentFolder;
	}
	
	public void setCurrentFolder(MFolder folder){
		//change the current pointer to a specific folder
		this.currentFolder = folder;
	}
	
	public ArrayList<IFile> getFileAndFolders(){
		//the method return all the files and methods on the current folder
		ArrayList<IFile> content = new ArrayList<IFile>();
		for(MFile file:files){
			content.add(file);
		}
		for(MFolder folder:folders){
			content.add(folder);
		}
		return content;
	}
	
	public MFolder getFolderAtPath(String path){
		//get folder from a specific path
		return getPointerOfPath(path);
	}
	
	public void createFolder(String name){
		//If we need to create a folder then the assignation is going to be on the current path
		currentFolder.addFolder(new MFolder(name));
	}
	
	public void setFileName(MFile file, String name){
		file.setName(name);
	}
	
	public MFile searchFile(String path){
		//Search for the file with the absolute path with every name.
		//detect if the name is for a file or a directory to serach on the correct list
		//if is a directory now the pointer moves if found
		String[] absolutePath = path.split("/");
		String fileName = absolutePath[absolutePath.length-1];
		MFolder pointer = getPointerOfPath(path);
		return pointer.searchFile(fileName);
		
	}
		
	public void addFileToPath(String path) throws Exception{
		//add a file to a absolute path if the entire path exist
		String[] absolutePath = path.split("/");
		String fileName = absolutePath[absolutePath.length-1];
		MFolder pointer = getPointerOfPath(path);
		if(pointer!=null){
			pointer.addFile(new MFile(fileName));
		}
		else{
			throw new Exception("[Directory] path not found on directory");
		}
	}
	
	public void deleteFileToPath(String path)throws Exception{
		//delete a file to a absolute path if the entire path and file exist
		String[] absolutePath = path.split("/");
		String fileName = absolutePath[absolutePath.length-1];
		MFolder pointer = getPointerOfPath(path);
		boolean delteSuccess;
		if(pointer!=null){
			//search for the file with the name
			delteSuccess = pointer.deleteFile(fileName);
			if(!delteSuccess){
				throw new Exception("")
			}
		}
		else{
			return false;
		}
	}
        
        public void deleteFolderToPath(String path){
        	//Need to delete the last folder name so need the pointer of the father
        	String[] values = path.split("/");
        	String fatherPath;
        	for(int i=0;i<values.length-1;i++){
        		fatherPath += values[i] + "/";
        	}
        	fatherPath = fatherPath.subString(0,fatherPath-1);
         	MFolder pointer = this.getPointerOfPath(fatherPath);
          pointer.deleteFolder(values[values.length-1]);  
        }
        
        public IFile search(String path){
            IFile file;
            if(path.contains(".")){
                file = this.searchFile(path);
            }
            else{
                file = this.getPointerOfPath(path);
            }
            return file;
        }
	
	private MFolder getPointerOfPath(String path){
		String[] absolutePath = path.split("/");
		MFolder pointer = rootFolder;
		for(String fileName : absolutePath){
			if(!fileName.contains(".")){
				pointer = pointer.searchFolder(fileName);
				if(pointer == null){
					return null;
				}
			}
		}
		return pointer;
	}
	
	
}

