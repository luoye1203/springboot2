package com.lht.cDao;

import com.lht.dModel.DmModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DaMengDataDao {

    List<Map<String,String>> getData();

    void addData(DmModel dmModel);
}
