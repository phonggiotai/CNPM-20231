package com.example.cnpm.quanlythuchinhatro.repository;

import com.example.cnpm.quanlythuchinhatro.model.MemberOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberOfRoomRepository extends JpaRepository<MemberOfRoom, Integer> {
    @Query("SELECT u.id AS id, u.name AS fullName, u.phoneNumber AS phoneNumber, u.bankName AS bankName, u.bankAccountNumber AS bankNumber, u.avatarUrl AS avatarUrl FROM MemberOfRoom mr JOIN User u ON mr.userId = u.id WHERE mr.roomId =:roomId")
    List<Map<String, Object>> getAllMemberOfRoom(Integer roomId);

    @Query(value = """
	SELECT count(distinct user_id)
	FROM member_of_room
	where room_id = : roomId
	""", nativeQuery = true)
    Integer coutnUserInRoom(Integer roomId);

    @Query("SELECT mor.userId FROM MemberOfRoom mor WHERE mor.roomId = :roomId")
    List<Integer> findUserIdsByRoomId(@Param("roomId") Integer roomId);
}