package com.sangle.common.mysql;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sangle.common.connector.Hibernate;
import com.sangle.common.entity.User;
import com.sangle.common.worker.MyThreadPool;


public class UserMysql {
    public static final UserMysql Instance = new UserMysql();

    private UserMysql() {
    }

    public int add(User user) {
        int id = -1;
        Session session = Hibernate.Instance.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            id = (int) session.save(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return id;
    }

    public int update(User item) {
        int rs = -1;
        Transaction tx = null;
        Session session = Hibernate.Instance.getSession();
        try {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
            rs = item.getId();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return rs;
    }

    public User getByAppIdUserId(int appId, int userId) {
        User user = null;
        Session session = Hibernate.Instance.getSession();
        try {
            Query query = session.createQuery("FROM User WHERE appId = :appId AND userId = :userId", User.class);
            query.setParameter("appId", appId);
            query.setParameter("userId", userId);

            List<User> result = query.list();
            if (!result.isEmpty()) {
                user = result.get(0);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return user;
    }

    public List<Integer[]> getSliceAppIdUserId(int pos, int limit) {
        Session session = Hibernate.Instance.getSession();
        List<Integer[]> users = new ArrayList<>();
        try {
            Query query = session.createQuery("SELECT appId, userId FROM User WHERE id > :pos", Object[].class);
            query.setParameter("pos", pos);
            query.setMaxResults(limit);
            List<Object[]> result = query.list();
            for (Object[] obj : result) {
                users.add(new Integer[] { (int) obj[0], (int) obj[1] });
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return users;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) {
            int userId = (int) (Math.random() * 400_000_000) + 100_000;
            int appId = (int) (Math.random() * 2000) + 10_000_000;
            int count = 1;
            User user = new User();
            user.setUserId(userId);
            user.setAppId(appId);
            user.setCount(count);

            MyThreadPool.Instance.asynAddUser(user);
        }
        // int total = 10_000_000;
        // int pageSize = 500;
        // long currTime = System.currentTimeMillis();
        // for (int i = 0; i < total; i += pageSize) {
        // List<Integer[]> users = Instance.getSliceAppIdUserId(i, Math.min(pageSize,
        // total - i));
        // if (users.size() == 0)
        // break;
        // }
        // System.err.println(System.currentTimeMillis() - currTime);
    }
}
