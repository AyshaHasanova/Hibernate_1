package Company.dao.service;

import dao.EmployeeDao;
import Company.dao.models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Company.dao.util.HibernateSessionFactoryUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDao();

    public EmployeeService() {
    }

    public void addSalaryForExperience() {

        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = employeeDao.findAll();
            employees.forEach(employee -> {
                employee.setSalary(employee.getSalary() + calculateExperience(employee.getDate()));
                session.update(employee);
            });

            transaction.commit();
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            finally {
                session.close();
            }
        }

    }

    private int calculateExperience(LocalDate date) {
        int experience = Period.between(date, LocalDate.now()).getYears();
        if (experience == 2) {
            return 100;
        } else if (experience == 4) {
            return 600;
        } else if (experience >= 5) {
            return 1000;
        }
        return 0;
    }
}





