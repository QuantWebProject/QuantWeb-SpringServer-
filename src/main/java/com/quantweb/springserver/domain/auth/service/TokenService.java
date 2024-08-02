package com.quantweb.springserver.domain.auth.service;

import com.quantweb.springserver.domain.auth.entity.Token;
import com.quantweb.springserver.domain.auth.entity.TokenRepository;
import com.quantweb.springserver.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TokenService {

  private final TokenRepository tokenRepository;

  @Transactional
  public void synchronizeRefreshToken(User user, String refreshToken) {
    tokenRepository
        .findByUserId(user.getId())
        .ifPresentOrElse(
            token -> token.updateRefreshToken(refreshToken),
            () -> tokenRepository.save(new Token(user, refreshToken)));
  }

  @Transactional
  public void deleteByMemberId(Long memberId) {
    tokenRepository.deleteByUserId(memberId);
  }
}
