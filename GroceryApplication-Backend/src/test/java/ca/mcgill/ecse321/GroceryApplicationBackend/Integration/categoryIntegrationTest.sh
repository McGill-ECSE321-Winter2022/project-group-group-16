#!/bin/bash

. ./utils.sh --source-only

DESCRIPTION="thisisadescription"

initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/deleteCategory/${CATEGORY_ID}")
CATEGORY=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/category/?image=img1&applicationId=0&name=catname&description=thisisadescription")
ID=$(echo "$CATEGORY" | grep -oP "(?<=\"id\":)(.*)(?=})")
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/getCategory/${ID}" | grep -o "$DESCRIPTION")

if [ -z "$OUTPUT" ]; then
  echo "CategoryIntegrationTest Failed"
else
  echo "CategoryIntegrationTest Passed"
fi