#!/bin/bash

. ./utils.sh --source-only

DAY="MONDAY"
initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/shift/{$SHIFT_ID}")
SHIFT=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/shift/?day={$DAY}&shiftType=OPENING&employeeId={$EMPLOYEE_ID}")
SHIFT_ID=$( echo "$SHIFT" | jsonValue id | head -n 1)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/shift/{$SHIFT_ID}" | grep -o "$DAY")

if [ -z "$OUTPUT" ]; then
  echo "ShiftIntegrationTest Failed"
else
  echo "ShiftIntegrationTest Passed"
fi