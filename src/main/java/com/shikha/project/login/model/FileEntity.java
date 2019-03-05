package com.shikha.project.login.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class FileEntity {

	  	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private Long id;
	    @Column(name = "filename")
	    private String filename;
	    
	    @Lob
	    @Column(name = "content")
	    private byte[] content;
	    
	    @Column(name="creation_date")
	    private Date creationDate;
	    
	    @Column(name="update_date")
	    private Date updateDate;
	    
	    @OneToMany(mappedBy = "fileEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	    private List<FileHistoryEntity> fileHistory;
	  
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
		 * @return the filename
		 */
		public String getFilename() {
			return filename;
		}

		/**
		 * @param filename the filename to set
		 */
		public void setFilename(String filename) {
			this.filename = filename;
		}

		/**
		 * @return the content
		 */
		public byte[] getContent() {
			return content;
		}

		/**
		 * @param content the content to set
		 */
		public void setContent(byte[] content) {
			this.content = content;
		}

		/**
		 * @return the creationDate
		 */
		public Date getCreationDate() {
			return creationDate;
		}

		/**
		 * @param creationDate the creationDate to set
		 */
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}

		/**
		 * @return the updateDate
		 */
		public Date getUpdateDate() {
			return updateDate;
		}

		/**
		 * @param updateDate the updateDate to set
		 */
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}

		/**
		 * @return the fileHistory
		 */
		public List<FileHistoryEntity> getFileHistory() {
			return fileHistory;
		}

		/**
		 * @param fileHistory the fileHistory to set
		 */
		public void setFileHistory(List<FileHistoryEntity> fileHistory) {
			this.fileHistory = fileHistory;
		}

	
	    
	
}
