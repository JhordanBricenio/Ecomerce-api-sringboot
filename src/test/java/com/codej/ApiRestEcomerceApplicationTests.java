package com.codej;

import com.codej.model.Cupon;
import com.codej.repository.ICuponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ApiRestEcomerceApplicationTests {

	@Autowired
	private ICuponRepository cuponRepository;



	@Test
	void contextLoads() {

	}


}
