package com.shikha.project.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="file_history")
public class FileHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name="uploadedby")
	private String uploadedby;
	
	@Column(name="uploadtype")
	private String uploadtype;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "fileid", foreignKey = @ForeignKey(name = "FK_fileid"))
	private FileEntity fileEntity;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the uploadedby
	 */
	public String getUploadedby() {
		return uploadedby;
	}

	/**
	 * @param uploadedby the uploadedby to set
	 */
	public void setUploadedby(String uploadedby) {
		this.uploadedby = uploadedby;
	}

	/**
	 * @return the fileEntity
	 */
	public FileEntity getFileEntity() {
		return fileEntity;
	}

	/**
	 * @param fileEntity the fileEntity to set
	 */
	public void setFileEntity(FileEntity fileEntity) {
		this.fileEntity = fileEntity;
	}

	/**
	 * @return the uploadtype
	 */
	public String getUploadtype() {
		return uploadtype;
	}

	/**
	 * @param uploadtype the uploadtype to set
	 */
	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}
	
	
}
