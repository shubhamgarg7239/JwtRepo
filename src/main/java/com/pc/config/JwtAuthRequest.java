package com.pc.config;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String userName ; //userName = email ;
	private String password ;
}
