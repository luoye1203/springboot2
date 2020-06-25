package com.lht.cDao;

import com.lht.dModel.RecordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDao {
    public RecordModel getData(@Param("user_id")String user_id);
}
