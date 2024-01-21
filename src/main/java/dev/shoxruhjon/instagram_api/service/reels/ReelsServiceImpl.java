package dev.shoxruhjon.instagram_api.service.reels;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.ReelsEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.ReelsRepository;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsServiceImpl implements ReelsService {
    private final ReelsRepository reelsRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public ReelsEntity createReel(ReelsEntity reels, UserResponse userResponse) {
        UserEntity user = modelMapper.map(userResponse, UserEntity.class);
        reels.setUser(user);
        return reelsRepository.save(reels);
    }

    @Override
    public List<ReelsEntity> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<ReelsEntity> findUserReel(Integer userId) {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
