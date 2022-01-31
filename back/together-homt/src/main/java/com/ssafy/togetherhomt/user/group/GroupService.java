package com.ssafy.togetherhomt.user.group;

import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String create(GroupDto groupDto) {

        Group group = Group.builder()
                .main_category(groupDto.getMain_category())
                .middle_category(groupDto.getMiddle_category())
                .sub_category(groupDto.getSub_category())
                .name(groupDto.getName())
                .build();

        groupRepository.save(group);

        return "success";
    }

    @Transactional
    public String setting(GroupSettingDto groupSettingDto) {

        User user = userRepository.findByEmail(groupSettingDto.getEmail());
        Group group = groupRepository.findByName(groupSettingDto.getGroupName());

        user.setGroup(group);

        return "success";
    }

    public GroupDto check(String email) {

        User user = userRepository.findByEmail(email);

        Group group = user.getGroup();

        GroupDto groupDto = new GroupDto();
        groupDto.setMain_category(group.getMain_category());
        groupDto.setMiddle_category(group.getMiddle_category());
        groupDto.setSub_category(group.getSub_category());
        groupDto.setName(group.getName());

        return groupDto;
    }
}
