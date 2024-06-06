package com.issue.service;

import com.issue.dto.IssueDto;
import com.issue.entity.IssueDetailsEntity;

public interface IssueListServiceInterface {

	IssueDetailsEntity createIssue(IssueDto issues);
}
