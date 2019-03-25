package com.shikha.project.login.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shikha.project.login.model.FileEntity;
import com.shikha.project.login.model.FileHistoryEntity;
import com.shikha.project.login.model.FileInfo;
import com.shikha.project.login.model.User;
import com.shikha.project.login.repository.FileRepository;

@Service
public class FileUploaderService {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	AsymmetricDecryptionService decryptService;

	public void store(byte[] file, String fileName) throws IOException, EncryptionException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<FileEntity> entityList = fileRepository.findByFilename(fileName);
		
		boolean alreadyUploaded = false;
		FileHistoryEntity ent = new FileHistoryEntity();
		FileEntity fileEntity = new FileEntity();
		List<FileHistoryEntity> fileHistList = new ArrayList<FileHistoryEntity>();
		
		if(!entityList.isEmpty()) {			
		for(FileEntity entity : entityList) {
		
			if (null != entity && decryptService.decrypt(new String(entity.getContent())).equals(decryptService.decrypt(new String(file)))) {
		
			for (FileHistoryEntity fileHistory : entity.getFileHistory()) {
				if (user.getEmail().equals(fileHistory.getUploadedby())) {
					alreadyUploaded = true;
					break;
				}

			}
			if (!alreadyUploaded) {
				
				ent.setUploadedby(user.getEmail());
				ent.setUploadtype("Secondary");
				ent.setFileEntity(entity);
				entity.getFileHistory().add(ent);			
				fileRepository.save(entity);
			} 
		}
		else {
			fileEntity.setContent(file);
			fileEntity.setCreationDate(Calendar.getInstance().getTime());
			fileEntity.setUpdateDate(Calendar.getInstance().getTime());
			fileEntity.setFilename(fileName);
			ent.setUploadedby(user.getEmail());
			ent.setUploadtype("Initial");
			ent.setFileEntity(fileEntity);
			fileHistList.add(ent);
			fileEntity.setFileHistory(fileHistList);
			fileRepository.save(fileEntity);
		}
		
		}
		}
		else {
			fileEntity.setContent(file);
			fileEntity.setCreationDate(Calendar.getInstance().getTime());
			fileEntity.setUpdateDate(Calendar.getInstance().getTime());
			fileEntity.setFilename(fileName);
			ent.setUploadedby(user.getEmail());
			ent.setUploadtype("Initial");
			ent.setFileEntity(fileEntity);
			fileHistList.add(ent);
			fileEntity.setFileHistory(fileHistList);
			fileRepository.save(fileEntity);
		}
		
		

	}
	
	public List<FileInfo> getFiles()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<FileEntity> fileList = fileRepository.findByEmail(user.getEmail());
		
		List<FileInfo> fil = new ArrayList<FileInfo>();
		for(FileEntity entity : fileList)
		{
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(entity.getFilename());
			for(FileHistoryEntity fileHist : entity.getFileHistory())
			{
				fileInfo.setUploadedBy(fileHist.getUploadedby());
				fileInfo.setUploadType(fileHist.getUploadtype());
			}
			fil.add(fileInfo);
		}
		
		return fil;
	}


}
