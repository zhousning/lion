package app.works;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class UtilTool {

	public static String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/upload/");       
        path = path.replaceAll("\\\\", "/");
        
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String fileName = uuid + "." + file.getOriginalFilename().replaceAll("[^\\.]+\\.", "");
        String url = "upload/" + fileName;
        
        File destFile = new File(path + "/" + fileName);
        if (!destFile.getParentFile().exists()) { 
        	destFile.getParentFile().mkdirs();
        }
        
        file.transferTo(destFile);
        return url;
	}
}
