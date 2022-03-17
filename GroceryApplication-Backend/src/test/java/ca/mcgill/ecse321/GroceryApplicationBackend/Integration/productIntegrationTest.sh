#!/bin/bash

. ./utils.sh --source-only

NAME="apple"

initiateIntegrationTesting
DELETE=$(curl -s -X DELETE "https://groceryapplication-backend-l.herokuapp.com/deleteProduct/${PRODUCT_ID}")
PRODUCT=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/product/?image=this&applicationId=0&categoryId={$CATEGORY_ID}&name=apple&description=thisisanfruitdescription&price=1.45&weight=67&volume=12&availability=IN_STORE&isRefundable=False&avaQuantity=12")
PRODUCT_BARCODE=$(echo "$PRODUCT" | grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 7 | tail -1)
OUTPUT=$(curl -s -X GET "https://groceryapplication-backend-l.herokuapp.com/getProductByBarcode/${PRODUCT_BARCODE}" | grep -o "$NAME")
if [ -z "$OUTPUT" ]; then
  echo "ProductIntegrationTest Failed"
else
  echo "ProductIntegrationTest Passed"
fi