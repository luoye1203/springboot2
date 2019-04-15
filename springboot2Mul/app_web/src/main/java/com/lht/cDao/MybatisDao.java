package com.lht.cDao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MybatisDao {
    public List<Map<String,String>> getLog();
}
