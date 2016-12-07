package by.samsolutions.controller.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil
{

	private FileUtil()
	{

	}

	public static String saveImageToDisk(final HttpServletRequest request, final MultipartFile file, final String requestImageUrl)
					throws IOException
	{

		if (file != null && !file.isEmpty())
		{
			String imageUrl;

			String uploadsDir = "/static/core/pictures/";
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
