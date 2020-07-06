package service;

import dto.GroupDTO;
import jdbc.GroupRepository;
import jdbc.StudentRepository;
import model.Group;
import model.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class GroupService {
    protected Group group;


    public GroupService(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<GroupDTO> getAllGroups() throws SQLException {
        List<GroupDTO> allGroupDto = new ArrayList<>();
        for (Group group : GroupRepository.getAllGroups()) {
            Set<Student> allStudents = new HashSet<>();
            allStudents.addAll(GroupRepository.getStudentsByGroup(group));
            group.setGroupList(allStudents);
            GroupDTO groupDTO = new GroupDTO();
            groupDTO = groupDTO.fromGroupToDto(group);
            allGroupDto.add(groupDTO);
        }
        return allGroupDto;
    }

    public void addStudent(Student student) {
        group.getGroupList().add(student);
        student.setGroup(group);
    }

    public List<Student> sortedBySurname() {
        List<Student> buf = new ArrayList<>(this.group.getGroupList());
        Collections.sort(buf, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getSurname().equals(o2.getSurname())) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        return buf;
    }


    public List<Student> sortedByMark() {
        List<Student> buf = new ArrayList<>(this.group.getGroupList());
        Collections.sort(buf, (o1, o2) -> {
            return Double.compare(o1.getAverageMark(), o2.getAverageMark());
        });
        return buf;
    }


    //Stream
    public Set<Student> listOfStudentByAvMark(int mark) {
        Set<Student> buf = getGroup().getGroupList();
        buf = buf.stream()
                .filter(student -> student.getAverageMark() > mark)
                .collect(Collectors.toSet());

        return buf;
    }


    public Set<Student> listOfStudentByGenAvMark(int mark) {
        Set<Student> buf = getGroup().getGroupList();
        buf = buf.stream()
                .filter(student -> student.getAverageMark() > mark)
                .filter(student -> student.getMarks().values().stream().allMatch(element -> element.getDigitMark() > mark))
                .collect(Collectors.toSet());

        return buf;
    }

    public Set<Student> getUnclassifiedStudents() {
        Set<Student> buf = getGroup().getGroupList();
        buf = buf.stream()
                .filter(student -> student.getMarks().values().stream().anyMatch(mark -> mark.getLetterMark() == 'U'))
                .collect(Collectors.toSet());
        return buf;
    }


    public void deleteUnclassifiedStudent() {
        Set<Student> buf = new HashSet<>();
        buf = getGroup().getGroupList().stream()
                .filter(student -> student.getMarks().values().stream().anyMatch(mark -> mark.getDigitMark() > 50))
                .collect(Collectors.toSet());
        getGroup().getGroupList().clear();
        getGroup().getGroupList().addAll(buf);


    }

    public static void addGroup(String name, String dateOfCreation) throws SQLException {
        String[] date = dateOfCreation.split("-");
        Group group = new Group(name, LocalDate.of(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0])));
        GroupRepository.addGroup(group);
    }

    public static List<Group> getGroups() {
        return GroupRepository.getAllGroups();
    }

    public static Group getGroupById(int id) throws SQLException {
        return GroupRepository.getGroupById(id);
    }

    public static Set<Student> getStudentsByGroup(Group group) throws SQLException {
       return GroupRepository.getStudentsByGroup(group);
    }
}

