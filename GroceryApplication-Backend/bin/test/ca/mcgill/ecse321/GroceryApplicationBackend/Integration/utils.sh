#!/bin/bash
function jsonValue() {
KEY=$1
num=$2
awk -F"[,:}]" '{for(i=1;i<=NF;i++){if($i~/'$KEY'\042/){print $(i+1)}}}' | tr -d '"' | sed -n ${num}p
}

# make sure the db is clear before running integration tests
function clearDataBase() {
    dbname="postgres://hgtnlamfvmogrz:129811b324bf21b8bab5e2bd0a179c0b498942242d64a79643450820a3d81b91@ec2-18-234-17-166.compute-1.amazonaws.com:5432/d720890vea8dg8"
    username="hgtnlamfvmogrz"
PGPASSWORD=129811b324bf21b8bab5e2bd0a179c0b498942242d64a79643450820a3d81b91 psql $dbname $username << EOF
  \o /dev/null
  SET client_min_messages TO WARNING;
	TRUNCATE address CASCADE;
	TRUNCATE category CASCADE;
	TRUNCATE customer CASCADE;
	TRUNCATE employee CASCADE;
	TRUNCATE grocery_order CASCADE;
	TRUNCATE grocery_order_product CASCADE;
	TRUNCATE grocery_store_application CASCADE;
	TRUNCATE grocery_store_application_order CASCADE;
	TRUNCATE grocery_user CASCADE;
	TRUNCATE manager CASCADE;
	TRUNCATE payment CASCADE;
	TRUNCATE product CASCADE;
	TRUNCATE shift CASCADE;
	TRUNCATE store CASCADE;
	TRUNCATE store_address CASCADE;
	TRUNCATE user_role CASCADE;
EOF
}

function initiateIntegrationTesting() {
  clearDataBase

  # create the grocery store application top level entity
  GS_STORE_APP=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/grocerystoreapplication")

  EMAIL="user1@email.com"
  GROCERY_USER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/gorceryUser/?email={$EMAIL}&username=username1&password=Password@1&firstName=Johny&lastName=Sins&date=2022-03-21")

  ADDRESS=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/address/?streetNumber=123&streetName=Avenue&province=Ontario&city=Toronto&country=Canada&postalCode=H4R4T6")
  ADDRESS_ID=$(echo "$ADDRESS" | grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 1)

  CATEGORY=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/category/?image=img1&applicationId=0&name=catname&description=thisisadescription")
  CATEGORY_ID=$(echo "$CATEGORY" | grep -oP "(?<=\"id\":)(.*)(?=})")

  PRODUCT=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/product/?image=this&applicationId=0&categoryId={$CATEGORY_ID}&name=apple&description=thisisanfruitdescription&price=1.45&weight=67&volume=12&availability=IN_STORE&isRefundable=False&avaQuantity=12")
  # shellcheck disable=SC1073
  PRODUCT_BARCODE=$(echo "$PRODUCT" | grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 7 | tail -1)


  EMPLOYEE=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/employee/?hiredDate=2000-10-31&employeeStatus=BANNED&hourlyPay=12.45&email={$EMAIL}&groceryStoreApplicationId=0")
  EMPLOYEE_ID=$( echo "$EMPLOYEE" | jsonValue id | head -n 1)

  STORENAME="MyStore"
  STORE=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/store/?name={$STORENAME}&weekDayOpening=10:00:00&weekDayClosing=13:00:00&weekEndOpening=12:19:00&weekEndClosing=22:00:00&addressId={$ADDRESS_ID}&groceryStoreApplicationId=0")

  SHIFT=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/shift/?day=MONDAY&shiftType=OPENING&employeeId={$EMPLOYEE_ID}")
  SHIFT_ID=$( echo "$SHIFT" | jsonValue id | head -n 1)

  CUSTOMER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/customer/?applicationId=0&addressId={$ADDRESS_ID}&userEmail={$EMAIL}")
  CUSTOMER_ID=$( echo "$CUSTOMER" | jsonValue customerId)
  MANAGER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/manager/?applicationId=0&email={$EMAIL}")
  MANAGER_ID=$(echo $MANAGER | jsonValue managerId)

  ORDER=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/groceryOrder/?applicationId=0&status=INCART&datePlaced=2022-02-21&deliveryDate=2022-02-12&customerNote=yesplz&purchaseType=ONLINE")
  ORDER_ID=$(echo $ORDER | jsonValue orderId)

  PAYMENT=$(curl -s -X POST "https://groceryapplication-backend-l.herokuapp.com/payment/?orderId=${ORDER_ID}&amount=78&paymentType=GIFTCARD&paymentCode=asdasx")
  PAYMENT_ID=$(echo $PAYMENT |  grep -oP "(?<=\:)(.*?)(?=\,)" | head -n 1)
}

