package com.onejane.multisource.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_apply")
public class TApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 申请人警号
     */
    @Column(name = "applyer_police_num")
    private String applyerPoliceNum;

    /**
     * 申请人姓名
     */
    @Column(name = "applyer_name")
    private String applyerName;

    /**
     * 被讯问人姓名
     */
    @Column(name = "asked_name")
    private String askedName;

    /**
     * 申请人id(警员id)
     */
    @Column(name = "applyer_id")
    private Integer applyerId;

    /**
     * 单位id
     */
    @Column(name = "unit_id")
    private Integer unitId;

    /**
     * 笔录信息ids(警情/案件编号、被询/讯问人姓名、所属单位分组 例如,1,2,3,)
     */
    @Column(name = "case_info_ids")
    private String caseInfoIds;

    /**
     * 笔录份数
     */
    @Column(name = "case_count")
    private Integer caseCount;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 申请状态(0:未申请 1:已申请)
     */
    @Column(name = "apply_state")
    private String applyState;

    /**
     * 申请目的
     */
    @Column(name = "apply_goal")
    private String applyGoal;

    /**
     * 审批时间
     */
    @Column(name = "approve_time")
    private Date approveTime;

    /**
     * 审批状态(0:待审批 1:未通过 2:已通过)
     */
    @Column(name = "approve_state")
    private String approveState;

    /**
     * 审批人id
     */
    @Column(name = "approver_id")
    private Integer approverId;

    /**
     * 审批意见
     */
    @Column(name = "approve_prop")
    private String approveProp;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取申请人警号
     *
     * @return applyer_police_num - 申请人警号
     */
    public String getApplyerPoliceNum() {
        return applyerPoliceNum;
    }

    /**
     * 设置申请人警号
     *
     * @param applyerPoliceNum 申请人警号
     */
    public void setApplyerPoliceNum(String applyerPoliceNum) {
        this.applyerPoliceNum = applyerPoliceNum;
    }

    /**
     * 获取申请人姓名
     *
     * @return applyer_name - 申请人姓名
     */
    public String getApplyerName() {
        return applyerName;
    }

    /**
     * 设置申请人姓名
     *
     * @param applyerName 申请人姓名
     */
    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName;
    }

    /**
     * 获取被讯问人姓名
     *
     * @return asked_name - 被讯问人姓名
     */
    public String getAskedName() {
        return askedName;
    }

    /**
     * 设置被讯问人姓名
     *
     * @param askedName 被讯问人姓名
     */
    public void setAskedName(String askedName) {
        this.askedName = askedName;
    }

    /**
     * 获取申请人id(警员id)
     *
     * @return applyer_id - 申请人id(警员id)
     */
    public Integer getApplyerId() {
        return applyerId;
    }

    /**
     * 设置申请人id(警员id)
     *
     * @param applyerId 申请人id(警员id)
     */
    public void setApplyerId(Integer applyerId) {
        this.applyerId = applyerId;
    }

    /**
     * 获取单位id
     *
     * @return unit_id - 单位id
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * 设置单位id
     *
     * @param unitId 单位id
     */
    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取笔录信息ids(警情/案件编号、被询/讯问人姓名、所属单位分组 例如,1,2,3,)
     *
     * @return case_info_ids - 笔录信息ids(警情/案件编号、被询/讯问人姓名、所属单位分组 例如,1,2,3,)
     */
    public String getCaseInfoIds() {
        return caseInfoIds;
    }

    /**
     * 设置笔录信息ids(警情/案件编号、被询/讯问人姓名、所属单位分组 例如,1,2,3,)
     *
     * @param caseInfoIds 笔录信息ids(警情/案件编号、被询/讯问人姓名、所属单位分组 例如,1,2,3,)
     */
    public void setCaseInfoIds(String caseInfoIds) {
        this.caseInfoIds = caseInfoIds;
    }

    /**
     * 获取笔录份数
     *
     * @return case_count - 笔录份数
     */
    public Integer getCaseCount() {
        return caseCount;
    }

    /**
     * 设置笔录份数
     *
     * @param caseCount 笔录份数
     */
    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    /**
     * 获取申请时间
     *
     * @return apply_time - 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请时间
     *
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取申请状态(0:未申请 1:已申请)
     *
     * @return apply_state - 申请状态(0:未申请 1:已申请)
     */
    public String getApplyState() {
        return applyState;
    }

    /**
     * 设置申请状态(0:未申请 1:已申请)
     *
     * @param applyState 申请状态(0:未申请 1:已申请)
     */
    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    /**
     * 获取申请目的
     *
     * @return apply_goal - 申请目的
     */
    public String getApplyGoal() {
        return applyGoal;
    }

    /**
     * 设置申请目的
     *
     * @param applyGoal 申请目的
     */
    public void setApplyGoal(String applyGoal) {
        this.applyGoal = applyGoal;
    }

    /**
     * 获取审批时间
     *
     * @return approve_time - 审批时间
     */
    public Date getApproveTime() {
        return approveTime;
    }

    /**
     * 设置审批时间
     *
     * @param approveTime 审批时间
     */
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    /**
     * 获取审批状态(0:待审批 1:未通过 2:已通过)
     *
     * @return approve_state - 审批状态(0:待审批 1:未通过 2:已通过)
     */
    public String getApproveState() {
        return approveState;
    }

    /**
     * 设置审批状态(0:待审批 1:未通过 2:已通过)
     *
     * @param approveState 审批状态(0:待审批 1:未通过 2:已通过)
     */
    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }

    /**
     * 获取审批人id
     *
     * @return approver_id - 审批人id
     */
    public Integer getApproverId() {
        return approverId;
    }

    /**
     * 设置审批人id
     *
     * @param approverId 审批人id
     */
    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    /**
     * 获取审批意见
     *
     * @return approve_prop - 审批意见
     */
    public String getApproveProp() {
        return approveProp;
    }

    /**
     * 设置审批意见
     *
     * @param approveProp 审批意见
     */
    public void setApproveProp(String approveProp) {
        this.approveProp = approveProp;
    }
}