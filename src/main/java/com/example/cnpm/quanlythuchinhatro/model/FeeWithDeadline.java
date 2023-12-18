package com.example.cnpm.quanlythuchinhatro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "FeeWithDeadline")
public class FeeWithDeadline {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //room_id
    @Column(name = "room_id")
    private Integer roomId;
    //name
    @Column(name = "fee_name")
    private String feeName;
    //deadline
    @Column(name = "deadline")
    private Date deadline;
    //money
    @Column(name = "money")
    private BigDecimal money;
    //status
    @Column(name = "status")
    private Integer status=0;
    //type
    @Column(name = "type")
    private Integer type;
    public FeeWithDeadline() {
    }

    public FeeWithDeadline(Integer roomId, String feeName, Date deadline, BigDecimal money, Integer status, Integer type) {
        this.roomId = roomId;
        this.feeName = feeName;
        this.deadline = deadline;
        this.money = money;
        this.status = status;
        this.type = type;
    }
}
