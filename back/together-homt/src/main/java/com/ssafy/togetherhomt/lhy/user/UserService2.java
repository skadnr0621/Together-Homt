package com.ssafy.togetherhomt.lhy.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService2 {

    private CommonService commonService;


    public UserDto2 getUser() {
        User user = commonService.getLoginUser();
        if (user == null)
            return null;
        return new UserDto2(user, true);
    }

}
