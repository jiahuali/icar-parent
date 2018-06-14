package com.changan.icar.comm.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationExample implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6034921932287218907L;

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OperationExample() {
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

		public Criteria andMsgIdIsNull() {
			addCriterion("msg_id is null");
			return (Criteria) this;
		}

		public Criteria andMsgIdIsNotNull() {
			addCriterion("msg_id is not null");
			return (Criteria) this;
		}

		public Criteria andMsgIdEqualTo(String value) {
			addCriterion("msg_id =", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotEqualTo(String value) {
			addCriterion("msg_id <>", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdGreaterThan(String value) {
			addCriterion("msg_id >", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdGreaterThanOrEqualTo(String value) {
			addCriterion("msg_id >=", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLessThan(String value) {
			addCriterion("msg_id <", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLessThanOrEqualTo(String value) {
			addCriterion("msg_id <=", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLike(String value) {
			addCriterion("msg_id like", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotLike(String value) {
			addCriterion("msg_id not like", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdIn(List<String> values) {
			addCriterion("msg_id in", values, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotIn(List<String> values) {
			addCriterion("msg_id not in", values, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdBetween(String value1, String value2) {
			addCriterion("msg_id between", value1, value2, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotBetween(String value1, String value2) {
			addCriterion("msg_id not between", value1, value2, "msgId");
			return (Criteria) this;
		}

		public Criteria andUuidIsNull() {
			addCriterion("uuid is null");
			return (Criteria) this;
		}

		public Criteria andUuidIsNotNull() {
			addCriterion("uuid is not null");
			return (Criteria) this;
		}

		public Criteria andUuidEqualTo(String value) {
			addCriterion("uuid =", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidNotEqualTo(String value) {
			addCriterion("uuid <>", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidGreaterThan(String value) {
			addCriterion("uuid >", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidGreaterThanOrEqualTo(String value) {
			addCriterion("uuid >=", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidLessThan(String value) {
			addCriterion("uuid <", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidLessThanOrEqualTo(String value) {
			addCriterion("uuid <=", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidLike(String value) {
			addCriterion("uuid like", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidNotLike(String value) {
			addCriterion("uuid not like", value, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidIn(List<String> values) {
			addCriterion("uuid in", values, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidNotIn(List<String> values) {
			addCriterion("uuid not in", values, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidBetween(String value1, String value2) {
			addCriterion("uuid between", value1, value2, "uuid");
			return (Criteria) this;
		}

		public Criteria andUuidNotBetween(String value1, String value2) {
			addCriterion("uuid not between", value1, value2, "uuid");
			return (Criteria) this;
		}

		public Criteria andTuidIsNull() {
			addCriterion("tuid is null");
			return (Criteria) this;
		}

		public Criteria andTuidIsNotNull() {
			addCriterion("tuid is not null");
			return (Criteria) this;
		}

		public Criteria andTuidEqualTo(String value) {
			addCriterion("tuid =", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidNotEqualTo(String value) {
			addCriterion("tuid <>", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidGreaterThan(String value) {
			addCriterion("tuid >", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidGreaterThanOrEqualTo(String value) {
			addCriterion("tuid >=", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidLessThan(String value) {
			addCriterion("tuid <", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidLessThanOrEqualTo(String value) {
			addCriterion("tuid <=", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidLike(String value) {
			addCriterion("tuid like", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidNotLike(String value) {
			addCriterion("tuid not like", value, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidIn(List<String> values) {
			addCriterion("tuid in", values, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidNotIn(List<String> values) {
			addCriterion("tuid not in", values, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidBetween(String value1, String value2) {
			addCriterion("tuid between", value1, value2, "tuid");
			return (Criteria) this;
		}

		public Criteria andTuidNotBetween(String value1, String value2) {
			addCriterion("tuid not between", value1, value2, "tuid");
			return (Criteria) this;
		}

		public Criteria andDatetimeIsNull() {
			addCriterion("datetime is null");
			return (Criteria) this;
		}

		public Criteria andDatetimeIsNotNull() {
			addCriterion("datetime is not null");
			return (Criteria) this;
		}

		public Criteria andDatetimeEqualTo(Date value) {
			addCriterion("datetime =", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeNotEqualTo(Date value) {
			addCriterion("datetime <>", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeGreaterThan(Date value) {
			addCriterion("datetime >", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeGreaterThanOrEqualTo(Date value) {
			addCriterion("datetime >=", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeLessThan(Date value) {
			addCriterion("datetime <", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeLessThanOrEqualTo(Date value) {
			addCriterion("datetime <=", value, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeIn(List<Date> values) {
			addCriterion("datetime in", values, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeNotIn(List<Date> values) {
			addCriterion("datetime not in", values, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeBetween(Date value1, Date value2) {
			addCriterion("datetime between", value1, value2, "datetime");
			return (Criteria) this;
		}

		public Criteria andDatetimeNotBetween(Date value1, Date value2) {
			addCriterion("datetime not between", value1, value2, "datetime");
			return (Criteria) this;
		}

		public Criteria andOperationIsNull() {
			addCriterion("operation is null");
			return (Criteria) this;
		}

		public Criteria andOperationIsNotNull() {
			addCriterion("operation is not null");
			return (Criteria) this;
		}

		public Criteria andOperationEqualTo(Integer value) {
			addCriterion("operation =", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationNotEqualTo(Integer value) {
			addCriterion("operation <>", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationGreaterThan(Integer value) {
			addCriterion("operation >", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationGreaterThanOrEqualTo(Integer value) {
			addCriterion("operation >=", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationLessThan(Integer value) {
			addCriterion("operation <", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationLessThanOrEqualTo(Integer value) {
			addCriterion("operation <=", value, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationIn(List<Integer> values) {
			addCriterion("operation in", values, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationNotIn(List<Integer> values) {
			addCriterion("operation not in", values, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationBetween(Integer value1, Integer value2) {
			addCriterion("operation between", value1, value2, "operation");
			return (Criteria) this;
		}

		public Criteria andOperationNotBetween(Integer value1, Integer value2) {
			addCriterion("operation not between", value1, value2, "operation");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1811545019415421460L;

		protected Criteria() {
			super();
		}
	}

	public static class Criterion implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6368997319497542086L;

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