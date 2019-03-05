package com.shikha.project.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shikha.project.login.model.FileEntity;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
	
	FileEntity findByContent(byte[] content);
	
	@Query("SELECT f from FileEntity f JOIN f.fileHistory fh WHERE f.filename= :filename AND fh.uploadedby= :userName")
	FileEntity findByFilenameAndUserName(String filename, String userName);
	
	@Query("SELECT f.filename from FileEntity f JOIN f.fileHistory fh WHERE fh.uploadedby= :email")
	List<FileEntity> findByEmail(String email);
	
	List<FileEntity> findByFilename(String filename);
}
