# HR Dashboard API - cURL Commands

**Server URL:** `http://localhost:8080`

---

## Dashboard Overview

### Get Dashboard Overview
```bash
curl -X GET "http://localhost:8080/api/hr/dashboard/overview"
```

### Get Employee Overview
```bash
curl -X GET "http://localhost:8080/api/hr/dashboard/employee-overview"
```

### Get Attendance Overview
```bash
curl -X GET "http://localhost:8080/api/hr/dashboard/attendance-overview"
```

### Get Leave Overview
```bash
curl -X GET "http://localhost:8080/api/hr/dashboard/leave-overview"
```

### Get Grievance Overview
```bash
curl -X GET "http://localhost:8080/api/hr/dashboard/grievance-overview"
```

---

## Employee Management

### Get All Employees
```bash
curl -X GET "http://localhost:8080/api/hr/employees"
```

### Get Employee by ID
```bash
curl -X GET "http://localhost:8080/api/hr/employees/1"
```

### Create New Employee
```bash
curl -X POST "http://localhost:8080/api/hr/employees" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "phone": "1234567890",
    "department": "IT",
    "position": "Software Engineer",
    "joiningDate": "2024-01-15",
    "status": "ACTIVE"
  }'
```

### Update Employee
```bash
curl -X PUT "http://localhost:8080/api/hr/employees/1" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "phone": "1234567890",
    "department": "IT",
    "position": "Senior Software Engineer",
    "joiningDate": "2024-01-15",
    "status": "ACTIVE"
  }'
```

### Delete Employee
```bash
curl -X DELETE "http://localhost:8080/api/hr/employees/1"
```

### Get Employees by Department
```bash
curl -X GET "http://localhost:8080/api/hr/employees/department/IT"
```

### Get Employees by Status
```bash
curl -X GET "http://localhost:8080/api/hr/employees/status/ACTIVE"
```

### Get Total Employee Count
```bash
curl -X GET "http://localhost:8080/api/hr/employees/count/total"
```

### Get Active Employee Count
```bash
curl -X GET "http://localhost:8080/api/hr/employees/count/active"
```

---

## Attendance Management

### Record Attendance
```bash
curl -X POST "http://localhost:8080/api/hr/attendance" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "date": "2024-05-19",
    "status": "PRESENT",
    "checkInTime": "09:00:00",
    "checkOutTime": "18:00:00"
  }'
```

### Get Attendance by ID
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/1"
```

### Get Attendance by Employee
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/employee/1"
```

### Get Attendance by Date
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/date/2024-05-19"
```

### Get Attendance Between Dates
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/employee/1/between?startDate=2024-05-01&endDate=2024-05-31"
```

### Update Attendance
```bash
curl -X PUT "http://localhost:8080/api/hr/attendance/1" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "date": "2024-05-19",
    "status": "PRESENT",
    "checkInTime": "09:30:00",
    "checkOutTime": "18:30:00"
  }'
```

### Delete Attendance
```bash
curl -X DELETE "http://localhost:8080/api/hr/attendance/1"
```

### Get Today's Present Attendance
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/today/present"
```

### Get Today's Absent Attendance
```bash
curl -X GET "http://localhost:8080/api/hr/attendance/today/absent"
```

---

## Leave Management

### Create Leave Request
```bash
curl -X POST "http://localhost:8080/api/hr/leave-requests" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "startDate": "2024-06-01",
    "endDate": "2024-06-05",
    "leaveType": "ANNUAL",
    "reason": "Vacation"
  }'
```

### Get Leave Request by ID
```bash
curl -X GET "http://localhost:8080/api/hr/leave-requests/1"
```

### Get Leave Requests by Employee
```bash
curl -X GET "http://localhost:8080/api/hr/leave-requests/employee/1"
```

### Get Pending Leave Requests
```bash
curl -X GET "http://localhost:8080/api/hr/leave-requests/pending"
```

### Get Pending Requests for Approver
```bash
curl -X GET "http://localhost:8080/api/hr/leave-requests/approver/2/pending"
```

### Approve Leave Request
```bash
curl -X PUT "http://localhost:8080/api/hr/leave-requests/1/approve" \
  -H "Content-Type: application/json" \
  -d '{
    "approvalComments": "Approved"
  }'
```

### Reject Leave Request
```bash
curl -X PUT "http://localhost:8080/api/hr/leave-requests/1/reject" \
  -H "Content-Type: application/json" \
  -d '{
    "rejectionReason": "Cannot approve at this time"
  }'
```

### Delete Leave Request
```bash
curl -X DELETE "http://localhost:8080/api/hr/leave-requests/1"
```

### Get Pending Leave Approval Count
```bash
curl -X GET "http://localhost:8080/api/hr/leave-requests/count/pending"
```

---

## Payroll Management

### Create Payroll
```bash
curl -X POST "http://localhost:8080/api/hr/payroll" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "month": "MAY",
    "baseSalary": 50000,
    "bonus": 5000,
    "deductions": 2000
  }'
```

### Get Payroll by ID
```bash
curl -X GET "http://localhost:8080/api/hr/payroll/1"
```

### Get Payroll by Employee
```bash
curl -X GET "http://localhost:8080/api/hr/payroll/employee/1"
```

### Get Pending Payroll
```bash
curl -X GET "http://localhost:8080/api/hr/payroll/pending"
```

### Process Payroll
```bash
curl -X PUT "http://localhost:8080/api/hr/payroll/1/process" \
  -H "Content-Type: application/json" \
  -d '{
    "processedDate": "2024-05-31",
    "paymentMethod": "BANK_TRANSFER"
  }'
```

### Delete Payroll
```bash
curl -X DELETE "http://localhost:8080/api/hr/payroll/1"
```

### Get Total Payroll by Month
```bash
curl -X GET "http://localhost:8080/api/hr/payroll/total/MAY"
```

---

## Performance Reviews

### Create Performance Review
```bash
curl -X POST "http://localhost:8080/api/hr/performance-reviews" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "reviewerId": 2,
    "rating": 4.5,
    "comments": "Excellent performance this quarter"
  }'
```

### Get Performance Review by ID
```bash
curl -X GET "http://localhost:8080/api/hr/performance-reviews/1"
```

### Get Reviews by Employee
```bash
curl -X GET "http://localhost:8080/api/hr/performance-reviews/employee/1"
```

### Get Pending Reviews for Reviewer
```bash
curl -X GET "http://localhost:8080/api/hr/performance-reviews/reviewer/2/pending"
```

### Submit Performance Review
```bash
curl -X PUT "http://localhost:8080/api/hr/performance-reviews/1/submit" \
  -H "Content-Type: application/json" \
  -d '{
    "rating": 4.5,
    "comments": "Great work overall",
    "submittedDate": "2024-05-19"
  }'
```

### Delete Performance Review
```bash
curl -X DELETE "http://localhost:8080/api/hr/performance-reviews/1"
```

### Get Pending Review Count
```bash
curl -X GET "http://localhost:8080/api/hr/performance-reviews/count/pending"
```

---

## Training Programs

### Create Training Program
```bash
curl -X POST "http://localhost:8080/api/hr/training-programs" \
  -H "Content-Type: application/json" \
  -d '{
    "programName": "Advanced Java Development",
    "description": "Learn advanced Java concepts",
    "startDate": "2024-06-01",
    "endDate": "2024-06-30",
    "instructor": "John Smith",
    "status": "SCHEDULED"
  }'
```

### Get Training Program by ID
```bash
curl -X GET "http://localhost:8080/api/hr/training-programs/1"
```

### Get All Training Programs
```bash
curl -X GET "http://localhost:8080/api/hr/training-programs"
```

### Get Scheduled Training Programs
```bash
curl -X GET "http://localhost:8080/api/hr/training-programs/scheduled"
```

### Get Ongoing Training Programs
```bash
curl -X GET "http://localhost:8080/api/hr/training-programs/ongoing"
```

### Update Training Program
```bash
curl -X PUT "http://localhost:8080/api/hr/training-programs/1" \
  -H "Content-Type: application/json" \
  -d '{
    "programName": "Advanced Java Development",
    "description": "Learn advanced Java concepts and patterns",
    "startDate": "2024-06-01",
    "endDate": "2024-06-30",
    "instructor": "John Smith",
    "status": "ONGOING"
  }'
```

### Delete Training Program
```bash
curl -X DELETE "http://localhost:8080/api/hr/training-programs/1"
```

---

## Grievances

### File Grievance
```bash
curl -X POST "http://localhost:8080/api/hr/grievances" \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "subject": "Workplace issue",
    "description": "Detailed grievance description",
    "severity": "HIGH"
  }'
```

### Get Grievance by ID
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/1"
```

### Get Grievances by Employee
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/employee/1"
```

### Get Open Grievances
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/open"
```

### Get High Priority Grievances
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/high-priority"
```

### Assign Grievance
```bash
curl -X PUT "http://localhost:8080/api/hr/grievances/1/assign" \
  -H "Content-Type: application/json" \
  -d '{
    "assignedTo": 3,
    "assignmentNotes": "Please investigate and report"
  }'
```

### Resolve Grievance
```bash
curl -X PUT "http://localhost:8080/api/hr/grievances/1/resolve" \
  -H "Content-Type: application/json" \
  -d '{
    "resolution": "Issue has been resolved",
    "resolutionDate": "2024-05-19",
    "status": "RESOLVED"
  }'
```

### Delete Grievance
```bash
curl -X DELETE "http://localhost:8080/api/hr/grievances/1"
```

### Get High Severity Grievance Count
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/count/high-severity"
```

### Get Open Grievance Count
```bash
curl -X GET "http://localhost:8080/api/hr/grievances/count/open"
```

---

## Notes

- All API endpoints are located at `http://localhost:8080`
- All requests require `Content-Type: application/json` header for POST/PUT requests
- Replace ID numbers (1, 2, 3, etc.) with actual IDs from your database
- Date formats should follow `YYYY-MM-DD`
- Status values: `ACTIVE`, `INACTIVE`, `ON_LEAVE`, `PRESENT`, `ABSENT`, `SCHEDULED`, `ONGOING`, `COMPLETED`
- Leave types: `ANNUAL`, `SICK`, `CASUAL`, `MATERNITY`, `UNPAID`
- Severity levels: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`

---

**Generated:** May 19, 2026  
**HR Dashboard Backend API Documentation**
