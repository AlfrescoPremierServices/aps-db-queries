package com.alfresco.support.alfrescodb.model;

public class AlfrescoEndPoint {
	private String tenant;
	private String name;
	private String alfRepoUrl;
	private String alfShareUrl;
	private String version;

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getTenant(){
		return this.tenant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlfRepoUrl() {
		return alfRepoUrl;
	}

	public void setAlfRepoUrl(String alfRepoUrl) {
		this.alfRepoUrl = alfRepoUrl;
	}

	public String getAlfShareUrl() {
		return alfShareUrl;
	}

	public void setAlfShareUrl(String alfShareUrl) {
		this.alfShareUrl = alfShareUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String printAlfrescoEndPoints() {
		return String.format("\n%s, %s, %s, %s, %s", name, tenant, alfRepoUrl, alfShareUrl, version);
	}
}
