package com.emit.password_manager_api.service.auditKeyword;

import com.emit.password_manager_api.service.*;

import java.util.List;

import com.emit.password_manager_api.model.*;

public interface AuditKeywordService extends BaseService<AuditKeyword, Long> {
	List<AuditKeyword> findByKeyword(Keyword keyword);
}
