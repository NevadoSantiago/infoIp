package com.meli.infoIp.controller;

import com.meli.infoIp.model.InfoIpResponse;
import com.meli.infoIp.services.interfaces.InfoIpInterface;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/infoIp")
@Controller
public class InfoIpController {

    @Autowired
    InfoIpInterface service;

   /* @GetMapping()
    public ResponseEntity<?> getIpInfo(){
        return new ResponseEntity<>(service.getInfoByIp(), HttpStatus.ACCEPTED);
    }*/
    @GetMapping()
    public String home(){
        return "home";
    }

    @PostMapping()
    public String infoIp(
        Model model,
        @RequestParam(value = "ipAddress") String ipAddress) throws JSONException {
        InfoIpResponse info = service.getInfoByIpAddress(ipAddress);
        model.addAttribute("infoIp",info);
        return "home";
    }
}
