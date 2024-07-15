package com.quantweb.springserver.auth.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.quantweb.springserver.auth.entity.TokenRepository;
import com.quantweb.springserver.auth.support.JwtTokenProvider;

@RequiredArgsConstructor
@Configuration
public class AuthConfiguration implements WebMvcConfigurer {

  private final JwtTokenProvider jwtTokenProvider;
  private final TokenRepository tokenRepository;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthInterceptor(jwtTokenProvider))
        .addPathPatterns("/api/**");

    registry.addInterceptor(new RefreshTokenAuthInterceptor(jwtTokenProvider, tokenRepository))
        .addPathPatterns("/api/auth/oauth2/token/refresh");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/*")
        .addResourceLocations("classpath:/static/");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new TokenResolver(jwtTokenProvider));
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/*")
        .allowedOrigins("http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("")
        .allowCredentials(true);
  }
}
