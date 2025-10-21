package com.rrhh.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrhh.sistema.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
	    origins = "http://localhost:4200",
	    allowCredentials = "true"
	)
public class AuthController {
	private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/test")
    public String test() {
        return "OK";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> request){
    	System.out.println("⚠️ ¡Entró al endpoint /login!"); if (request == null) {
            System.out.println("❌ Request es null");
            return ResponseEntity.badRequest().body("Cuerpo de la petición inválido");
        }
        String email = request.get("email");
        String password = request.get("password");
        
        System.out.println("📧 Email: '" + email + "'");
        System.out.println("🔑 Password: '" + password + "'");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Faltan email o password");
        }
        
        String token = authService.login(email, password);
        if(token != null){
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(401).body("Credencial invalidas");
        }
    }

}
