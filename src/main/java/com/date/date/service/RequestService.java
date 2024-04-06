package com.date.date.service;

import com.date.date.exceptions.UserExceptions;
import com.date.date.model.Request;
import com.date.date.model.User;
import com.date.date.repository.RequestRepository;
import com.date.date.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.date.date.exceptions.ExceptionMesageEnum.USER_NOT_FOUND;

@Service
public class RequestService {
    @Autowired UserService userService;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    UserRepository userRepository;
    public Request sendRequest(int requester, int requested) throws UserExceptions {
        User user1=userService.getUserById(requester);
        User user2=userService.getUserById(requested);

        if(userService.getUserById(requester) !=null &&
        userService.getUserById(requested) != null){
            return  requestRepository.save( new Request(requester, requested));
        }
        throw new UserExceptions(USER_NOT_FOUND);
    }
    public User listRequest(int userId) throws UserExceptions {
        Request request = requestRepository.findRequestByRequested(userId);
       return userRepository.findById(request.getRequester()).orElseThrow(() -> new UserExceptions(USER_NOT_FOUND));
    }
    @Transactional
    public void acceptRequest(int requestedId,int requesterId){
       User requested = userRepository.findUserById(requestedId);
       User requester = userRepository.findUserById(requesterId);
        requested.setPartnerId(requester.getId());
        requester.setPartnerId(requested.getId());
    }
}
