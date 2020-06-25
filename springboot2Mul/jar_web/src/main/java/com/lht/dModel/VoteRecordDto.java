package com.lht.dModel;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "vote_record")
public class VoteRecordDto {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name="vote_id")
    private String voteId;

    @Column(name="group_id")
    private String groupId;

    @Column(name="create_time")
    private String createTime;




}
