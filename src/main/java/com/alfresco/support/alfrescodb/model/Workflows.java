package com.alfresco.support.alfrescodb.model;

public class Workflows {
    private int occurrences;
    private int open;
    private int closed;
    private String taskName;
	private String procDefId;
	private String procDefName;
	private String tenantName;
	private String deployTime;
	private String version;
	
	private String startTime;

	public void setProcDefName(String procDefName) {
		this.procDefName = procDefName;
	}

	public String getProcDefName(){
		return this.procDefName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskName(){
		return this.taskName;
	}
	
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	public String getProcDefId(){
		return this.procDefId;
	}
	
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public int getOccurrences(){
		return this.occurrences;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion(){
		return this.version;
	}

	public void setDeployTime(String deployTime) {
		this.deployTime = deployTime;
	}

	public String getDeployTime(){
		return this.deployTime;
	}

	public String getTenantName() {
		return this.tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	// Printing messages
	public String printProcesses() {
		return String.format("\n%s, %s, %s", tenantName, procDefName, occurrences);
	}

	public String printTasks() {
		return String.format("\n%s, %s, %s, %s", tenantName, procDefName, taskName, occurrences);
	}

	public String printSingleTasks() {
		return String.format("\n%s, %s, %s", tenantName, taskName, occurrences);
	}

	public String printDeployments() {
		return String.format("\n%s, %s, %s, %s", tenantName, procDefName, version, deployTime);
	}
	
	
	
	
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime(){
		return this.startTime;
	}
	
	public String printLongRunningProcesses() {
		return String.format("\n%s, %s, %s", tenantName, procDefName, startTime);
	}
	
	
	
	
	
	
}
