package com.rytec.rec.db.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceNodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public DeviceNodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDnoIsNull() {
            addCriterion("dno is null");
            return (Criteria) this;
        }

        public Criteria andDnoIsNotNull() {
            addCriterion("dno is not null");
            return (Criteria) this;
        }

        public Criteria andDnoEqualTo(Integer value) {
            addCriterion("dno =", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoNotEqualTo(Integer value) {
            addCriterion("dno <>", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoGreaterThan(Integer value) {
            addCriterion("dno >", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("dno >=", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoLessThan(Integer value) {
            addCriterion("dno <", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoLessThanOrEqualTo(Integer value) {
            addCriterion("dno <=", value, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoIn(List<Integer> values) {
            addCriterion("dno in", values, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoNotIn(List<Integer> values) {
            addCriterion("dno not in", values, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoBetween(Integer value1, Integer value2) {
            addCriterion("dno between", value1, value2, "dno");
            return (Criteria) this;
        }

        public Criteria andDnoNotBetween(Integer value1, Integer value2) {
            addCriterion("dno not between", value1, value2, "dno");
            return (Criteria) this;
        }

        public Criteria andDnameIsNull() {
            addCriterion("dname is null");
            return (Criteria) this;
        }

        public Criteria andDnameIsNotNull() {
            addCriterion("dname is not null");
            return (Criteria) this;
        }

        public Criteria andDnameEqualTo(String value) {
            addCriterion("dname =", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotEqualTo(String value) {
            addCriterion("dname <>", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThan(String value) {
            addCriterion("dname >", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThanOrEqualTo(String value) {
            addCriterion("dname >=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThan(String value) {
            addCriterion("dname <", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThanOrEqualTo(String value) {
            addCriterion("dname <=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLike(String value) {
            addCriterion("dname like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotLike(String value) {
            addCriterion("dname not like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameIn(List<String> values) {
            addCriterion("dname in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotIn(List<String> values) {
            addCriterion("dname not in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameBetween(String value1, String value2) {
            addCriterion("dname between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotBetween(String value1, String value2) {
            addCriterion("dname not between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDtypeIsNull() {
            addCriterion("dtype is null");
            return (Criteria) this;
        }

        public Criteria andDtypeIsNotNull() {
            addCriterion("dtype is not null");
            return (Criteria) this;
        }

        public Criteria andDtypeEqualTo(Integer value) {
            addCriterion("dtype =", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotEqualTo(Integer value) {
            addCriterion("dtype <>", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThan(Integer value) {
            addCriterion("dtype >", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dtype >=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThan(Integer value) {
            addCriterion("dtype <", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThanOrEqualTo(Integer value) {
            addCriterion("dtype <=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeIn(List<Integer> values) {
            addCriterion("dtype in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotIn(List<Integer> values) {
            addCriterion("dtype not in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeBetween(Integer value1, Integer value2) {
            addCriterion("dtype between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dtype not between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeIsNull() {
            addCriterion("lnodetype is null");
            return (Criteria) this;
        }

        public Criteria andLnodetypeIsNotNull() {
            addCriterion("lnodetype is not null");
            return (Criteria) this;
        }

        public Criteria andLnodetypeEqualTo(String value) {
            addCriterion("lnodetype =", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeNotEqualTo(String value) {
            addCriterion("lnodetype <>", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeGreaterThan(String value) {
            addCriterion("lnodetype >", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeGreaterThanOrEqualTo(String value) {
            addCriterion("lnodetype >=", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeLessThan(String value) {
            addCriterion("lnodetype <", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeLessThanOrEqualTo(String value) {
            addCriterion("lnodetype <=", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeLike(String value) {
            addCriterion("lnodetype like", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeNotLike(String value) {
            addCriterion("lnodetype not like", value, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeIn(List<String> values) {
            addCriterion("lnodetype in", values, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeNotIn(List<String> values) {
            addCriterion("lnodetype not in", values, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeBetween(String value1, String value2) {
            addCriterion("lnodetype between", value1, value2, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodetypeNotBetween(String value1, String value2) {
            addCriterion("lnodetype not between", value1, value2, "lnodetype");
            return (Criteria) this;
        }

        public Criteria andLnodenumIsNull() {
            addCriterion("lnodenum is null");
            return (Criteria) this;
        }

        public Criteria andLnodenumIsNotNull() {
            addCriterion("lnodenum is not null");
            return (Criteria) this;
        }

        public Criteria andLnodenumEqualTo(Integer value) {
            addCriterion("lnodenum =", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumNotEqualTo(Integer value) {
            addCriterion("lnodenum <>", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumGreaterThan(Integer value) {
            addCriterion("lnodenum >", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("lnodenum >=", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumLessThan(Integer value) {
            addCriterion("lnodenum <", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumLessThanOrEqualTo(Integer value) {
            addCriterion("lnodenum <=", value, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumIn(List<Integer> values) {
            addCriterion("lnodenum in", values, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumNotIn(List<Integer> values) {
            addCriterion("lnodenum not in", values, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumBetween(Integer value1, Integer value2) {
            addCriterion("lnodenum between", value1, value2, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andLnodenumNotBetween(Integer value1, Integer value2) {
            addCriterion("lnodenum not between", value1, value2, "lnodenum");
            return (Criteria) this;
        }

        public Criteria andNidIsNull() {
            addCriterion("nid is null");
            return (Criteria) this;
        }

        public Criteria andNidIsNotNull() {
            addCriterion("nid is not null");
            return (Criteria) this;
        }

        public Criteria andNidEqualTo(Integer value) {
            addCriterion("nid =", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotEqualTo(Integer value) {
            addCriterion("nid <>", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidGreaterThan(Integer value) {
            addCriterion("nid >", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidGreaterThanOrEqualTo(Integer value) {
            addCriterion("nid >=", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidLessThan(Integer value) {
            addCriterion("nid <", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidLessThanOrEqualTo(Integer value) {
            addCriterion("nid <=", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidIn(List<Integer> values) {
            addCriterion("nid in", values, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotIn(List<Integer> values) {
            addCriterion("nid not in", values, "nid");
            return (Criteria) this;
        }

        public Criteria andNidBetween(Integer value1, Integer value2) {
            addCriterion("nid between", value1, value2, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotBetween(Integer value1, Integer value2) {
            addCriterion("nid not between", value1, value2, "nid");
            return (Criteria) this;
        }

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andNaddIsNull() {
            addCriterion("nadd is null");
            return (Criteria) this;
        }

        public Criteria andNaddIsNotNull() {
            addCriterion("nadd is not null");
            return (Criteria) this;
        }

        public Criteria andNaddEqualTo(Integer value) {
            addCriterion("nadd =", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddNotEqualTo(Integer value) {
            addCriterion("nadd <>", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddGreaterThan(Integer value) {
            addCriterion("nadd >", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddGreaterThanOrEqualTo(Integer value) {
            addCriterion("nadd >=", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddLessThan(Integer value) {
            addCriterion("nadd <", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddLessThanOrEqualTo(Integer value) {
            addCriterion("nadd <=", value, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddIn(List<Integer> values) {
            addCriterion("nadd in", values, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddNotIn(List<Integer> values) {
            addCriterion("nadd not in", values, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddBetween(Integer value1, Integer value2) {
            addCriterion("nadd between", value1, value2, "nadd");
            return (Criteria) this;
        }

        public Criteria andNaddNotBetween(Integer value1, Integer value2) {
            addCriterion("nadd not between", value1, value2, "nadd");
            return (Criteria) this;
        }

        public Criteria andNnoIsNull() {
            addCriterion("nno is null");
            return (Criteria) this;
        }

        public Criteria andNnoIsNotNull() {
            addCriterion("nno is not null");
            return (Criteria) this;
        }

        public Criteria andNnoEqualTo(Integer value) {
            addCriterion("nno =", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoNotEqualTo(Integer value) {
            addCriterion("nno <>", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoGreaterThan(Integer value) {
            addCriterion("nno >", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("nno >=", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoLessThan(Integer value) {
            addCriterion("nno <", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoLessThanOrEqualTo(Integer value) {
            addCriterion("nno <=", value, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoIn(List<Integer> values) {
            addCriterion("nno in", values, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoNotIn(List<Integer> values) {
            addCriterion("nno not in", values, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoBetween(Integer value1, Integer value2) {
            addCriterion("nno between", value1, value2, "nno");
            return (Criteria) this;
        }

        public Criteria andNnoNotBetween(Integer value1, Integer value2) {
            addCriterion("nno not between", value1, value2, "nno");
            return (Criteria) this;
        }

        public Criteria andNtypeIsNull() {
            addCriterion("ntype is null");
            return (Criteria) this;
        }

        public Criteria andNtypeIsNotNull() {
            addCriterion("ntype is not null");
            return (Criteria) this;
        }

        public Criteria andNtypeEqualTo(Integer value) {
            addCriterion("ntype =", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotEqualTo(Integer value) {
            addCriterion("ntype <>", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeGreaterThan(Integer value) {
            addCriterion("ntype >", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ntype >=", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeLessThan(Integer value) {
            addCriterion("ntype <", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ntype <=", value, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeIn(List<Integer> values) {
            addCriterion("ntype in", values, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotIn(List<Integer> values) {
            addCriterion("ntype not in", values, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeBetween(Integer value1, Integer value2) {
            addCriterion("ntype between", value1, value2, "ntype");
            return (Criteria) this;
        }

        public Criteria andNtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ntype not between", value1, value2, "ntype");
            return (Criteria) this;
        }

        public Criteria andConfIsNull() {
            addCriterion("conf is null");
            return (Criteria) this;
        }

        public Criteria andConfIsNotNull() {
            addCriterion("conf is not null");
            return (Criteria) this;
        }

        public Criteria andConfEqualTo(String value) {
            addCriterion("conf =", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfNotEqualTo(String value) {
            addCriterion("conf <>", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfGreaterThan(String value) {
            addCriterion("conf >", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfGreaterThanOrEqualTo(String value) {
            addCriterion("conf >=", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfLessThan(String value) {
            addCriterion("conf <", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfLessThanOrEqualTo(String value) {
            addCriterion("conf <=", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfLike(String value) {
            addCriterion("conf like", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfNotLike(String value) {
            addCriterion("conf not like", value, "conf");
            return (Criteria) this;
        }

        public Criteria andConfIn(List<String> values) {
            addCriterion("conf in", values, "conf");
            return (Criteria) this;
        }

        public Criteria andConfNotIn(List<String> values) {
            addCriterion("conf not in", values, "conf");
            return (Criteria) this;
        }

        public Criteria andConfBetween(String value1, String value2) {
            addCriterion("conf between", value1, value2, "conf");
            return (Criteria) this;
        }

        public Criteria andConfNotBetween(String value1, String value2) {
            addCriterion("conf not between", value1, value2, "conf");
            return (Criteria) this;
        }

        public Criteria andNfunIsNull() {
            addCriterion("nfun is null");
            return (Criteria) this;
        }

        public Criteria andNfunIsNotNull() {
            addCriterion("nfun is not null");
            return (Criteria) this;
        }

        public Criteria andNfunEqualTo(Integer value) {
            addCriterion("nfun =", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunNotEqualTo(Integer value) {
            addCriterion("nfun <>", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunGreaterThan(Integer value) {
            addCriterion("nfun >", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunGreaterThanOrEqualTo(Integer value) {
            addCriterion("nfun >=", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunLessThan(Integer value) {
            addCriterion("nfun <", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunLessThanOrEqualTo(Integer value) {
            addCriterion("nfun <=", value, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunIn(List<Integer> values) {
            addCriterion("nfun in", values, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunNotIn(List<Integer> values) {
            addCriterion("nfun not in", values, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunBetween(Integer value1, Integer value2) {
            addCriterion("nfun between", value1, value2, "nfun");
            return (Criteria) this;
        }

        public Criteria andNfunNotBetween(Integer value1, Integer value2) {
            addCriterion("nfun not between", value1, value2, "nfun");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table devicenode
     *
     * @mbggenerated do_not_delete_during_merge Thu Dec 22 15:55:15 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table devicenode
     *
     * @mbggenerated Thu Dec 22 15:55:15 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}