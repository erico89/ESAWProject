package controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/FileUploader")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class FileUploader extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String root_path;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploader() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	root_path = getServletContext().getRealPath("/");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
			String filename = request.getPart("file").getSubmittedFileName();
			
		    for (Part part : request.getParts()) {
		      saveProfilePhoto(part,filename);
		    }
		    
		    response.getWriter().print("File sucessfully uploaded");
		    
		} catch (Exception e) {
			e.printStackTrace();
		}	
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void saveProfilePhoto(Part file, String filename) throws IOException 
	{
		//Set OS slasher
		String nameOS = System.getProperty("os.name").toLowerCase();
		String slasher;
		if(nameOS.contains("windows")) slasher = "\\";
		else slasher = "/";
		
		//Create output directory if it is not already created
		String outputDirectory = "imgs";
		File newDirectory = new File(root_path + slasher + outputDirectory);
		
	    if (!newDirectory.exists()){
	        newDirectory.mkdirs();
	    } 
	    
	    //Write the file in the output directory
	    try {
	    	file.write(newDirectory.getAbsolutePath() + slasher + filename);
	    	System.out.println("Photo saved at: " + newDirectory.getAbsolutePath());
	    }catch(IOException e) {
	    	e.getMessage();
	    }
	}

}
