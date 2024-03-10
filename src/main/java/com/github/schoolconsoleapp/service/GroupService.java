package com.github.schoolconsoleapp.service;

import com.github.schoolconsoleapp.entity.Group;
import com.github.schoolconsoleapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> findByStudentCountLessThanEqual(int studentCount) {
        return groupRepository.findByStudentCountLessThanEqual(studentCount);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group getByName(String name) {
        return groupRepository.findByGroupName(name);
    }
}