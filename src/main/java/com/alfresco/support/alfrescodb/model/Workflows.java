package com.alfresco.support.alfrescodb.model;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;




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
	private String age;
	private String deleteReason;

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
	
	
	
	
	
	
	
	
	
	
	//Cody Addition
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime(){
		return this.startTime;
	}
	
	public void setAge(String startTime) {
		
		try{
		
			LocalDateTime now = LocalDateTime.now();
		
			//Source: https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-java/29812532
			String CurrentDate=  now.toString();
			String StartDate=  this.startTime;
			
			//cut the time off of today's date
			String arrayCurrent[]=CurrentDate.split("T");
			//cut the time off of the start date
			String arrayStart[]=StartDate.split(" ");

			Date date1;
			Date date2;

			SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");

			//Setting dates
			date1 = dates.parse(arrayCurrent[0].replace("-","/"));
			date2 = dates.parse(arrayStart[0].replace("-","/"));

			//Comparing dates
			long difference = Math.abs(date1.getTime() - date2.getTime());
			long differenceDates = difference / (24 * 60 * 60 * 1000);

			//Convert long to String
			String dayDifference = Long.toString(differenceDates);
		
		
		
			this.age = dayDifference;
		}
		catch (Exception exception) {
			this.age=exception.toString();
		}
		
		

	}

	public String getAge(){
		
		try{
		
			LocalDateTime now = LocalDateTime.now();
		
			//Source: https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-java/29812532
			String CurrentDate=  now.toString();
			String StartDate=  this.startTime;
			
			//cut the time off of today's date
			String arrayCurrent[]=CurrentDate.split("T");
			//cut the time off of the start date
			String arrayStart[]=StartDate.split(" ");

			Date date1;
			Date date2;

			SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");

			//Setting dates
			date1 = dates.parse(arrayCurrent[0].replace("-","/"));
			date2 = dates.parse(arrayStart[0].replace("-","/"));

			//Comparing dates
			long difference = Math.abs(date1.getTime() - date2.getTime());
			long differenceDates = difference / (24 * 60 * 60 * 1000);

			//Convert long to String
			String dayDifference = Long.toString(differenceDates);
		
		
		
			this.age = dayDifference;
		}
		catch (Exception exception) {
			this.age=exception.toString();
		}
		
		return this.age;
	}
	
	public String printLongRunningProcesses() {
		return String.format("\n%s, %s, %s", tenantName, procDefName, startTime);
	}
	
	
	
	
	
	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	} 
	
	public String printCancelledProcesses() {
		return String.format("\n%s, %s, %s", tenantName, deleteReason, occurrences);
	}
	
	
	
	
	
	public String printCompletedProcesses() {
		return String.format("\n%s, %s", tenantName, occurrences);
	}
	
	
	
	
	
	public String printOpenProcessesCount() {
		return String.format("\n%s, %s", tenantName, occurrences);
	}
	//End Cody Addition
}