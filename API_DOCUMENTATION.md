# HR Management System - Backend APIs

## Overview
Complete REST API implementation for HR Dashboard with comprehensive employee management, attendance, leave, payroll, performance, training, and grievance modules.

---

## API Endpoints

### 1. Employee Management APIs (`/api/hr/employees`)

#### Create Employee
- **POST** `/api/hr/employees`
- **Body**: EmployeeDTO with firstName, lastName, email, phone, department, position, joiningDate, status
- **Response**: Created EmployeeDTO with ID
- **Status**: 201 CREATED

#### Get Employee by ID
- **GET** `/api/hr/employees/{id}`
- **Response**: EmployeeDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get All Employees
- **GET** `/api/hr/employees`
- **Response**: List of EmployeeDTO
- **Status**: 200 OK

#### Update Employee
- **PUT** `/api/hr/employees/{id}`
- **Body**: Updated EmployeeDTO
- **Response**: Updated EmployeeDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Delete Employee
- **DELETE** `/api/hr/employees/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get Employees by Department
- **GET** `/api/hr/employees/department/{department}`
- **Response**: List of EmployeeDTO
- **Status**: 200 OK

#### Get Employees by Status
- **GET** `/api/hr/employees/status/{status}`
- **Response**: List of EmployeeDTO
- **Status**: 200 OK

#### Get Total Employees Count
- **GET** `/api/hr/employees/count/total`
- **Response**: Long count
- **Status**: 200 OK

#### Get Active Employees Count
- **GET** `/api/hr/employees/count/active`
- **Response**: Long count
- **Status**: 200 OK

---

### 2. Attendance Management APIs (`/api/hr/attendance`)

#### Record Attendance
- **POST** `/api/hr/attendance`
- **Body**: AttendanceDTO with employeeId, attendanceDate, status, checkInTime, checkOutTime
- **Response**: Created AttendanceDTO
- **Status**: 201 CREATED

#### Get Attendance by ID
- **GET** `/api/hr/attendance/{id}`
- **Response**: AttendanceDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get Employee Attendance
- **GET** `/api/hr/attendance/employee/{employeeId}`
- **Response**: List of AttendanceDTO
- **Status**: 200 OK

#### Get Attendance by Date
- **GET** `/api/hr/attendance/date/{date}` (format: YYYY-MM-DD)
- **Response**: List of AttendanceDTO
- **Status**: 200 OK

#### Get Employee Attendance Between Dates
- **GET** `/api/hr/attendance/employee/{employeeId}/between`
- **Query Params**: startDate, endDate (format: YYYY-MM-DD)
- **Response**: List of AttendanceDTO
- **Status**: 200 OK

#### Update Attendance
- **PUT** `/api/hr/attendance/{id}`
- **Body**: Updated AttendanceDTO
- **Response**: Updated AttendanceDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Delete Attendance
- **DELETE** `/api/hr/attendance/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get Today's Present Count
- **GET** `/api/hr/attendance/today/present`
- **Response**: Long count
- **Status**: 200 OK

#### Get Today's Absent Count
- **GET** `/api/hr/attendance/today/absent`
- **Response**: Long count
- **Status**: 200 OK

---

### 3. Leave Management APIs (`/api/hr/leave-requests`)

#### Create Leave Request
- **POST** `/api/hr/leave-requests`
- **Body**: LeaveRequestDTO with employeeId, leaveType, startDate, endDate, reason
- **Response**: Created LeaveRequestDTO (auto-calculates numberOfDays)
- **Status**: 201 CREATED

#### Get Leave Request by ID
- **GET** `/api/hr/leave-requests/{id}`
- **Response**: LeaveRequestDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get Employee Leave Requests
- **GET** `/api/hr/leave-requests/employee/{employeeId}`
- **Response**: List of LeaveRequestDTO
- **Status**: 200 OK

#### Get Pending Leave Requests
- **GET** `/api/hr/leave-requests/pending`
- **Response**: List of LeaveRequestDTO with PENDING status
- **Status**: 200 OK

#### Get Approver's Pending Requests
- **GET** `/api/hr/leave-requests/approver/{approverId}/pending`
- **Response**: List of LeaveRequestDTO
- **Status**: 200 OK

#### Approve Leave Request
- **PUT** `/api/hr/leave-requests/{id}/approve`
- **Query Params**: approverId
- **Response**: Approved LeaveRequestDTO
- **Status**: 200 OK

#### Reject Leave Request
- **PUT** `/api/hr/leave-requests/{id}/reject`
- **Query Params**: approverId
- **Response**: Rejected LeaveRequestDTO
- **Status**: 200 OK

#### Delete Leave Request
- **DELETE** `/api/hr/leave-requests/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get Pending Leave Approvals Count
- **GET** `/api/hr/leave-requests/count/pending`
- **Response**: Long count
- **Status**: 200 OK

---

### 4. Payroll Management APIs (`/api/hr/payroll`)

#### Create Payroll
- **POST** `/api/hr/payroll`
- **Body**: PayrollDTO with employeeId, payrollMonth, basicSalary, allowances, deductions
- **Response**: Created PayrollDTO (auto-calculates netSalary)
- **Status**: 201 CREATED

#### Get Payroll by ID
- **GET** `/api/hr/payroll/{id}`
- **Response**: PayrollDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get Employee Payroll
- **GET** `/api/hr/payroll/employee/{employeeId}`
- **Response**: List of PayrollDTO
- **Status**: 200 OK

#### Get Pending Payrolls
- **GET** `/api/hr/payroll/pending`
- **Response**: List of PayrollDTO with PENDING status
- **Status**: 200 OK

#### Process Payroll
- **PUT** `/api/hr/payroll/{id}/process`
- **Response**: Processed PayrollDTO
- **Status**: 200 OK

#### Delete Payroll
- **DELETE** `/api/hr/payroll/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get Total Payroll for Month
- **GET** `/api/hr/payroll/total/{month}` (format: YYYY-MM)
- **Response**: BigDecimal total
- **Status**: 200 OK

---

### 5. Performance Review APIs (`/api/hr/performance-reviews`)

#### Create Performance Review
- **POST** `/api/hr/performance-reviews`
- **Body**: PerformanceReviewDTO with employeeId, reviewerId, reviewDate
- **Response**: Created PerformanceReviewDTO
- **Status**: 201 CREATED

#### Get Performance Review by ID
- **GET** `/api/hr/performance-reviews/{id}`
- **Response**: PerformanceReviewDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get Employee Performance Reviews
- **GET** `/api/hr/performance-reviews/employee/{employeeId}`
- **Response**: List of PerformanceReviewDTO
- **Status**: 200 OK

#### Get Reviewer's Pending Reviews
- **GET** `/api/hr/performance-reviews/reviewer/{reviewerId}/pending`
- **Response**: List of pending PerformanceReviewDTO
- **Status**: 200 OK

#### Submit Performance Review
- **PUT** `/api/hr/performance-reviews/{id}/submit`
- **Body**: PerformanceReviewDTO with rating (1-5) and feedback
- **Response**: Submitted PerformanceReviewDTO
- **Status**: 200 OK

#### Delete Performance Review
- **DELETE** `/api/hr/performance-reviews/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get Pending Reviews Count
- **GET** `/api/hr/performance-reviews/count/pending`
- **Response**: Long count
- **Status**: 200 OK

---

### 6. Training Program APIs (`/api/hr/training-programs`)

#### Create Training Program
- **POST** `/api/hr/training-programs`
- **Body**: TrainingProgramDTO with programName, startDate, endDate, trainer, location, description, capacity
- **Response**: Created TrainingProgramDTO
- **Status**: 201 CREATED

#### Get Training Program by ID
- **GET** `/api/hr/training-programs/{id}`
- **Response**: TrainingProgramDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get All Training Programs
- **GET** `/api/hr/training-programs`
- **Response**: List of TrainingProgramDTO
- **Status**: 200 OK

#### Get Scheduled Trainings
- **GET** `/api/hr/training-programs/scheduled`
- **Response**: List of SCHEDULED TrainingProgramDTO
- **Status**: 200 OK

#### Get Ongoing Trainings
- **GET** `/api/hr/training-programs/ongoing`
- **Response**: List of ONGOING TrainingProgramDTO
- **Status**: 200 OK

#### Update Training Program
- **PUT** `/api/hr/training-programs/{id}`
- **Body**: Updated TrainingProgramDTO
- **Response**: Updated TrainingProgramDTO
- **Status**: 200 OK

#### Delete Training Program
- **DELETE** `/api/hr/training-programs/{id}`
- **Response**: Success message
- **Status**: 200 OK

---

### 7. Grievance Management APIs (`/api/hr/grievances`)

#### Create Grievance
- **POST** `/api/hr/grievances`
- **Body**: GrievanceDTO with employeeId, grievanceDate, grievanceType, severity, description
- **Response**: Created GrievanceDTO
- **Status**: 201 CREATED

#### Get Grievance by ID
- **GET** `/api/hr/grievances/{id}`
- **Response**: GrievanceDTO
- **Status**: 200 OK or 404 NOT FOUND

#### Get Employee Grievances
- **GET** `/api/hr/grievances/employee/{employeeId}`
- **Response**: List of GrievanceDTO
- **Status**: 200 OK

#### Get Open Grievances
- **GET** `/api/hr/grievances/open`
- **Response**: List of OPEN GrievanceDTO
- **Status**: 200 OK

#### Get High Priority Grievances
- **GET** `/api/hr/grievances/high-priority`
- **Response**: List of HIGH severity OPEN GrievanceDTO
- **Status**: 200 OK

#### Assign Grievance
- **PUT** `/api/hr/grievances/{id}/assign`
- **Query Params**: assignedTo (employee ID)
- **Response**: Assigned GrievanceDTO
- **Status**: 200 OK

#### Resolve Grievance
- **PUT** `/api/hr/grievances/{id}/resolve`
- **Query Params**: resolutionNotes
- **Response**: Resolved GrievanceDTO
- **Status**: 200 OK

#### Delete Grievance
- **DELETE** `/api/hr/grievances/{id}`
- **Response**: Success message
- **Status**: 200 OK

#### Get High Severity Grievances Count
- **GET** `/api/hr/grievances/count/high-severity`
- **Response**: Long count
- **Status**: 200 OK

#### Get Open Grievances Count
- **GET** `/api/hr/grievances/count/open`
- **Response**: Long count
- **Status**: 200 OK

---

### 8. Dashboard APIs (`/api/hr/dashboard`)

#### Get Dashboard Overview
- **GET** `/api/hr/dashboard/overview`
- **Response**: JSON object with aggregated stats:
  - employeeStats (total, active, inactive)
  - attendanceStats (presentToday, absentToday)
  - leaveStats (pendingApprovals)
  - performanceStats (pendingReviews)
  - grievanceStats (open, highSeverity)
- **Status**: 200 OK

#### Get Employee Overview
- **GET** `/api/hr/dashboard/employee-overview`
- **Response**: Employee statistics summary
- **Status**: 200 OK

#### Get Attendance Overview
- **GET** `/api/hr/dashboard/attendance-overview`
- **Response**: Attendance statistics with percentage
- **Status**: 200 OK

#### Get Leave Overview
- **GET** `/api/hr/dashboard/leave-overview`
- **Response**: Leave request statistics
- **Status**: 200 OK

#### Get Grievance Overview
- **GET** `/api/hr/dashboard/grievance-overview`
- **Response**: Grievance statistics
- **Status**: 200 OK

---

## DTOs Summary

### EmployeeDTO
- id, firstName, lastName, email, phone, department, position, joiningDate, status, managerId

### AttendanceDTO
- id, employeeId, attendanceDate, checkInTime, checkOutTime, status, remarks

### LeaveRequestDTO
- id, employeeId, leaveType, startDate, endDate, numberOfDays, reason, status, approverId, approvalDate

### PayrollDTO
- id, employeeId, payrollMonth, basicSalary, allowances, deductions, netSalary, status, processedDate

### PerformanceReviewDTO
- id, employeeId, reviewerId, reviewDate, rating, feedback, status, reviewPeriod

### TrainingProgramDTO
- id, programName, startDate, endDate, trainer, location, description, status, capacity

### GrievanceDTO
- id, employeeId, grievanceDate, grievanceType, severity, description, status, assignedTo, resolutionDate, resolutionNotes

---

## Status Codes

- **200 OK**: Successful GET, PUT, DELETE request
- **201 CREATED**: Successful POST request
- **400 BAD_REQUEST**: Invalid input data
- **404 NOT_FOUND**: Resource not found
- **500 INTERNAL_SERVER_ERROR**: Server error

---

## Exception Handling

All endpoints follow standard REST error handling with meaningful error messages in the response body.

---

## Base URL
```
http://localhost:8080
```

## Notes
- All endpoints have CORS enabled for all origins (*)
- All endpoints return JSON responses
- Date format: YYYY-MM-DD
- DateTime format: YYYY-MM-DDTHH:mm:ss
