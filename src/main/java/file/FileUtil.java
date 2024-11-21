package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	public static ArrayList<String> multipleFile(HttpServletRequest req,
			String sDirectory) throws ServletException, IOException{
		
		ArrayList<String> listFileName = new ArrayList<>();
		
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			if(!part.getName().equals("ofile")) {
				continue;
			}
			
			String partHeader = part.getHeader("content-disposition");
			System.out.println("partHeader="+ partHeader);
			
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"","");
			
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory+File.separator + originalFileName);
			}
			
			listFileName.add(originalFileName);
		}
		
		return listFileName;
	}
}
