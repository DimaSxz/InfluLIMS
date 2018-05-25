package com.springboot.influlims.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class FileStorageService {

	@Autowired
	private ServletContext servletContext;

	@Value("${upload.extraction.location}")
	private String extractionPath;

	@Value("${upload.pcr.protocol.location}")
	private String pcrProtocolPath;

	@Value("${upload.pcr.report.location}")
	private String pcrReportPath;

	public MultipartFile getExtractionFile(String fileName) {
		return getFile(extractionPath, fileName);
	}

	public MultipartFile getPcrProtocolFile(String fileName) {
		return getFile(pcrProtocolPath, fileName);
	}

	public MultipartFile getPcrReportFile(String fileName) {
		return getFile(pcrReportPath, fileName);
	}

	private MultipartFile getFile(String basePath, String fileName) {
		try {
			String path = servletContext.getRealPath(basePath);
			File file = new File(path + fileName);
			if (file.exists()){
				Path filePath = file.toPath();
				return new MockMultipartFile(fileName, null, Files.probeContentType(filePath), Files.readAllBytes(filePath));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveExtractionFile(MultipartFile file) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss");
		String fileName = dateFormat.format(System.currentTimeMillis()) + "EXT." + FilenameUtils.getExtension(file.getOriginalFilename());
		saveFile(file, extractionPath, fileName);
		return fileName;
	}

	public String savePcrProtocolFile(MultipartFile file) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss");
		String fileName = dateFormat.format(System.currentTimeMillis()) + "EXT." + FilenameUtils.getExtension(file.getOriginalFilename());
		saveFile(file, pcrProtocolPath, fileName);
		return fileName;
	}

	public String savePcrReportFile(MultipartFile file) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss");
		String fileName = dateFormat.format(System.currentTimeMillis()) + "EXT." + FilenameUtils.getExtension(file.getOriginalFilename());
		saveFile(file, pcrReportPath, fileName);
		return fileName;
	}

	private void saveFile(MultipartFile file, String basePath, String fileName) {
		try {
			String path = servletContext.getRealPath(basePath);
			File dirPath = new File(path);
			if(!dirPath.exists()) {
				if(!dirPath.mkdirs()) throw new IOException("mkdir returns error, can't create dir: " + path);
			}
			file.transferTo(new File(path + fileName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
