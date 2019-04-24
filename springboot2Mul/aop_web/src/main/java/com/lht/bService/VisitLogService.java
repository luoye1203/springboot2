package com.lht.bService;

import com.lht.cDao.VisitLogDao;
import com.lht.dModel.VisitLogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitLogService {

    @Autowired
    private VisitLogDao visitLogDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertVistLog(VisitLogModel visitLogModel){
        visitLogDao.insertVistLog(visitLogModel);

//        throw new RuntimeException("测试");
    }

}
