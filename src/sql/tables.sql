CREATE TABLE "Book" (
  "book_id" serial NOT NULL,
  "name" TEXT NOT NULL,
  "original_name" TEXT,
  "pages" int NOT NULL,
  "release_date" int NOT NULL,
  "publisher" TEXT NOT NULL,
  "weight" int NOT NULL,
  "height" int NOT NULL,
  "width" int NOT NULL,
  "description" TEXT NOT NULL,
  "ISBN" int NOT NULL,
  "material" TEXT,
  "language" TEXT NOT NULL,
  "author" TEXT NOT NULL,
  "circulation" int,
  "price" double precision NOT NULL,
  "status" int NOT NULL,
  CONSTRAINT Book_pk PRIMARY KEY ("book_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Review" (
  "review_id" serial NOT NULL,
  "message" TEXT,
  "rate" int NOT NULL DEFAULT '0',
  "book_id" bigint NOT NULL,
  "user_id" bigint NOT NULL,
  "create_date" TIMESTAMP NOT NULL,
  "update_date" TIMESTAMP,
  CONSTRAINT Review_pk PRIMARY KEY ("review_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "User" (
  "user_id" serial NOT NULL UNIQUE,
  "first_name" TEXT NOT NULL,
  "last_name" TEXT,
  "gender" int,
  "login" TEXT NOT NULL UNIQUE,
  "password" TEXT NOT NULL,
  "email" TEXT NOT NULL UNIQUE,
  "phone" TEXT NOT NULL,
  CONSTRAINT User_pk PRIMARY KEY ("user_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Role" (
  "role_id" serial NOT NULL,
  "description" TEXT,
  CONSTRAINT Role_pk PRIMARY KEY ("role_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Address" (
  "address_id" serial NOT NULL,
  "user_id" bigint NOT NULL,
  "country" TEXT NOT NULL,
  "city" TEXT NOT NULL,
  "street" TEXT NOT NULL,
  "home" TEXT,
  "apartment" TEXT,
  "floor" int,
  "post_code" int NOT NULL,
  CONSTRAINT Address_pk PRIMARY KEY ("address_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Category" (
  "category_id" serial NOT NULL,
  "name" TEXT NOT NULL,
  CONSTRAINT Category_pk PRIMARY KEY ("category_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Coupon" (
  "coupon_id" serial NOT NULL,
  "description" TEXT,
  "coupon_percentage" double precision NOT NULL,
  "start_date" TIMESTAMP NOT NULL,
  "stop_date" TIMESTAMP NOT NULL,
  "status" int NOT NULL,
  CONSTRAINT Coupon_pk PRIMARY KEY ("coupon_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Order" (
  "order_id" serial NOT NULL,
  "user_id" bigint NOT NULL,
  "payment_id" bigint NOT NULL,
  "order_date" TIMESTAMP NOT NULL,
  "executed_date" TIMESTAMP,
  "status" int NOT NULL,
  "total_price" double precision NOT NULL,
  "country" TEXT NOT NULL,
  "city" TEXT NOT NULL,
  "street" TEXT NOT NULL,
  "home" TEXT,
  "apartment" TEXT,
  "floor" int,
  "post_code" int NOT NULL,
  "coupon_id" bigint,
  CONSTRAINT Order_pk PRIMARY KEY ("order_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Book_category" (
  "book_id" bigint NOT NULL,
  "category_id" bigint NOT NULL
) WITH (
OIDS=FALSE
);



CREATE TABLE "Payment" (
  "payment_id" serial NOT NULL,
  "name" TEXT NOT NULL,
  "description" TEXT,
  CONSTRAINT Payment_pk PRIMARY KEY ("payment_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Order_detail" (
  "od_id" serial NOT NULL,
  "order_id" bigint NOT NULL,
  "book_id" bigint NOT NULL,
  "amount" int NOT NULL DEFAULT '0',
  "price" double precision NOT NULL,
  CONSTRAINT Order_detail_pk PRIMARY KEY ("od_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Order_payment" (
  "op_id" serial NOT NULL,
  "payment_id" bigint NOT NULL,
  "transaction_id" bigint NOT NULL,
  "status" int NOT NULL,
  "order_id" bigint NOT NULL,
  CONSTRAINT Order_payment_pk PRIMARY KEY ("op_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Discount" (
  "discount_id" serial NOT NULL,
  "book_id" bigint NOT NULL,
  "description" TEXT,
  "start_date" TIMESTAMP NOT NULL,
  "stop_date" TIMESTAMP NOT NULL,
  "discount_percentage" double precision NOT NULL,
  "status" int NOT NULL,
  CONSTRAINT Discount_pk PRIMARY KEY ("discount_id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "Wish" (
  "user_id" bigint NOT NULL,
  "book_id" bigint NOT NULL
) WITH (
OIDS=FALSE
);



CREATE TABLE "User_role" (
  "user_id" bigint NOT NULL,
  "role_id" bigint NOT NULL
) WITH (
OIDS=FALSE
);




ALTER TABLE "Review" ADD CONSTRAINT "Review_fk0" FOREIGN KEY ("book_id") REFERENCES "Book"("book_id");
ALTER TABLE "Review" ADD CONSTRAINT "Review_fk1" FOREIGN KEY ("user_id") REFERENCES "User"("user_id");



ALTER TABLE "Address" ADD CONSTRAINT "Address_fk0" FOREIGN KEY ("user_id") REFERENCES "User"("user_id");



ALTER TABLE "Order" ADD CONSTRAINT "Order_fk0" FOREIGN KEY ("user_id") REFERENCES "User"("user_id");
ALTER TABLE "Order" ADD CONSTRAINT "Order_fk1" FOREIGN KEY ("payment_id") REFERENCES "Payment"("payment_id");
ALTER TABLE "Order" ADD CONSTRAINT "Order_fk2" FOREIGN KEY ("coupon_id") REFERENCES "Coupon"("coupon_id");

ALTER TABLE "Book_category" ADD CONSTRAINT "Book_category_fk0" FOREIGN KEY ("book_id") REFERENCES "Book"("book_id");
ALTER TABLE "Book_category" ADD CONSTRAINT "Book_category_fk1" FOREIGN KEY ("category_id") REFERENCES "Category"("category_id");


ALTER TABLE "Order_detail" ADD CONSTRAINT "Order_detail_fk0" FOREIGN KEY ("order_id") REFERENCES "Order"("order_id");
ALTER TABLE "Order_detail" ADD CONSTRAINT "Order_detail_fk1" FOREIGN KEY ("book_id") REFERENCES "Book"("book_id");

ALTER TABLE "Order_payment" ADD CONSTRAINT "Order_payment_fk0" FOREIGN KEY ("payment_id") REFERENCES "Payment"("payment_id");
ALTER TABLE "Order_payment" ADD CONSTRAINT "Order_payment_fk1" FOREIGN KEY ("order_id") REFERENCES "Order"("order_id");

ALTER TABLE "Discount" ADD CONSTRAINT "Discount_fk0" FOREIGN KEY ("book_id") REFERENCES "Book"("book_id");

ALTER TABLE "Wish" ADD CONSTRAINT "Wish_fk0" FOREIGN KEY ("user_id") REFERENCES "User"("user_id");
ALTER TABLE "Wish" ADD CONSTRAINT "Wish_fk1" FOREIGN KEY ("book_id") REFERENCES "Book"("book_id");

ALTER TABLE "User_role" ADD CONSTRAINT "User_role_fk0" FOREIGN KEY ("user_id") REFERENCES "User"("user_id");
ALTER TABLE "User_role" ADD CONSTRAINT "User_role_fk1" FOREIGN KEY ("role_id") REFERENCES "Role"("role_id");
