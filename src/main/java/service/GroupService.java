package service;

import jdbc.StudentRepository;
import model.Group;
import model.Student;

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
                .filter(student -> student.getAverageMark()>mark)
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

//TODO get unclassified student list
//TODO create method deleteStudentFromGroup
//TODO install PostgreSQL
    public Set<Student> getUnclassifiedStudents(){
        Set<Student> buf = getGroup().getGroupList();
        buf =  buf.stream()
                .filter(student -> student.getMarks().values().stream().anyMatch(mark -> mark.getLetterMark()=='U'))
                .collect(Collectors.toSet());
        return buf;
    }


    public void deleteUnclassifiedStudent() {
        Set<Student> buf = new HashSet<>();
        buf =  getGroup().getGroupList().stream()
                .filter(student -> student.getMarks().values().stream().anyMatch(mark->mark.getDigitMark()>50))
                .collect(Collectors.toSet());
        getGroup().getGroupList().clear();
        getGroup().getGroupList().addAll(buf);


    }
}

