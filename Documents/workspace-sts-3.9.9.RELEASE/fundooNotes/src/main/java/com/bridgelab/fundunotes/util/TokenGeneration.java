package com.bridgelab.fundunotes.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Component
public class TokenGeneration {
	private final String secret = "shriniwas";

	public String generateToken(int id) {
		return JWT.create().withClaim("userid",id).sign(Algorithm.HMAC512(secret));
	}

	public int parseToken(String token) {
	  System.out.println(JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("userid").asInt());
		int userid= JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("userid").asInt();
      return userid;
	}
}