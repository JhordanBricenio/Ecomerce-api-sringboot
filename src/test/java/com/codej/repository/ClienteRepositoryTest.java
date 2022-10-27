package com.codej.repository;
import static org.assertj.core.api.Assertions.assertThat;

import com.codej.model.Admin;
import com.codej.model.Cupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@DataJpaTest
public class ClienteRepositoryTest {

        @Autowired
        private ICuponRepository cuponRepository;

        @Autowired
        BCryptPasswordEncoder crypt;


        @Test
        public void testCreateCupon() {
            ;
        }




}
