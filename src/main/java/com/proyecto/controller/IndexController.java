package com.proyecto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class IndexController {
	@PostMapping(value = "demo")
	public String welcome() {
		return "Welcome from secure endpoint";
	}
}
