package com.ssafy.togetherhomt.lhy.user;

import com.ssafy.togetherhomt.common.CommonService;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private CommonService commonService;


    public UserDto getUser() {
        User user = commonService.getLoginUser();
        if (user == null)
            return null;
        return new UserDto(user, true);
    }

}
