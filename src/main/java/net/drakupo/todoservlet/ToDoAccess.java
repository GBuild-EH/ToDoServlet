package net.drakupo.todoservlet;

import java.util.*;
import org.hibernate.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ToDoAccess {

    public ToDoAccess() {}

    public static Logger logger = LogManager.getLogger("debug");
    public int genID() {

        Transaction tx = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Object q = session.createQuery("SELECT MAX(id) FROM ToDoTask").uniqueResult();
            if (q != null) {
                return ((int) q) + 1;
            } else return 1;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            logger.error(e);
            return -1;
        }
    }

    public void addTask(ToDoTask task) {
        Transaction tx = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(task);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error(e);
        }
    }

    public void deleteTask(int id) {
        Transaction tx = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ToDoTask task = session.get(ToDoTask.class, id);
            if (task != null) {
                session.delete(task);
                System.out.println("Task deleted successfully");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e);
        }
    }

    public ToDoTask showTask(int id) {
        Transaction tx = null;
        ToDoTask task = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            task = session.get(ToDoTask.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e);
        }
        return task;
    }

    public List<ToDoTask> showTasks() {
        Transaction tx = null;
        List tasks = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            tasks = session.createQuery("From ToDoTask").getResultList();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e);
        }
        return tasks;
    }

    public void updateTask(ToDoTask task) {
        Transaction tx = null;
        try (Session session = ToDoSetup.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e);
        }
    }
}
