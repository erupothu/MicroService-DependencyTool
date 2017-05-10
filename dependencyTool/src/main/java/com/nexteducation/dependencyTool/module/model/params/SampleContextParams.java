package com.nexteducation.dependencyTool.module.model.params;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class SampleContextParams {

	@QueryParam("uuid")
	String uuid;
	
	@PathParam("pathUuid")
	String pathUuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPathUuid() {
		return pathUuid;
	}

	public void setPathUuid(String pathUuid) {
		this.pathUuid = pathUuid;
	}

	@Override
	public String toString() {
		return "SampleContextParams [" + (uuid != null ? "uuid=" + uuid + ", " : "")
				+ (pathUuid != null ? "pathUuid=" + pathUuid : "") + "]";
	}
	
}
