#!/bin/bash

. ./utils.sh --source-only

WEEKDAYOPENING="10:00:00"
initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/store/{$STORENAME}")
STORE=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/store/?name={$STORENAME}&weekDayOpening=10:00:00&weekDayClosing=13:00:00&weekEndOpening=12:19:00&weekEndClosing=22:00:00&addressId={$ADDRESS_ID}&groceryStoreApplicationId=0")
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/store/{$STORENAME}" | grep -o "$WEEKDAYOPENING")

if [ -z "$OUTPUT" ]; then
  echo "StoreIntegrationTest Failed"
else
  echo "StoreIntegrationTest Passed"
fi