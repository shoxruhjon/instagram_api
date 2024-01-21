package dev.shoxruhjon.instagram_api.service.reels;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.ReelsEntity;

import java.util.List;

public interface ReelsService {
    public ReelsEntity createReel(ReelsEntity reels, UserResponse userResponse);
    public List<ReelsEntity> findAllReels();
    public List<ReelsEntity> findUserReel(Integer userId);
}
