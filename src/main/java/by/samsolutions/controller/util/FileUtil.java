package by.samsolutions.controller.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import by.samsolutions.controller.exception.TooLargeFileException;

@Component
public class FileUtil
{

	@Value("${uploadsDir}")
	private String uploadsDir;

	public String saveImageToDisk(final HttpServletRequest request, final MultipartFile file, final String requestImageUrl)
					throws TooLargeFileException, IOException
	{

		if (file != null && !file.isEmpty())
		{
			if(file.getSize() > 50_000_000L)
			{
				throw new TooLargeFileException();
			}

			String imageUrl;
			String pathToUploads = request.getServletContext().getRealPath(uploadsDir);

			if (!new File(pathToUploads).exists())
			{
				new File(pathToUploads).mkdir();
			}

			String orgName = file.getOriginalFilename();
			String filePath = pathToUploads + File.separator + orgName;
			imageUrl = request.getContextPath() + uploadsDir + orgName;
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
