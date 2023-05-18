package com.h2sm.smarthomebackend.auth.configuration;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class AuthChannelInterceptor implements ChannelInterceptor {
    private static final String USERNAME_HEADER = "username";
    private static final String PASSWORD_HEADER = "password";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        var x = message.getHeaders();

        // Instantiate an object for retrieving the STOMP headers
//        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//        // Check that the object is not null
//        assert accessor != null;
//        // If the frame is a CONNECT frame
//        if(accessor.getCommand() == StompCommand.CONNECT){
////            // retrieve the username from the headers
////            final String username = accessor.getFirstNativeHeader(USERNAME_HEADER);
////            // retrieve the password from the headers
////            final String password = accessor.getFirstNativeHeader(PASSWORD_HEADER);
////            System.out.println("username + password " + username + " " + password);
////            // authenticate the user and if that's successful add their user information to the headers.
////            //UsernamePasswordAuthenticationToken user = service.getAuthenticatedOrFail(username, password);
////            //accessor.setUser(user);
//
//        }

        return message;

    }

//    @Override
//    public boolean preReceive(MessageChannel channel) {
//        return true;
//    }
}
