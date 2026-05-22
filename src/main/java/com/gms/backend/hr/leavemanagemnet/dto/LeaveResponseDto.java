package com.gms.backend.hr.leavemanagemnet.dto;

import com.gms.backend.hr.leavemanagemnet.enums.LeaveStatus;

import java.time.LocalDate;

public class LeaveResponseDto {

    private Long id;

    private Integer staffId;

    private String leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer days;

    private String reason;

    private LeaveStatus status;

    public LeaveResponseDto() {
    }

    public LeaveResponseDto(
            Long id,
            Integer staffId,
            String leaveType,
            LocalDate fromDate,
            LocalDate toDate,
            Integer days,
            String reason,
            LeaveStatus status
    ) {
        this.id = id;
        this.staffId = staffId;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.days = days;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getDays() {
        return days;
    }

    public String getReason() {
        return reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}