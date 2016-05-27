package com.rzq.gpms.api.tree.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeCriteria {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public TreeCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
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

		public Criteria andPidIsNull() {
			addCriterion("pid is null");
			return (Criteria) this;
		}

		public Criteria andPidIsNotNull() {
			addCriterion("pid is not null");
			return (Criteria) this;
		}

		public Criteria andPidEqualTo(Integer value) {
			addCriterion("pid =", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotEqualTo(Integer value) {
			addCriterion("pid <>", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidGreaterThan(Integer value) {
			addCriterion("pid >", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidGreaterThanOrEqualTo(Integer value) {
			addCriterion("pid >=", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidLessThan(Integer value) {
			addCriterion("pid <", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidLessThanOrEqualTo(Integer value) {
			addCriterion("pid <=", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidIn(List<Integer> values) {
			addCriterion("pid in", values, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotIn(List<Integer> values) {
			addCriterion("pid not in", values, "pid");
			return (Criteria) this;
		}

		public Criteria andPidBetween(Integer value1, Integer value2) {
			addCriterion("pid between", value1, value2, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotBetween(Integer value1, Integer value2) {
			addCriterion("pid not between", value1, value2, "pid");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andIsleafIsNull() {
			addCriterion("isleaf is null");
			return (Criteria) this;
		}

		public Criteria andIsleafIsNotNull() {
			addCriterion("isleaf is not null");
			return (Criteria) this;
		}

		public Criteria andIsleafEqualTo(String value) {
			addCriterion("isleaf =", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafNotEqualTo(String value) {
			addCriterion("isleaf <>", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafGreaterThan(String value) {
			addCriterion("isleaf >", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafGreaterThanOrEqualTo(String value) {
			addCriterion("isleaf >=", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafLessThan(String value) {
			addCriterion("isleaf <", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafLessThanOrEqualTo(String value) {
			addCriterion("isleaf <=", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafLike(String value) {
			addCriterion("isleaf like", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafNotLike(String value) {
			addCriterion("isleaf not like", value, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafIn(List<String> values) {
			addCriterion("isleaf in", values, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafNotIn(List<String> values) {
			addCriterion("isleaf not in", values, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafBetween(String value1, String value2) {
			addCriterion("isleaf between", value1, value2, "isleaf");
			return (Criteria) this;
		}

		public Criteria andIsleafNotBetween(String value1, String value2) {
			addCriterion("isleaf not between", value1, value2, "isleaf");
			return (Criteria) this;
		}

		public Criteria andLinkIsNull() {
			addCriterion("link is null");
			return (Criteria) this;
		}

		public Criteria andLinkIsNotNull() {
			addCriterion("link is not null");
			return (Criteria) this;
		}

		public Criteria andLinkEqualTo(String value) {
			addCriterion("link =", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotEqualTo(String value) {
			addCriterion("link <>", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThan(String value) {
			addCriterion("link >", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThanOrEqualTo(String value) {
			addCriterion("link >=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThan(String value) {
			addCriterion("link <", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThanOrEqualTo(String value) {
			addCriterion("link <=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLike(String value) {
			addCriterion("link like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotLike(String value) {
			addCriterion("link not like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkIn(List<String> values) {
			addCriterion("link in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotIn(List<String> values) {
			addCriterion("link not in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkBetween(String value1, String value2) {
			addCriterion("link between", value1, value2, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotBetween(String value1, String value2) {
			addCriterion("link not between", value1, value2, "link");
			return (Criteria) this;
		}

		public Criteria andEnableIsNull() {
			addCriterion("enable is null");
			return (Criteria) this;
		}

		public Criteria andEnableIsNotNull() {
			addCriterion("enable is not null");
			return (Criteria) this;
		}

		public Criteria andEnableEqualTo(Integer value) {
			addCriterion("enable =", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableNotEqualTo(Integer value) {
			addCriterion("enable <>", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableGreaterThan(Integer value) {
			addCriterion("enable >", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableGreaterThanOrEqualTo(Integer value) {
			addCriterion("enable >=", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableLessThan(Integer value) {
			addCriterion("enable <", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableLessThanOrEqualTo(Integer value) {
			addCriterion("enable <=", value, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableIn(List<Integer> values) {
			addCriterion("enable in", values, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableNotIn(List<Integer> values) {
			addCriterion("enable not in", values, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableBetween(Integer value1, Integer value2) {
			addCriterion("enable between", value1, value2, "enable");
			return (Criteria) this;
		}

		public Criteria andEnableNotBetween(Integer value1, Integer value2) {
			addCriterion("enable not between", value1, value2, "enable");
			return (Criteria) this;
		}

		public Criteria andOrderidIsNull() {
			addCriterion("orderid is null");
			return (Criteria) this;
		}

		public Criteria andOrderidIsNotNull() {
			addCriterion("orderid is not null");
			return (Criteria) this;
		}

		public Criteria andOrderidEqualTo(Integer value) {
			addCriterion("orderid =", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidNotEqualTo(Integer value) {
			addCriterion("orderid <>", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidGreaterThan(Integer value) {
			addCriterion("orderid >", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
			addCriterion("orderid >=", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidLessThan(Integer value) {
			addCriterion("orderid <", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidLessThanOrEqualTo(Integer value) {
			addCriterion("orderid <=", value, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidIn(List<Integer> values) {
			addCriterion("orderid in", values, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidNotIn(List<Integer> values) {
			addCriterion("orderid not in", values, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidBetween(Integer value1, Integer value2) {
			addCriterion("orderid between", value1, value2, "orderid");
			return (Criteria) this;
		}

		public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
			addCriterion("orderid not between", value1, value2, "orderid");
			return (Criteria) this;
		}

		public Criteria andNameLikeInsensitive(String value) {
			addCriterion("upper(name) like", value.toUpperCase(), "name");
			return (Criteria) this;
		}

		public Criteria andIsleafLikeInsensitive(String value) {
			addCriterion("upper(isleaf) like", value.toUpperCase(), "isleaf");
			return (Criteria) this;
		}

		public Criteria andLinkLikeInsensitive(String value) {
			addCriterion("upper(link) like", value.toUpperCase(), "link");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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