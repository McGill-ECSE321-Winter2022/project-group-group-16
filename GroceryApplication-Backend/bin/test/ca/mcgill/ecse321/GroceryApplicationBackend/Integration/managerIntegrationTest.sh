#!/bin/bash

. ./utils.sh --source-only

initiateIntegrationTesting

DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/id/{$MANAGER_ID}")
MANAGER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/manager/?applicationId=0&email={$EMAIL}")
MANAGER_ID=$(echo "$MANAGER" | jsonValue managerId)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/manager/id/{$MANAGER_ID}" | grep -o "$MANAGER_ID")

if [ -z "$OUTPUT" ]; then
  echo "ManagerIntegrationTest Failed"
else
  echo "ManagerIntegrationTest Passed"
fi