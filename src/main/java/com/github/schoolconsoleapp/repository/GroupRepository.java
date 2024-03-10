package com.github.schoolconsoleapp.repository;

import com.github.schoolconsoleapp.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM Group g WHERE g.id IN (SELECT s.group.id FROM Student s WHERE s.id <= :studentCount)")
    List<Group> findByStudentCountLessThanEqual(int studentCount);

    @Query("SELECT g FROM Group g WHERE g.groupName = :groupName")
    Group findByGroupName(String groupName);
}