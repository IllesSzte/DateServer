package com.date.date.controller;

import com.date.date.exceptions.UserExceptions;
import com.date.date.model.Request;
import com.date.date.model.User;
import com.date.date.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/request")
public class RequestController {
    @Autowired
    RequestService requestService;
    @PostMapping(path = "/send-request")
    public Request sendRequest(@RequestParam Integer requesterId, @RequestParam Integer requestedId) throws UserExceptions {
        return requestService.sendRequest(requesterId,requestedId);
    }
    @GetMapping(path = "/get-requester")
    public User getRequester(@RequestParam int id) throws UserExceptions {
        return requestService.listRequest(id);
    }
    @PutMapping(path = "/accept-request")
    public void acceptRequest(@RequestParam int requestedUserId,@RequestParam int requesterUserId){
        requestService.acceptRequest(requestedUserId,requesterUserId);
    }
}
