package com.alfresco.support.alfrescodb.model;

public class UsersAndGroups {
    private int occurrences;
	private String capability;
	private String groupName;
    private String user;
    private String tenantName;

	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public int getOccurrences(){
		return this.occurrences;
	}

	public void setGroup(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName(){
		return this.groupName;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser(){
		return this.user;
	}

	public void setCapability(String capability) {
		this.capability = capability;
	}

	public String getCapability(){
		return this.capability;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String printGroups() {
		return String.format("\n%s, %s, %s", tenantName, groupName, occurrences);
	}

	public String printCapabilities() {
		return String.format("\n%s, %s, %s", tenantName, groupName, capability);
	}

}
