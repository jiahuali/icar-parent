package com.changan.icar.comm.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountExample implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9165575243564926735L;

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public AccountExample() {
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

		public Criteria andUsernameIsNull() {
			addCriterion("username is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("username is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("username =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("username <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("username >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("username >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("username <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("username <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("username like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("username not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("username in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("username not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("username between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("username not between", value1, value2, "username");
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

		public Criteria andPhoneIsNull() {
			addCriterion("phone is null");
			return (Criteria) this;
		}

		public Criteria andPhoneIsNotNull() {
			addCriterion("phone is not null");
			return (Criteria) this;
		}

		public Criteria andPhoneEqualTo(String value) {
			addCriterion("phone =", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotEqualTo(String value) {
			addCriterion("phone <>", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThan(String value) {
			addCriterion("phone >", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("phone >=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThan(String value) {
			addCriterion("phone <", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLessThanOrEqualTo(String value) {
			addCriterion("phone <=", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneLike(String value) {
			addCriterion("phone like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotLike(String value) {
			addCriterion("phone not like", value, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneIn(List<String> values) {
			addCriterion("phone in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotIn(List<String> values) {
			addCriterion("phone not in", values, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneBetween(String value1, String value2) {
			addCriterion("phone between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPhoneNotBetween(String value1, String value2) {
			addCriterion("phone not between", value1, value2, "phone");
			return (Criteria) this;
		}

		public Criteria andPushTagIsNull() {
			addCriterion("push_tag is null");
			return (Criteria) this;
		}

		public Criteria andPushTagIsNotNull() {
			addCriterion("push_tag is not null");
			return (Criteria) this;
		}

		public Criteria andPushTagEqualTo(String value) {
			addCriterion("push_tag =", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagNotEqualTo(String value) {
			addCriterion("push_tag <>", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagGreaterThan(String value) {
			addCriterion("push_tag >", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagGreaterThanOrEqualTo(String value) {
			addCriterion("push_tag >=", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagLessThan(String value) {
			addCriterion("push_tag <", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagLessThanOrEqualTo(String value) {
			addCriterion("push_tag <=", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagLike(String value) {
			addCriterion("push_tag like", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagNotLike(String value) {
			addCriterion("push_tag not like", value, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagIn(List<String> values) {
			addCriterion("push_tag in", values, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagNotIn(List<String> values) {
			addCriterion("push_tag not in", values, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagBetween(String value1, String value2) {
			addCriterion("push_tag between", value1, value2, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushTagNotBetween(String value1, String value2) {
			addCriterion("push_tag not between", value1, value2, "pushTag");
			return (Criteria) this;
		}

		public Criteria andPushAliasIsNull() {
			addCriterion("push_alias is null");
			return (Criteria) this;
		}

		public Criteria andPushAliasIsNotNull() {
			addCriterion("push_alias is not null");
			return (Criteria) this;
		}

		public Criteria andPushAliasEqualTo(String value) {
			addCriterion("push_alias =", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasNotEqualTo(String value) {
			addCriterion("push_alias <>", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasGreaterThan(String value) {
			addCriterion("push_alias >", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasGreaterThanOrEqualTo(String value) {
			addCriterion("push_alias >=", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasLessThan(String value) {
			addCriterion("push_alias <", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasLessThanOrEqualTo(String value) {
			addCriterion("push_alias <=", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasLike(String value) {
			addCriterion("push_alias like", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasNotLike(String value) {
			addCriterion("push_alias not like", value, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasIn(List<String> values) {
			addCriterion("push_alias in", values, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasNotIn(List<String> values) {
			addCriterion("push_alias not in", values, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasBetween(String value1, String value2) {
			addCriterion("push_alias between", value1, value2, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andPushAliasNotBetween(String value1, String value2) {
			addCriterion("push_alias not between", value1, value2, "pushAlias");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceIsNull() {
			addCriterion("default_device is null");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceIsNotNull() {
			addCriterion("default_device is not null");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceEqualTo(String value) {
			addCriterion("default_device =", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceNotEqualTo(String value) {
			addCriterion("default_device <>", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceGreaterThan(String value) {
			addCriterion("default_device >", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceGreaterThanOrEqualTo(String value) {
			addCriterion("default_device >=", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceLessThan(String value) {
			addCriterion("default_device <", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceLessThanOrEqualTo(String value) {
			addCriterion("default_device <=", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceLike(String value) {
			addCriterion("default_device like", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceNotLike(String value) {
			addCriterion("default_device not like", value, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceIn(List<String> values) {
			addCriterion("default_device in", values, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceNotIn(List<String> values) {
			addCriterion("default_device not in", values, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceBetween(String value1, String value2) {
			addCriterion("default_device between", value1, value2, "defaultDevice");
			return (Criteria) this;
		}

		public Criteria andDefaultDeviceNotBetween(String value1, String value2) {
			addCriterion("default_device not between", value1, value2, "defaultDevice");
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

		public Criteria andUserFromIsNull() {
			addCriterion("user_from is null");
			return (Criteria) this;
		}

		public Criteria andUserFromIsNotNull() {
			addCriterion("user_from is not null");
			return (Criteria) this;
		}

		public Criteria andUserFromEqualTo(Integer value) {
			addCriterion("user_from =", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromNotEqualTo(Integer value) {
			addCriterion("user_from <>", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromGreaterThan(Integer value) {
			addCriterion("user_from >", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_from >=", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromLessThan(Integer value) {
			addCriterion("user_from <", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromLessThanOrEqualTo(Integer value) {
			addCriterion("user_from <=", value, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromIn(List<Integer> values) {
			addCriterion("user_from in", values, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromNotIn(List<Integer> values) {
			addCriterion("user_from not in", values, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromBetween(Integer value1, Integer value2) {
			addCriterion("user_from between", value1, value2, "userFrom");
			return (Criteria) this;
		}

		public Criteria andUserFromNotBetween(Integer value1, Integer value2) {
			addCriterion("user_from not between", value1, value2, "userFrom");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2243432142722762524L;

		protected Criteria() {
			super();
		}
	}

	public static class Criterion implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 296971448655458388L;

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