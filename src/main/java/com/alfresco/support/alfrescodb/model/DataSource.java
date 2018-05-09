package com.alfresco.support.alfrescodb.model;

public class DataSource {
	private String tenant;
	private String name;
	private String dataSourceConfig;

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

	public String getDataSourceConfig() {
		return dataSourceConfig;
	}

	public void setDataSourceConfig(String dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
	}

	public String printDataSources() {
		return String.format("\n%s, %s, %s", name, tenant, dataSourceConfig);
	}
}
