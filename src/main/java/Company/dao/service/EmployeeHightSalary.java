package Company.dao.service;

import Company.dao.models.Employee;
import dao.EmployeeDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Company.dao.util.HibernateSessionFactoryUtil;

import java.util.List;

public class EmployeeHightSalary {
    private final EmployeeDao employeeDao = new EmployeeDao();

    public EmployeeHightSalary() {
    }

    public void findHightSalary() {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = employeeDao.findAll();
            employees.forEach(employee -> {
                if (employee.getSalary() > 6000)
                    System.out.println(employee.getFirstName());
            });

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            finally {
                session.close();
            }

        }
    }
}

