package com.alfresco.support.alfrescodb.model;

public class Content {
	private int occurrences;
	private int docsNumber;
    private String mimeType;
    private float diskSpace;

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType(){
		return this.mimeType;
	}

	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public int getOccurrences(){
		return this.occurrences;
	}

	public void setDocsNumber(int docsNumber) {
		this.docsNumber = docsNumber;
	}

	public int getDocsNumber(){
		return this.docsNumber;
	}

	public void setDiskSpace(int diskSpace) {
		this.diskSpace = diskSpace;
	}

	public float getDiskSpace() {
		return this.diskSpace;
	}

	public String printContentByMimemtype() {
		return String.format("\n%s, %s, %s", mimeType, occurrences, diskSpace);
	}

	public String printContentSize() {
		return String.format("\n%s, %s", docsNumber, diskSpace);
	}
}
