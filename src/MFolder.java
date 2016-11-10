/*
 * MFolder.java
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

public class MFolder extends IFile{
	
	protected ArrayList<MFile> files;
	protected ArrayList<MFolder> folders;
	
	public MFolder(String name){
		this.name = name;
		setType(FileType.FOLDER);
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
	
	public ArrayList<MFile> getFiles(){
		return this.files;
	}
	
	public void setFiles(ArrayList<MFile> files){
		this.files = files;
	}
	
	public void addFile(MFile file){
		if(this.files == null){
			files = new ArrayList<MFile>();
		}
		this.files.add(file);
	}
	
	public ArrayList<MFolder> getFolders(){
		return this.folders;
	}
	
	public void setFolder(ArrayList<MFolder> folders){
		this.folders = folders;
	}
	
	public void addFolder(MFolder folder){
		if(this.folders == null){
			folders = new ArrayList<MFolder>();
		}
		this.folders.add(folder);
	}
	
	public MFolder searchFolder(String name){
		for(MFolder f : folders){
			if(f.getName().equals(name))
				return f;
		}
		return null;
	}
	
	public MFile searchFile(String name){
		for(MFile f : files){
			if(f.getName().equals(name))
				return f;
		}
		return null;
	}
	
	public boolean deleteFile(String name){
		MFile file = searchFile(name);
		if(file!=null){
			file = null;
			return true;
		}
		return false;
	}
	
	public void deleteFolder(String name){
		MFolder folder = this.searchFolder(name);
		folder = null;
	}
	
}

