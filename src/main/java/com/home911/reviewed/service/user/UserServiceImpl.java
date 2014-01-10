package com.home911.reviewed.service.user;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.home911.reviewed.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getCanonicalName());

    public UserServiceImpl() {
        ObjectifyService.register(User.class);
    }

    public User getUser(String username) {
        LOGGER.info("Getting user for username:[" + username + "]");
        User user = ofy().load().type(User.class).id(username).now();
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    public void saveUser(User user) {
        LOGGER.info("Saving user: " + user);
        ofy().save().entity(user).now();
    }
}
