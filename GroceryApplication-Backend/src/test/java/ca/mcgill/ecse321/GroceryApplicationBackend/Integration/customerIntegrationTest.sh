#!/bin/bash

. ./utils.sh --source-only

initiateIntegrationTesting

DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/deleteCustomer/{$CUSTOMER_ID}")
CUSTOMER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/customer/?applicationId=0&addressId={$ADDRESS_ID}&userEmail={$EMAIL}")
CUSTOMER_ID=$( echo "$CUSTOMER" | jsonValue customerId)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/getCustomerById/{$CUSTOMER_ID}" | grep -o "$CUSTOMER_ID")

if [ -z "$OUTPUT" ]; then
  echo "CustomerIntegrationTest Failed"
else
  echo "CustomerIntegrationTest Passed"
fi