package com.gms.backend.inventory.dashboard.service;

public interface DashboardService {

    Object getSummary();

    Object getStockStatus();

    Object getPendingActions();

    Object getLowStockItems();

    Object getOutOfStockItems();

    Object search(String q);

    Object getNotifications(boolean isRead);
}