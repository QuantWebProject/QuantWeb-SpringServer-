package com.quantweb.springserver.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quantweb.springserver.auth.entity.TokenRepository;
import com.quantweb.springserver.user.entity.User;
import com.quantweb.springserver.auth.entity.Token;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TokenService {

  private final TokenRepository tokenRepository;

  @Transactional
  public void synchronizeRefreshToken(User user, String refreshToken) {
    tokenRepository.findByUserId(user.getId())
        .ifPresentOrElse(
            token -> token.updateRefreshToken(refreshToken),
            () -> tokenRepository.save(new Token(user, refreshToken))
        );
  }

  @Transactional
  public void deleteByMemberId(Long memberId) {
    tokenRepository.deleteByUserId(memberId);
  }
}
