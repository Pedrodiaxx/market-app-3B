package tecdesoftware.markep_app.domain.service;

import tecdesoftware.markep_app.config.JwtUtil;
import tecdesoftware.markep_app.persistence.crud.ClienteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteCrudRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String correo, String contrasena) {
        return clienteRepository.findByCorreoElectronico(correo)
                .filter(cliente -> passwordEncoder.matches(contrasena, cliente.getContrasena()))
                .map(cliente -> jwtUtil.generateToken(cliente.getCorreoElectronico()))
                .orElse(null);
    }
}