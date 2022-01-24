package com.ssafy.togetherhomt.common;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public boolean isLoginUser(User user) {
        User sUser = ((PrincipalDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();

        return user.getEmail().equals(sUser.getEmail());
    }

}
