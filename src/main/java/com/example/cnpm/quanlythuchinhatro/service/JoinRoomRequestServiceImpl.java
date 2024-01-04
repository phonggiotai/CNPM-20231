package com.example.cnpm.quanlythuchinhatro.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.cnpm.quanlythuchinhatro.dto.JoinRoomRequestDto;
import com.example.cnpm.quanlythuchinhatro.repository.JoinRoomRequestRepository;
import com.example.cnpm.quanlythuchinhatro.model.JoinRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinRoomRequestServiceImpl implements JoinRoomRequestService{

    @Autowired
    private JoinRoomRequestRepository joinRoomRequestRepository;
    public JoinRoomRequestServiceImpl(JoinRoomRequestRepository joinRoomRequestRepository) {
        this.joinRoomRequestRepository = joinRoomRequestRepository;
    }

    @Override
    public List<Object[]> getJRRForAdmin(Integer roomId) {
        return joinRoomRequestRepository.getJRRForAdmin(roomId);
    }
    @Override
    public Boolean approval(Integer roomId, Integer userId, Boolean status) {
        JoinRoomRequest jrr= joinRoomRequestRepository.findByUserIdAndRoomId(userId, roomId);
        if(jrr == null) return false;
        else{
            if(status == true){
                jrr.setStatus(2);
                joinRoomRequestRepository.save(jrr);
                return true;
            }
            else{
                jrr.setStatus(0);
                joinRoomRequestRepository.save(jrr);
                return true;
            }
        }
    }

    public List<JoinRoomRequestDto> getAllJoinRoomRequests() {
        List<JoinRoomRequest> requests = joinRoomRequestRepository.findAll();
        return requests.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JoinRoomRequestDto convertToDto(JoinRoomRequest request) {
        JoinRoomRequestDto dto = new JoinRoomRequestDto();
        dto.setRoomName(request.getRoom().getRoomName()); // Giả sử có mối quan hệ giữa JoinRoomRequest và Room
        dto.setRequestDate(request.getRequestDate());
        dto.setStatus(request.getStatus());
        dto.setRoomId(request.getRoomId());
        return dto;
    }
}
