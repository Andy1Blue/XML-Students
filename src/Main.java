import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, Double> studentsWithAverage = new HashMap<>();
        String studentName;

        try {
            File inputFile = new File("students.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("------\nList of students\n------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student id: "
                            + eElement.getAttribute("id")
                    );
                    System.out.println("Name: "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    studentName = eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent();

                    NodeList gradeList = eElement.getElementsByTagName("result");
                    System.out.println("\nList of Grades: ");
                    double average = 0;
                    for (int count = 0; count < gradeList.getLength(); count++) {
                        Node node1 = gradeList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element grade = (Element) node1;
                            System.out.println(grade.getAttribute("course") + ": "
                                    + grade.getAttribute("grade"));
                            average += Double.valueOf(grade.getAttribute("grade").replaceAll("[^0-9.]", "."));

                        }
                    }
                    average = Math.round(average / gradeList.getLength() * 100.0) / 100.0;
                    System.out.println("\nAverage: " + average);
                    studentsWithAverage.put(studentName, average);
                }
                System.out.println("---");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n\n------\nMap of students (Name and avarange)\n------");
        for (Map.Entry<String, Double> entry : studentsWithAverage.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("\n------\nThe longest last name\n------");
        int findTheLongestLastname = 0;
        int theLongestLastnameInt = 0;
        String theLongestLastname = "";
        for (Map.Entry<String, Double> entry : studentsWithAverage.entrySet()) {
            findTheLongestLastname = entry.getKey().split(" ")[1].length();
            if (theLongestLastnameInt < findTheLongestLastname) {
                theLongestLastnameInt = findTheLongestLastname;
                theLongestLastname = entry.getKey();
            }
        }
        System.out.println("The longest lastname have " + theLongestLastname);

        System.out.println("\n------\nThe highest avarange\n------");
        double findTheHighestAvarange = 0;
        double theHighestAvarange = 0;
        String nameOfStudentWithTheHighestAvarange = "";
        for (Map.Entry<String, Double> entry : studentsWithAverage.entrySet()) {
            findTheHighestAvarange = entry.getValue();
            if (theHighestAvarange < findTheHighestAvarange) {
                theHighestAvarange = findTheHighestAvarange;
                nameOfStudentWithTheHighestAvarange = entry.getKey();
            }
        }
        System.out.println(nameOfStudentWithTheHighestAvarange + " have " + theHighestAvarange + " avarange!");

    }
}

