package com.lht.bService;


import com.lht.cDao.CacheDao;
import com.lht.dModel.RecordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacherService {

    @Autowired
    private CacheDao cacheDao;


    public RecordModel getData(String user_id){

        return this.cacheDao.getData(user_id);
    }

}
