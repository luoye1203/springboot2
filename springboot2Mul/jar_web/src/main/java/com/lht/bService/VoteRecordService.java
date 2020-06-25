package com.lht.bService;

import com.lht.mapper.VoteRecordMapper;
import com.lht.dModel.VoteRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteRecordService {
    @Autowired
    private VoteRecordMapper voteRecordMapper;

    public  List<VoteRecordDto>  getAll(){

        return this.voteRecordMapper.selectAll();
    }

    public int  deleteById(){
        int i= this.voteRecordMapper.deleteByPrimaryKey(1);

        return i;

    }
}
