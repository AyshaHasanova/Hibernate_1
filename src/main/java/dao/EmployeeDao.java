package dao;

import Company.dao.models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Company.dao.util.HibernateSessionFactoryUtil;

import java.util.Collections;
import java.util.List;

public class EmployeeDao {

    public Employee findById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void delete(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(employee);
        transaction.commit();
        session.close();
    }


    public List<Employee> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Employee ").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
