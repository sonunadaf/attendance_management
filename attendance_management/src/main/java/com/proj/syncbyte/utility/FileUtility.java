package com.proj.syncbyte.utility;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtility {

	private static final Logger log = Logger.getLogger(FileUtility.class);

	public FileUtility() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isImageFile(MultipartFile file) {
		String mimeType = file.getContentType();
		if (mimeType.split("/")[0].equals("image")) {
			return true;
		}
		return false;
	}

	public static String saveFile(MultipartFile file) {
		try {
			String path = getFilePath(file.getOriginalFilename());
			File tempFile = new File(path);
			file.transferTo(tempFile);
			return path;
		} catch (IllegalStateException e) {
			log.error(e.getMessage());
			return "";
		} catch (IOException e) {
			log.error(e.getMessage());
			return "";
		}

	}

	public static String getFilePath(String fileName) {
		String path = System.getProperty("user.home");
		String uuid = UUID.randomUUID().toString();
		path = path + File.separator + "syncbyte" + File.separator + "finger_print";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
			log.info("Created Excel file Directory :");
		}
		path = path + File.separator + uuid + "_" + fileName;
		return path;

	}

	public static String getExcelFilePath(String fileName) {
		String path = System.getProperty("user.home");
		String uuid = UUID.randomUUID().toString();
		path = path + File.separator + "syncbyte" + File.separator + "excel";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
			log.info("Created Excel file Directory :");
		}
		path = path + File.separator + uuid + "_" + fileName;

		return path;

	}

}
