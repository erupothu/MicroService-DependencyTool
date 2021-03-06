package com.nexteducation.dependencyTool.util;

public class UserRequest {

	private long branchId;
	private long academicSessionId;
	private long currentUserId;

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(final long branchId) {
		this.branchId = branchId;
	}

	public long getAcademicSessionId() {
		return academicSessionId;
	}

	public void setAcademicSessionId(final long academicSessionId) {
		this.academicSessionId = academicSessionId;
	}

	public long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(final long currentUserId) {
		this.currentUserId = currentUserId;
	}
}
