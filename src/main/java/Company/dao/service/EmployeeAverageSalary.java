package Company.dao.service;

import dao.EmployeeDao;
import org.hibernate.Session;
import Company.dao.util.HibernateSessionFactoryUtil;

public class EmployeeAverageSalary {
    private final EmployeeDao employeeDao = new EmployeeDao();

    public EmployeeAverageSalary() {
    }


    public void findAverageSalary() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object averageSalary = session.createQuery("SELECT firstName FROM Employee WHERE salary<( Select AVG(salary) From Employee) ")
                .getResultList();
        System.out.println(averageSalary);
        session.close();
    }

}


