package com.emit.password_manager_api.service.auditKeyword;

import com.emit.password_manager_api.service.*;

import java.sql.Date;
import java.util.List;

import com.emit.password_manager_api.model.*;

public interface AuditKeywordService extends BaseService<AuditKeyword, Long> {
	List<AuditKeyword> findByKeyword(Integer pageNumber, Keyword keyword);
	List<AuditKeyword> findAuditKeywordByDate(Integer pageNumber, Date date);
	List<AuditKeyword> globalSearch(Integer pageNumber, String searchParams);
	List<AuditKeyword> decryptAuditKeyword(List<AuditKeyword> auditKeywordList);
}
