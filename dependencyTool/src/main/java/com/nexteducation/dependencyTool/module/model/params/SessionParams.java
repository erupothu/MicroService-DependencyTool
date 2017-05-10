package com.nexteducation.dependencyTool.module.model.params;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import io.swagger.annotations.ApiParam;

/**
 * Fixed Fields
 * 
 * @author ashishpratap
 *
 */

public class SessionParams {

	@NotNull(message = "Branch should not be null")
	@QueryParam("lbid")
	@ApiParam(value = "Session Parameter - Branch Id")
	private Long branchId;

	@NotNull(message = "Academic Session should not be null")
	@QueryParam("lasid")
	@ApiParam(value = "Session Parameter - Academic Session Id")
	private Long academicSessionId;

	// student or parent or staff id

	@NotNull(message = "User should not be null")
	@QueryParam("luid")
	@ApiParam(value = "Session Parameter - User Id")
	private Long userId;

	// user profile id

	@QueryParam("lupid")
	@ApiParam(value = "Session Parameter - Reference Id")
	private Long userProfileId;

	@QueryParam("ptype")
	@ApiParam(value = "Session Parameter - Profile Type")
	private String profileType;

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getAcademicSessionId() {
		return academicSessionId;
	}

	public void setAcademicSessionId(Long academicSessionId) {
		this.academicSessionId = academicSessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SessionParams [branchId=" + branchId + ", academicSessionId=" + academicSessionId + ", userId=" + userId
				+ ", userProfileId=" + userProfileId + ", profileType=" + profileType + "]";
	}

}