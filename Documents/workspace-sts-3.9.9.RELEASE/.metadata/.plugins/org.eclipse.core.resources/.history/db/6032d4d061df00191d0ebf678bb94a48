package com.bridgelab.fundunotes.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.bridgelab.fundunotes.util.TokenGeneration;

@Configuration
public class FunduConfiguration {
      @Bean
      public ModelMapper setModelMapper() {
    	  ModelMapper modelmapper=new ModelMapper(); 
    	  modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    	  return modelmapper;
      }
      @Bean
      public BCrypt encoder() {
    	  return new BCrypt();
      }
      @Bean
      public TokenGeneration token() {
    	  return new TokenGeneration();
      }
}
