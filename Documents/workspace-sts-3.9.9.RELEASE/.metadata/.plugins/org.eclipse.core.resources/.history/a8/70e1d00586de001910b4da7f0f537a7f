package com.bridgelab.fundunotes.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenGeneration {
	private final long EXPIRATION_TIME = 5000;
	private final String secret = "shriniwas";

	public String generateToken(String email) {
		return JWT.create().withClaim("email", email).sign(Algorithm.HMAC512(secret));
	}

	public String parseToken(String token) {
		return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("email").asString();

	}

}
