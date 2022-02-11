package com.ssafy.togetherhomt.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Transactional
    public void create(GroupDto groupDto) {
        Group group = Group.builder()
                .mainCategory(groupDto.getMainCategory())
                .middleCategory(groupDto.getMiddleCategory())
                .subCategory(groupDto.getSubCategory())
                .name(groupDto.getName())
                .build();
        groupRepository.save(group);
    }

    public List<GroupDto> search(Map<String, String> searchOption) {
        List<Group> groups = new ArrayList<>();
        if (searchOption.containsKey("sub"))
            groups = groupRepository.findBySubCategory(searchOption.get("sub"));
        else if (searchOption.containsKey("middle"))
            groups = groupRepository.findByMiddleCategory(searchOption.get("middle"));
        else if (searchOption.containsKey("main"))
            groups = groupRepository.findByMainCategory(searchOption.get("main"));
        groups.size();
        return null;
    }

    public GroupDto search(String name) {
        Group group = groupRepository.findByName(name);
        group.getGroupId();
        return null;
    }

}
