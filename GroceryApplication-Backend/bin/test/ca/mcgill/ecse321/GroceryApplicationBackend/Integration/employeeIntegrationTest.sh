#!/bin/bash

. ./utils.sh --source-only

initiateIntegrationTesting
STATUS="ACTIVE"

DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/employee/id/{$EMPLOYEE_ID}")
EMPLOYEE=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/employee/?hiredDate=2000-10-31&employeeStatus={$STATUS}&hourlyPay=12.45&email={$EMAIL}&groceryStoreApplicationId=0")
EMPLOYEE_ID=$( echo "$EMPLOYEE" | jsonValue id | head -n 1)

OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/employee/id/{$EMPLOYEE_ID}" | grep -o "$STATUS")

if [ -z "$OUTPUT" ]; then
  echo "EmployeeIntegrationTest Failed"
else
  echo "EmployeeIntegrationTest Passed"
fi