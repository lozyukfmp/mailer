package by.samsolutions.controller.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import by.samsolutions.controller.exception.TooLargeFileException;

@Component
public class FileUtil
{

	@Value("${uploadsDir}")
	private String uploadsDir;

	@Value("${maxFileSize}")
	private Long maxFileSize;

	public String saveImageToDisk(final HttpServletRequest request, final MultipartFile file, final String requestImageUrl)
					throws TooLargeFileException, IOException
	{

		if (file != null && !file.isEmpty())
		{
			if(file.getSize() > maxFileSize)
			{
				throw new TooLargeFileException();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();

			String imageUrl;
			String pathToUploads = request.getServletContext().getRealPath(uploadsDir);
			//String pathToUploads = uploadsDir + File.separator + username;

			if (!new File(pathToUploads).exists())
			{
				new File(pathToUploads).mkdir();
			}

			//String orgName = (new Date()).getTime() + file.getOriginalFilename();
			String orgName = file.getOriginalFilename();
			String filePath = pathToUploads + File.separator + orgName;
			imageUrl = request.getContextPath() + uploadsDir + orgName;
			//imageUrl = orgName;
			File dest = new File(filePath);
			file.transferTo(dest);

			return imageUrl;
		}
		else
		{
			return requestImageUrl;
		}

	}
}
