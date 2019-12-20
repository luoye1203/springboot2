package com.lht.myquartz.cDao;

import com.lht.myquartz.dModel.TaskModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao {
    public String getTaskId();

    public Integer saveTask(TaskModel taskModel);
    public void updateTsk(TaskModel taskModel);

}
