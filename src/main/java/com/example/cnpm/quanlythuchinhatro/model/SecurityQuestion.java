package com.example.cnpm.quanlythuchinhatro.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SecurityQuestion")
public class SecurityQuestion {
    //id
    //user_id
    //question
    //answer

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_id")
    private Integer userId;
    @Column(name="room_id")
    private Integer roomId;
    @Column(name="status")
    private Integer status;

    public SecurityQuestion() {
    }
    public SecurityQuestion(Integer userId, Integer roomId, Integer status) {
        this.userId = userId;
        this.roomId = roomId;
        this.status = status;
    }    
}