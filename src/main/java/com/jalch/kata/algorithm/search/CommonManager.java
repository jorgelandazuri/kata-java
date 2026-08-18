package com.jalch.kata.algorithm.search;

//You are given as standard input the number of employees in a company, the first names of two selected employees in a
//company, and the direct line management relations between every employee. Each person in the company can directly
//line manage a maximum of 2 other employees. The input has the following format: * on the first line, the number of
// unique employees in the company * on the second line, the name of the first selected employee (a first name only without spaces)
// * on the third line, the name of the second selected employee (a first name only without spaces, guaranteed to be different
// from the first selected employee) * on the subsequent lines, the line management relations in the format "EmployeeX EmployeeY" -
// meaning EmployeeX manages EmployeeY (first names without spaces and spaces are used to separate the two names)

//The input is correct (there are only direct line management relations, no cycles, all employees appear in the input).
// For simplicity, the first line after the selected employees (line 4) always contains the manager at the top of the hierarchy.
// Write a program that reads the input from stdin and then outputs out the name of the single employee at the lowest
// point in the hierarchy to which the two selected employees report, either directly or indirectly.
// If one employee reports to the other, either directly or indirectly, print out the name of the highest of the two selected employees.
//E.G
//INPUT (OUTPUT=Fred)
//6
//Hilary
//James
//Sarah Fred
//Sarah Paul
//Fred Hilary
//Fred Jenny
//Jenny James

import java.util.*;

public class CommonManager {

    public static String find(String input) {
        Scanner scanner = new Scanner(input);
        scanner.nextInt(); //Ignore number of employees
        scanner.nextLine();
        String employeeA = scanner.nextLine();
        String employeeB = scanner.nextLine();
        Map<String, String> employeeToDirectManager = new HashMap<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.length() > 0) {
                String currentManager = str.split(" ")[0];
                String currentEmployee = str.split(" ")[1];
                //Root manager, manages (him|her)self.
                if (employeeToDirectManager.isEmpty()) employeeToDirectManager.put(currentManager, currentManager);
                employeeToDirectManager.put(currentEmployee, currentManager);
            } else {
                break;
            }
        }
        scanner.close();
        return managerInCommonFor(employeeA, employeeB, employeeToDirectManager);
    }

    private static String managerInCommonFor(String employeeA, String employeeB, Map<String, String> employeeToDirectManager) {

        if (employeeA.equals(employeeB)) {
            return employeeToDirectManager.get(employeeA);
        } else if (employeeToDirectManager.getOrDefault(employeeA, "").equals(employeeB)) {
            return employeeB;
        } else if (employeeToDirectManager.getOrDefault(employeeB, "").equals(employeeA)) {
            return employeeA;
        }

        LinkedList<String> employeeAToUpperManagerPath = getManagerPathFor(employeeA, new LinkedList<>(), employeeToDirectManager);
        LinkedList<String> employeeBToUpperManagerPath = getManagerPathFor(employeeB, new LinkedList<>(), employeeToDirectManager);

        employeeAToUpperManagerPath.retainAll(employeeBToUpperManagerPath);


        if (employeeAToUpperManagerPath.contains(employeeB)) {
            return employeeB;
        } else if (employeeBToUpperManagerPath.contains(employeeA)) {
            return employeeA;
        } else {
            employeeAToUpperManagerPath.retainAll(employeeBToUpperManagerPath);
            return employeeAToUpperManagerPath.peek();
        }
    }

    private static LinkedList<String> getManagerPathFor(String employee, LinkedList<String> path, Map<String, String> employeeToDirectManager) {
        if (employeeToDirectManager.getOrDefault(employee, "").equals(employee)) { //CEO absent manager reached
            path.add(employee);
            return path;
        }
        String manager = employeeToDirectManager.get(employee);
        path.add(manager);
        return getManagerPathFor(manager, path, employeeToDirectManager);
    }


}
