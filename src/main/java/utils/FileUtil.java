package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
	
	
	// 파일 이름 변경
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS")
					.format(new Date());
		String newFileName = now + ext;
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory+File.separator+newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
}
