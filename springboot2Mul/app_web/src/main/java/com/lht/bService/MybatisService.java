package com.lht.bService;

import com.lht.cDao.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MybatisService {

    @Autowired
    private MybatisDao mybatisDao;

    public List<Map<String,String>> getLog(){

        return mybatisDao.getLog();
    }
}
