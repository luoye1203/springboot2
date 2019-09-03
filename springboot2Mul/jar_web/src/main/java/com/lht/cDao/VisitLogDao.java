package com.lht.cDao;

import com.lht.dModel.VisitLogModel;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitLogDao {
    void insertVistLog(VisitLogModel visitLogModel);
}
