package com.lht.cDao;

import com.lht.model.VisitLogModel;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitLogDao {
    void insertVistLog(VisitLogModel visitLogModel);
}
