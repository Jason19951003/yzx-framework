package com.yzx.framework.web.support;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class UploadFile {
	
	private File file;
	private String fileName;
	private long rowSize;
	/**
	 * 取得檔案的副檔名
	 * @return 副檔名
	 */
	public String getSrcExtName() {
		if (this.fileName == null)
			return "";
	
		String [] ext = this.fileName.split("\\.");

		if (ext.length == 1)
			return "";
		else
			return ext[ext.length-1];
	}
	
	@Override
	public String toString() {
		return "UploadFile [file=" + file + ", fileName=" + fileName + ", rowSize=" + rowSize + "]";
	}
	
	public void copyFile(File destFile) throws IOException {
		FileUtils.copyFile(this.file, destFile);
	}
	public void copyFileToDirectory(File destDir) throws IOException {
		FileUtils.copyFileToDirectory(this.file, destDir);
	}
	
	public void moveFile(File destFile) throws IOException {		
		FileUtils.moveFile(this.file, destFile);		
	}
	
	public void moveFileToDirectory(File destDir) throws IOException {
		moveFileToDirectory(destDir, true);
	}
	/**
	 * 搬動檔案
	 * @param destDir 目的目錄
	 * @param createDir 是否創建資料夾
	 * @throws IOException
	 */
	public void moveFileToDirectory(File destDir, boolean createDir) throws IOException {
		FileUtils.moveFileToDirectory(this.file, destDir, createDir);
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the rowSize
	 */
	public long getRowSize() {
		return rowSize;
	}
	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(long rowSize) {
		this.rowSize = rowSize;
	}
	
	
}
