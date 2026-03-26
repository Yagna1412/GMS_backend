package com.gms.backend.customer.invoices.dto;

import com.gms.backend.customer.invoices.entity.Invoice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class InvoiceDetailDto {


        private String invoiceNumber;

        private String vehicleName;
        private String vehicleNumber;

        private String customerName;
        private String customerAddress;
        private String customerPhone;

        private String serviceType;
        private String serviceLocation;
        private String serviceDate;
        private String serviceTime;

        private Double serviceCharge;
        private Double laborCharge;
        private Double totalAmount;

        private String paymentMethod;
        private String paymentDate;
        private String status;

        public InvoiceDetailDto(Invoice i) {
            this.invoiceNumber = i.getInvoiceNumber();
            this.customerName = i.getCustomerName();
            this.customerAddress = i.getCustomerAddress();
            this.customerPhone = i.getCustomerPhone();
            this.serviceType = i.getServiceType();
            this.serviceLocation = i.getServiceLocation();
            this.serviceDate = i.getServiceDate();
            this.serviceTime = i.getServiceTime();
            this.serviceCharge = i.getServiceCharge();
            this.laborCharge = i.getLaborCharge();
            this.totalAmount = i.getTotalAmount();
            this.paymentMethod = i.getPaymentMethod();
            this.paymentDate = i.getPaymentDate();
            this.status = i.getStatus();
            this.vehicleName=i.getVehicleName();
            this.vehicleNumber=i.getVehicleNumber();
        }
    }

