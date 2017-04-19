package by.samsolutions.imgcloud.controller.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Locale;

import by.samsolutions.imgcloud.dto.PostDto;
import com.dropbox.core.*;
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

	@Value("${accessToken}")
	private String accessToken;

	public String saveImageToDisk(final MultipartFile file, final String requestImageUrl)
					throws TooLargeFileException, IOException, DbxException
	{

		if (file != null && !file.isEmpty())
		{
			if (file.getSize() > maxFileSize)
			{
				throw new TooLargeFileException();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();

            DbxRequestConfig reqConfig = new DbxRequestConfig("Social_Graph/1.0",
                    Locale.getDefault().toString());

            DbxClient client = new DbxClient(reqConfig, accessToken);

            String time = Long.toString((new Date()).getTime());
            String fileName = "/" + username + "/" + time + file.getOriginalFilename();

            try {
                client.uploadFile(fileName,
                        DbxWriteMode.add(), file.getSize(), file.getInputStream());
            } finally {
                file.getInputStream().close();
            }

			return "img" + fileName;
		}
		else
		{
			return requestImageUrl;
		}

	}

	public byte[] getImageFromDisk(String username, String imageUrl) throws IOException, DbxException
	{
        DbxRequestConfig reqConfig = new DbxRequestConfig("Social_Graph/1.0",
                Locale.getDefault().toString());

        DbxClient client = new DbxClient(reqConfig, accessToken);

        String path = "/" + username + "/" + imageUrl;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        client.getFile(path , null, outputStream);

		return outputStream.toByteArray();
	}
}
