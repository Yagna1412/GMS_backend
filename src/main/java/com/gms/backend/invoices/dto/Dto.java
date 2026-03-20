package com.gms.backend.invoices.dto;

public class Dto {



        private Double totalDue;
        private Double totalPaid;
        private Double credit;

    public Dto(double totalDue, double totalPaid, double credit) {
        this.totalDue = totalDue;
        this.totalPaid = totalPaid;
        this.credit = credit;
    }

}
