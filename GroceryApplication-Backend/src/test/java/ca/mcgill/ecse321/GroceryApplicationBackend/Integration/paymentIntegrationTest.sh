#!/bin/bash

. ./utils.sh --source-only

TYPE="GIFTCARD"
initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/payment/${PAYMENT_ID}")



PAYMENT=$(curl -s -X POST "https://groceryapplication-backend-l.heroku/app.com/payment/?orderId=${ORDER_ID}&amount=78&paymentType=GIFTCARD&paymentCode=asdasx")
PAYMENT_ID=$(echo $PAYMENT |  grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 1)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/payment/${PAYMENT_ID}" | grep -o "$TYPE")

if [ -z "$OUTPUT" ]; then
  echo "PaymentIntegrationTest Failed"
else
  echo "PaymentIntegrationTest Passed"
fi