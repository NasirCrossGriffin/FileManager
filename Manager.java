package fileManager;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.lang.*;
import java.nio.file.*;

public class Manager {
	public static String currentDir = "C:\\";
	
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Welcome to the file manager!");
		System.out.println("Enter a directory to manage files");
		currentDir = scan.nextLine();
		System.out.println("You are working in " + currentDir);
		Path directory = Paths.get(currentDir);
		try {
				Set<String> listOfFiles = listFiles(currentDir);
				for (String file : listOfFiles)
				{
					System.out.println(file);
				}
			}
		catch (IOException e) {
				e.printStackTrace();
			}
		
		
		System.out.println("What file would you like to manage? ");
		String filename = scan1.nextLine();
		Path workingFile = Paths.get(currentDir + "\\" + filename);
		manageFile(workingFile);
		System.out.println("File managed");	
		
	}
	
	public static Set<String> listFiles(String dir) throws IOException {
	    try (Stream<Path> stream = Files.list(Paths.get(dir))) {
	        return stream
	          .filter(file -> !Files.isDirectory(file))
	          .map(Path::getFileName)
	          .map(Path::toString)
	          .collect(Collectors.toSet());
	    }
	}
	
	public static void manageFile(Path workingFile)
	{
		System.out.println("You are currently managing, " + workingFile.getFileName());
		
		char manage;
		
		System.out.println("File management options:\n"
				+ "C: Copy\n"
				+ "M: Move\n"
				+ "D: Delete\n"
				+ "R: Rename\n"
				+ "O: Open\n");
		
		System.out.println("Please select a management option: ");
		Scanner scan = new Scanner(System.in);
		manage = scan.next().charAt(0);
		
		switch(manage) {
			case 'C':
				Copy(workingFile);
				break;
			case 'M':
				break;
			case 'D':
				break;
			case 'R':
				break;
			case 'O':
				break;
		}
	}
	
	public static void Copy(Path workingFile)
	{
		Path source = workingFile;
		Path target = Paths.get(workingFile.getParent() + "/" + "CopyOf" + workingFile.getFileName());
		try {
		    Files.copy(source, target,
		        StandardCopyOption.REPLACE_EXISTING);
		    	System.out.println("File copied successfully!");
		 
		} catch (IOException ex) {
		    System.err.format("I/O Error when copying file");
		}

	}
}
