package com.markvarga21.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String generateToken(Authentication authentication);
}
