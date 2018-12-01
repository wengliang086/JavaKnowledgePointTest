package com.my.test.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLambda {

    private List<Employee> employees = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test01() {
        List<Employee> employees = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 35;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test02() {
        filterEmployee(employees, e -> e.getAge() > 35).forEach(System.out::println);
    }

    @Test
    public void test03() {
        employees.stream().filter(e -> e.getAge() > 35).forEach(System.out::println);
    }

    // 优化方式一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : emps) {
            if (mp.test(employee)) {
                list.add(employee);
            }
        }
        return list;
    }
}
