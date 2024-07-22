package com.quantweb.springserver.user_image.service;

import com.quantweb.springserver.user_image.entity.UserImage;
import com.quantweb.springserver.user_image.entity.UserImageRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserImageService {

    private final UserImageRepository userImageRepository;

    @Transactional
    public UserImage saveUserImage(String imageUrl) {
        UserImage userImage = new UserImage(imageUrl, LocalDate.now());

        userImageRepository.save(userImage);

        return userImage;
    }

}
