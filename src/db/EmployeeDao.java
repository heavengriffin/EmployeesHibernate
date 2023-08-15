package db;

import com.mysql.cj.QueryResult;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.query.ParameterMetadata;
import org.hibernate.query.Query;
import org.hibernate.query.QueryParameter;
import org.hibernate.tool.hbm2ddl.TableMetadata;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EmployeeDao implements Dao<Employee> {

    @Override
    public void insert(Employee entity) {
        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.persist(entity);

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public void update(Employee entity) {
        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public void delete(Employee entity) {
        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;

    }

    @Override
    public Employee getEmployee(int id) {
        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        Employee employee = null;

        try {
            transaction = session.beginTransaction();

            employee = session.get(Employee.class, id);
            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employee;
    }

    @Override
    public List<Employee> searchByName(String name) {
        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee as e where e.name = '" + name + "'";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;
    }

    @Override
    public List<Employee> searchBySurname(String surname) {
        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee as e where e.surname = '" + surname + "'";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;
    }

    @Override
    public List<Employee> searchByAge(int age) {
        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee as e where e.age = '" + age + "'";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;
    }

    @Override
    public List<Employee> searchByAddress(String address) {
        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee as e where e.address = '" + address + "'";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;
    }

    @Override
    public List<Employee> searchBySalary(int salary) {
        List<Employee> employees = null;

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hql = "from Employee as e where e.salary = '" + salary + "'";
            Query query = session.createQuery(hql);
            employees = query.list();

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
        return employees;
    }

    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();

        Session session = HibernateUtil.createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            MetamodelImplementor metamodel = (MetamodelImplementor) session.getSessionFactory().getMetamodel();
            ClassMetadata classMetadata = (ClassMetadata) metamodel.entityPersister(Employee.class);
            columnNames.add(classMetadata.getIdentifierPropertyName());
            String[] names = classMetadata.getPropertyNames();
            for (String n : names)
                columnNames.add(n);

            transaction.commit();
        } catch (HibernateException exc) {
            if (transaction != null)
                transaction.rollback();
            exc.printStackTrace();
        } finally {
            HibernateUtil.close();
        }

        return columnNames;
    }
}
