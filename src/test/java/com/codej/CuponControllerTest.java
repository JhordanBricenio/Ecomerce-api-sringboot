package com.codej;

import com.codej.model.Cupon;
import com.codej.service.ICuponService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CuponControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICuponService cuponService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void guardarCuponTest() throws Exception {
        // TODO
        Cupon cupon= Cupon.builder()
                .tipo("Cupon de descuento")
                .codigo("CUPON-123")
                .valor(10.0)
                .limite(100.0)
                .build();

        given(cuponService.save(any(Cupon.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions result = mockMvc.perform(post("/cupones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cupon)));

        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.tipo").value("Cupon de descuento"))
                .andExpect(jsonPath("$.codigo").value("CUPON-123"))
                .andExpect(jsonPath("$.valor").value(10.0))
                .andExpect(jsonPath("$.limite").value(100.0));

    }

}
