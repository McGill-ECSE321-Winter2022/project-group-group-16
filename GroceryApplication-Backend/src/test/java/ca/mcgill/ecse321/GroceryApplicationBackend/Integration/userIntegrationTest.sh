#!/bin/bash

. ./utils.sh --source-only
initiateIntegrationTesting

EMAIL="user1@email.com"

DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/deleteGroceryUser/${EMAIL}")
GROCERY_USER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/gorceryUser/?email=${EMAIL}&username=username1&password=Password@1&firstName=Johny&lastName=Sins&date=2022-03-21")
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/getGroceryUserbyEmail/${EMAIL}" | grep -o "$EMAIL")

if [ -z "$OUTPUT" ]; then
  echo "UserIntegrationTest Failed"
else
  echo "UserIntegrationTest Passed"
fi