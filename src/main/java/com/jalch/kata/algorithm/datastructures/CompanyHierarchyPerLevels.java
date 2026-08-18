package com.jalch.kata.algorithm.datastructures;

import java.util.*;

public class CompanyHierarchyPerLevels {

    static Employee upperManager = null;

    //Assuming input is correct and validated
    public static String findUsingBTree(String input) {
        Scanner scanner = new Scanner(input);
        Integer.parseInt(scanner.nextLine());
        upperManager = new Employee();
        for (int i = 0; scanner.hasNextLine(); i++) {
            String currentLine = scanner.nextLine();
            String[] managerAndSubordinate = currentLine.split(" ");
            if (i == 0) upperManager.name = managerAndSubordinate[0];
            insertInBTree(upperManager, managerAndSubordinate[0], managerAndSubordinate[1]);
        }
        String s = buildHierarchyString(upperManager);
        System.out.print(s);
        scanner.close();
        return s;
    }

    private static void insertInBTree(Employee rootEmployee, String employeeAName, String employeeBName) {
        if (rootEmployee == null) return;
        if (rootEmployee.name.equals(employeeAName)) {
            if (rootEmployee.leftSubordinate == null) {
                rootEmployee.leftSubordinate = new Employee(employeeBName);
                return;
            } else {
                rootEmployee.rightSubordinate = new Employee(employeeBName);
                return;
            }
        }
        insertInBTree(rootEmployee.leftSubordinate, employeeAName, employeeBName);
        insertInBTree(rootEmployee.rightSubordinate, employeeAName, employeeBName);
    }

    private static String buildHierarchyString(Employee startNode) {
        Queue<Employee> currentLevelToPrint = new LinkedList<>();
        Queue<Employee> nextLevel = new LinkedList<>();
        currentLevelToPrint.add(startNode);
        StringBuffer sb = new StringBuffer();

        while (!currentLevelToPrint.isEmpty()) {
            Employee employeeToPrint = currentLevelToPrint.poll();
            sb.append(employeeToPrint.name);
            sb.append(" ");
            if (employeeToPrint.leftSubordinate != null)
                nextLevel.add(employeeToPrint.leftSubordinate);
            if (employeeToPrint.rightSubordinate != null)
                nextLevel.add(employeeToPrint.rightSubordinate);
            if (currentLevelToPrint.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append("\n");
                while (!nextLevel.isEmpty())
                    currentLevelToPrint.add(nextLevel.poll());
            }
        }
        return sb.toString();
    }

    static class Employee {
        String name;
        Employee leftSubordinate;
        Employee rightSubordinate;

        public Employee() {}

        public Employee(String name) {
            this.name = name;
        }
    }

}
