#!/bin/bash

. ./utils.sh --source-only

STATUS="INCART"
initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/groceryOrder/${ORDER_ID}")

ORDER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/groceryOrder/?applicationId=0&status=${STATUS}&datePlaced=2022-02-21&deliveryDate=2022-02-12&customerNote=yesplz&purchaseType=ONLINE")
ORDER_ID=$(echo $ORDER | jsonValue orderId)

OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/groceryOrder/${ORDER_ID}" | grep -o "$STATUS")

if [ -z "$OUTPUT" ]; then
  echo "OrderIntegrationTest Failed"
else
  echo "OrderIntegrationTest Passed"
fi