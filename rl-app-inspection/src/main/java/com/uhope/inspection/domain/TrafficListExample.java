package com.uhope.inspection.domain;

import java.util.ArrayList;
import java.util.List;

public class TrafficListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TrafficListExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andAccessoryIsNull() {
            addCriterion("accessory is null");
            return (Criteria) this;
        }

        public Criteria andAccessoryIsNotNull() {
            addCriterion("accessory is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoryEqualTo(String value) {
            addCriterion("accessory =", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotEqualTo(String value) {
            addCriterion("accessory <>", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryGreaterThan(String value) {
            addCriterion("accessory >", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryGreaterThanOrEqualTo(String value) {
            addCriterion("accessory >=", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLessThan(String value) {
            addCriterion("accessory <", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLessThanOrEqualTo(String value) {
            addCriterion("accessory <=", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryLike(String value) {
            addCriterion("accessory like", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotLike(String value) {
            addCriterion("accessory not like", value, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryIn(List<String> values) {
            addCriterion("accessory in", values, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotIn(List<String> values) {
            addCriterion("accessory not in", values, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryBetween(String value1, String value2) {
            addCriterion("accessory between", value1, value2, "accessory");
            return (Criteria) this;
        }

        public Criteria andAccessoryNotBetween(String value1, String value2) {
            addCriterion("accessory not between", value1, value2, "accessory");
            return (Criteria) this;
        }

        public Criteria andInspectionIdIsNull() {
            addCriterion("inspection_id is null");
            return (Criteria) this;
        }

        public Criteria andInspectionIdIsNotNull() {
            addCriterion("inspection_id is not null");
            return (Criteria) this;
        }

        public Criteria andInspectionIdEqualTo(String value) {
            addCriterion("inspection_id =", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdNotEqualTo(String value) {
            addCriterion("inspection_id <>", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdGreaterThan(String value) {
            addCriterion("inspection_id >", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("inspection_id >=", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdLessThan(String value) {
            addCriterion("inspection_id <", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdLessThanOrEqualTo(String value) {
            addCriterion("inspection_id <=", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdLike(String value) {
            addCriterion("inspection_id like", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdNotLike(String value) {
            addCriterion("inspection_id not like", value, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdIn(List<String> values) {
            addCriterion("inspection_id in", values, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdNotIn(List<String> values) {
            addCriterion("inspection_id not in", values, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdBetween(String value1, String value2) {
            addCriterion("inspection_id between", value1, value2, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andInspectionIdNotBetween(String value1, String value2) {
            addCriterion("inspection_id not between", value1, value2, "inspectionId");
            return (Criteria) this;
        }

        public Criteria andTrafficDateIsNull() {
            addCriterion("traffic_date is null");
            return (Criteria) this;
        }

        public Criteria andTrafficDateIsNotNull() {
            addCriterion("traffic_date is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficDateEqualTo(String value) {
            addCriterion("traffic_date =", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateNotEqualTo(String value) {
            addCriterion("traffic_date <>", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateGreaterThan(String value) {
            addCriterion("traffic_date >", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateGreaterThanOrEqualTo(String value) {
            addCriterion("traffic_date >=", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateLessThan(String value) {
            addCriterion("traffic_date <", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateLessThanOrEqualTo(String value) {
            addCriterion("traffic_date <=", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateLike(String value) {
            addCriterion("traffic_date like", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateNotLike(String value) {
            addCriterion("traffic_date not like", value, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateIn(List<String> values) {
            addCriterion("traffic_date in", values, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateNotIn(List<String> values) {
            addCriterion("traffic_date not in", values, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateBetween(String value1, String value2) {
            addCriterion("traffic_date between", value1, value2, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficDateNotBetween(String value1, String value2) {
            addCriterion("traffic_date not between", value1, value2, "trafficDate");
            return (Criteria) this;
        }

        public Criteria andTrafficContentIsNull() {
            addCriterion("traffic_content is null");
            return (Criteria) this;
        }

        public Criteria andTrafficContentIsNotNull() {
            addCriterion("traffic_content is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficContentEqualTo(String value) {
            addCriterion("traffic_content =", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentNotEqualTo(String value) {
            addCriterion("traffic_content <>", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentGreaterThan(String value) {
            addCriterion("traffic_content >", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentGreaterThanOrEqualTo(String value) {
            addCriterion("traffic_content >=", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentLessThan(String value) {
            addCriterion("traffic_content <", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentLessThanOrEqualTo(String value) {
            addCriterion("traffic_content <=", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentLike(String value) {
            addCriterion("traffic_content like", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentNotLike(String value) {
            addCriterion("traffic_content not like", value, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentIn(List<String> values) {
            addCriterion("traffic_content in", values, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentNotIn(List<String> values) {
            addCriterion("traffic_content not in", values, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentBetween(String value1, String value2) {
            addCriterion("traffic_content between", value1, value2, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andTrafficContentNotBetween(String value1, String value2) {
            addCriterion("traffic_content not between", value1, value2, "trafficContent");
            return (Criteria) this;
        }

        public Criteria andOneregionIsNull() {
            addCriterion("oneregion is null");
            return (Criteria) this;
        }

        public Criteria andOneregionIsNotNull() {
            addCriterion("oneregion is not null");
            return (Criteria) this;
        }

        public Criteria andOneregionEqualTo(String value) {
            addCriterion("oneregion =", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionNotEqualTo(String value) {
            addCriterion("oneregion <>", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionGreaterThan(String value) {
            addCriterion("oneregion >", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionGreaterThanOrEqualTo(String value) {
            addCriterion("oneregion >=", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionLessThan(String value) {
            addCriterion("oneregion <", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionLessThanOrEqualTo(String value) {
            addCriterion("oneregion <=", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionLike(String value) {
            addCriterion("oneregion like", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionNotLike(String value) {
            addCriterion("oneregion not like", value, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionIn(List<String> values) {
            addCriterion("oneregion in", values, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionNotIn(List<String> values) {
            addCriterion("oneregion not in", values, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionBetween(String value1, String value2) {
            addCriterion("oneregion between", value1, value2, "oneregion");
            return (Criteria) this;
        }

        public Criteria andOneregionNotBetween(String value1, String value2) {
            addCriterion("oneregion not between", value1, value2, "oneregion");
            return (Criteria) this;
        }
    }

    /**
     */
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