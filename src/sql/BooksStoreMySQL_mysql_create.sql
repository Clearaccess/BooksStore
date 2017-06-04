CREATE TABLE `Book` (
	`book_id` bigint NOT NULL AUTO_INCREMENT,
	`title` varchar(100),
	`circulation` int,
	`paper` varchar(100),
	`form` varchar(100),
	`weight` varchar(100),
	`publisher` varchar(100),
	`ISBN` varchar(100) NOT NULL,
	`author` varchar(100),
	`pages` varchar(100),
	`age_limit` varchar(100),
	`series` varchar(100),
	`cover` varchar(100),
	`release_date` varchar(100),
	`category_id` bigint NOT NULL,
    `price` double NOT NULL,
    `description` varchar(1000),
	PRIMARY KEY (`book_id`)
);

CREATE TABLE `User` (
	`user_id` bigint NOT NULL AUTO_INCREMENT,
	`first_name` varchar(50) NOT NULL,
	`last_name` varchar(50) NOT NULL,
	`login` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`email` varchar(1000) NOT NULL,
	`phone` varchar(50) NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `User_role` (
	`user_id` bigint NOT NULL,
	`role_id` bigint NOT NULL
);

CREATE TABLE `Role` (
	`role_id` bigint NOT NULL AUTO_INCREMENT,
	`description` varchar(50) NOT NULL,
	PRIMARY KEY (`role_id`)
);

CREATE TABLE `Address` (
	`address_id` bigint NOT NULL AUTO_INCREMENT,
	`user_id` bigint NOT NULL,
	`country` varchar(50) NOT NULL,
	`city` varchar(50) NOT NULL,
	`street` varchar(50) NOT NULL,
	`home` varchar(50),
	`apartment` varchar(50),
	`floor` varchar(50),
	`post_code` varchar(50) NOT NULL,
	PRIMARY KEY (`address_id`)
);

CREATE TABLE `Review` (
	`review_id` bigint NOT NULL AUTO_INCREMENT,
	`message` varchar(1000) NOT NULL,
	`rate` int NOT NULL,
	`book_id` bigint NOT NULL,
	`user_id` bigint NOT NULL,
	`create_date` TIMESTAMP NOT NULL,
	`update_date` TIMESTAMP,
	PRIMARY KEY (`review_id`)
);

CREATE TABLE `Wish` (
	`user_id` bigint NOT NULL,
	`book_id` bigint NOT NULL
);

CREATE TABLE `Category` (
	`category_id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`category_id`)
);

CREATE TABLE `Coupon` (
	`coupon_id` bigint NOT NULL AUTO_INCREMENT,
	`description` varchar(50),
	`percentage` double NOT NULL,
	`start_date` TIMESTAMP NOT NULL,
	`stop_date` TIMESTAMP NOT NULL,
	`status` int NOT NULL,
	PRIMARY KEY (`coupon_id`)
);

CREATE TABLE `Discount` (
	`discount_id` bigint NOT NULL AUTO_INCREMENT, 
	`book_id` bigint NOT NULL,
	`desription` varchar(50),
	`start_date` TIMESTAMP NOT NULL,
	`stop_date` TIMESTAMP NOT NULL,
	`percentage` double NOT NULL,
	`status` int NOT NULL,
	PRIMARY KEY (`discount_id`)
);

CREATE TABLE `Order` (
	`order_id` bigint NOT NULL AUTO_INCREMENT,
	`user_id` bigint NOT NULL,
	`payment_id` bigint NOT NULL,
	`order_date` TIMESTAMP NOT NULL,
	`executed_date` TIMESTAMP NOT NULL,
	`status` int NOT NULL,
	`total_price` double NOT NULL,
	`country` varchar(50) NOT NULL,
	`city` varchar(50) NOT NULL,
	`street` varchar(50) NOT NULL,
	`home` varchar(50),
	`apartment` varchar(50),
	`floor` varchar(50),
	`post_code` varchar(50) NOT NULL,
	`coupon_id` bigint NOT NULL,
	PRIMARY KEY (`order_id`)
);

CREATE TABLE `Payment` (
	`payment_id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`description` varchar(50),
	PRIMARY KEY (`payment_id`)
);

CREATE TABLE `Order_payment` (
	`op_id` bigint NOT NULL AUTO_INCREMENT,
	`payment_id` bigint NOT NULL,
	`transation_id` bigint NOT NULL,
	`status` int NOT NULL,
	`order_id` bigint NOT NULL,
	PRIMARY KEY (`op_id`)
);

CREATE TABLE `Order_detail` (
	`od_id` bigint NOT NULL AUTO_INCREMENT,
	`order_id` bigint NOT NULL,
	`book_id` bigint NOT NULL,
	`amount` int NOT NULL,
	`price` double NOT NULL,
	PRIMARY KEY (`od_id`)
);

ALTER TABLE `Book` ADD CONSTRAINT `Book_fk0` FOREIGN KEY (`category_id`) REFERENCES `Category`(`category_id`);

ALTER TABLE `User_role` ADD CONSTRAINT `User_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `User_role` ADD CONSTRAINT `User_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `Role`(`role_id`);

ALTER TABLE `Address` ADD CONSTRAINT `Address_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Review` ADD CONSTRAINT `Review_fk0` FOREIGN KEY (`book_id`) REFERENCES `Book`(`book_id`);

ALTER TABLE `Review` ADD CONSTRAINT `Review_fk1` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Wish` ADD CONSTRAINT `Wish_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Wish` ADD CONSTRAINT `Wish_fk1` FOREIGN KEY (`book_id`) REFERENCES `Book`(`book_id`);

ALTER TABLE `Discount` ADD CONSTRAINT `Discount_fk0` FOREIGN KEY (`book_id`) REFERENCES `Book`(`book_id`);

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk0` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`);

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk1` FOREIGN KEY (`payment_id`) REFERENCES `Payment`(`payment_id`);

ALTER TABLE `Order` ADD CONSTRAINT `Order_fk2` FOREIGN KEY (`coupon_id`) REFERENCES `Coupon`(`coupon_id`);

ALTER TABLE `Order_payment` ADD CONSTRAINT `Order_payment_fk0` FOREIGN KEY (`payment_id`) REFERENCES `Payment`(`payment_id`);

ALTER TABLE `Order_payment` ADD CONSTRAINT `Order_payment_fk1` FOREIGN KEY (`order_id`) REFERENCES `Order`(`order_id`);

ALTER TABLE `Order_detail` ADD CONSTRAINT `Order_detail_fk0` FOREIGN KEY (`order_id`) REFERENCES `Order`(`order_id`);

ALTER TABLE `Order_detail` ADD CONSTRAINT `Order_detail_fk1` FOREIGN KEY (`book_id`) REFERENCES `Book`(`book_id`);

