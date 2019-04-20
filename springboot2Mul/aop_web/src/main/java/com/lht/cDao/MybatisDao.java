package com.lht.cDao;


import com.lht.dAnnotation.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DataSource("slave2")
public interface MybatisDao {
    public List<Map<String,String>> getLog();

    @DataSource("slave1")
    public List<Map<String,String>> getDic();
}
