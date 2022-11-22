package com.oslyk.cashflow

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class CashFlowApplicationTests(
        @Autowired val mockMvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun `Assert Deals has Transaction_1 as the first item`() {
        mockMvc.get("/deal")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") { value(1) }
                    jsonPath("$[0].name") { value("Transaction_1") }
                }
    }

    @Test
    fun `Test knowledge`() {
        println(max(10, 4))
    }

    fun max(a: Int, b:Int): Int = if (a > b) a else b

}
