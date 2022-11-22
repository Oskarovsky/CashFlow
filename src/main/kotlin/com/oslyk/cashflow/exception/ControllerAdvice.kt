package com.oslyk.cashflow.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(DealNotFoundException::class)
    fun handleDealNotFoundException(
            servletRequest: HttpServletRequest,
            exception: Exception
    ): ResponseEntity<String> {
        return ResponseEntity("Deal not found", HttpStatus.NOT_FOUND)
    }

}