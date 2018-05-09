package com.alfresco.support.alfrescodb.model;

public class RestEndPoint {
	private String tenant;
	private String name;
	private String host;
	private String port;
	private String pathUri;

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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPathUri() {
		return pathUri;
	}

	public void setPathUri(String pathUri) {
		this.pathUri = pathUri;
	}

	public String printRestEndPoints() {
		return String.format("\n%s, %s, %s, %s, %s", name, tenant, host, port, pathUri);
	}
}
