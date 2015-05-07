package org.yonixee.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yonixee.dao.BaseDao;
import org.yonixee.utils.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by yonixee on 15/5/7.
 */
@Repository("baseDao")
@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    public List<T> find(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<T> find(String hql, Object[] param) {
        return createQuery(hql, param).list();
    }

    public List<T> find(String hql, List<Object> param) {
        return createQuery(hql, param).list();
    }



    protected Query createQuery(String hql, Object ... param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q;
    }

    protected Query createQuery(String hql, List param) {
        return createQuery(hql, param.toArray());
    }

    protected Query createCountQuery(String hql, List param) {
        hql = hql.replaceFirst("^.*from", "select count(*) from");
        return createQuery(hql, param);
    }

    public Page<T> find(String hql, Object[] param, Page<T> page) {
        return find(hql, Arrays.asList(param), page);
    }

    public Page<T> find(String hql, List<Object> param, Page<T> page) {
        Query q = createQuery(hql, param);
        long total = count(hql, param);
        page = page == null ? new Page<T>() : page;
        page.setTotal(total);
        page.setResult(q.setFirstResult(page.getOffset()).setMaxResults(page.getSize()).list());
        return page;
    }

    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    public T get(String hql, Object[] param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public T get(String hql, List<Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public Long count(String hql) {
        return (Long) createCountQuery(hql, new ArrayList<T>()).uniqueResult();
    }

    public Long count(String hql, Object[] param) {
        return count(hql, Arrays.asList(param));
    }

    public Long count(String hql, List<Object> param) {
        return (Long) createCountQuery(hql, param).uniqueResult();
    }

    public Integer executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

    public Integer executeHql(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }

    public Integer executeHql(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.executeUpdate();
    }

}
