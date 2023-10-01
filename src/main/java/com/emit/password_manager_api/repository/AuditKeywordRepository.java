package com.emit.password_manager_api.repository;

import com.emit.password_manager_api.model.*;

import java.util.List;

public interface AuditKeywordRepository extends BaseRepository<AuditKeyword, Long>{
	List<AuditKeyword> findByKeyword(Keyword keyword);
}
