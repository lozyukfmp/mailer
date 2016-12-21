package by.samsolutions.imgcloud.controller.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import by.samsolutions.imgcloud.controller.exception.TooLargeFileException;

@Component
public class FileUtil
{

	@Value("${uploadsDir}")
	private String uploadsDir;

	@Value("${maxFileSize}")
	private Long maxFileSize;

	public String saveImageToDisk(final MultipartFile file, final String requestImageUrl)
					throws TooLargeFileException, IOException
	{

		if (file != null && !file.isEmpty())
		{
			if (file.getSize() > maxFileSize)
			{
				throw new TooLargeFileException();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();

			String imageUrl;
			String pathToUploads = uploadsDir + File.separator + username;

			if (!new File(pathToUploads).exists())
			{
				new File(pathToUploads).mkdir();
			}

			String time = Long.toString((new Date()).getTime());
			String orgName = time + file.getOriginalFilename();
			String filePath = pathToUploads + File.separator + orgName;
			imageUrl = "img" + File.separator + username + File.separator + orgName;
			File dest = new File(filePath);
			file.transferTo(dest);

			return imageUrl;
		}
		else
		{
			return requestImageUrl;
		}

	}

	public byte[] getImageFromDisk(String username, String imageUrl) throws IOException
	{

		File file = new File(uploadsDir + File.separator + username + File.separator + imageUrl);

		if (file.exists())
		{
			return Files.readAllBytes(file.toPath());
		}

		return null;
	}
}
