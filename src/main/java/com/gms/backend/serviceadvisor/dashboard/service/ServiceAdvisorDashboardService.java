package com.gms.backend.serviceadvisor.dashboard.service;


import com.gms.backend.serviceadvisor.dashboard.dto.*;

public interface ServiceAdvisorDashboardService {

    DashboardKpiResponseDto getKpis();

    TodayScheduleResponseDto getTodaySchedule();

    PendingEstimationsResponseDto getPendingEstimations();

    ActiveComplaintsResponseDto getActiveComplaints();

    DashboardStatusResponseDto getDashboardStatus();
}