#!/bin/bash

. ./utils.sh --source-only

initiateIntegrationTesting
CITY="Toronto"
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/address/${ADDRESS_ID}")
ADDRESS=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/address/?streetNumber=123&streetName=Avenue&province=Ontario&city=${CITY}&country=Canada&postalCode=H4R4T6")
ID=$(echo "$ADDRESS" | grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 1)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/address/${ID}" | grep -o "$CITY")

if [ -z "$OUTPUT" ]; then
  echo "AddressIntegrationTest Failed"
else
  echo "AddressIntegrationTest Passed"
fi