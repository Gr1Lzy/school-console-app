package com.github.schoolconsoleapp;

import com.github.schoolconsoleapp.entity.Group;
import com.github.schoolconsoleapp.repository.GroupRepository;
import com.github.schoolconsoleapp.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SchoolConsoleTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByStudentCountLessThanEqual() {
        // Arrange
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Group A");

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Group B");

        List<Group> groups = Arrays.asList(group1, group2);

        when(groupRepository.findByStudentCountLessThanEqual(anyInt())).thenReturn(groups);

        // Act
        List<Group> result = groupService.findByStudentCountLessThanEqual(50);

        // Assert
        assertEquals(2, result.size());
        verify(groupRepository, times(1)).findByStudentCountLessThanEqual(anyInt());
    }

    @Test
    void testFindAll() {
        // Arrange
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Group A");

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Group B");

        List<Group> groups = Arrays.asList(group1, group2);

        when(groupRepository.findAll()).thenReturn(groups);

        // Act
        List<Group> result = groupService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void testGetByName() {
        // Arrange
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("Group A");

        when(groupRepository.findByGroupName("Group A")).thenReturn(group);

        // Act
        Group result = groupService.getByName("Group A");

        // Assert
        assertEquals("Group A", result.getGroupName());
        verify(groupRepository, times(1)).findByGroupName("Group A");
    }
}
