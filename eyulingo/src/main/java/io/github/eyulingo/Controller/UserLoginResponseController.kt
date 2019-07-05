package io.github.eyulingo.Controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
open class LoginFailureController {
    @RequestMapping(value = ["/failure"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun loginFailure(): String {
        return "{\"status\": \"failed_to_login\"}"
    }
}

@RestController
open class LoginSuccessController {
    @RequestMapping(value = ["/ok"], method = [RequestMethod.GET], produces = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun loginSuccess(): String {
        return "{\"status\": \"login_successfully\"}"
    }
}