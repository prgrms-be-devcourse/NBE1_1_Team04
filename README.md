# NBE1_1_Team04
8주차 팀 프로젝트 Repository 입니다.

# 요구사항 명세서

## 상품

- 전체 상품을 조회할 수 있다.
- 상품의 이름, 카테고리, 가격, 설명, 상품 이미지를 입력해서 상품을 등록할 수 있다.
- 상품을 수정할 수 있다.
- 상품을 삭제할 수 있다.
- 상품의 이미지를 출력할 수 있다.

## 상품 리뷰

- 물건을 구매한 회원은 해당 물건의 리뷰를 작성할 수 있다.
- 리뷰 제목, 내용, 별점을 입력해서 리뷰를 작성할 수 있다.
- 리뷰를 등록한 회원은 리뷰를 수정할 수 있다.
- 리뷰를 등록한 회원은 리뷰를 삭제할 수 있다.

## 회원

- 사용자는 회원가입할 수 있다.
- 사용자는 로그아웃할 수 있다.
- 관리자는 상품 추가 및 수정, 삭제를 할 수 있다.
    - 사용자는 상품에 대한 추가 및 수정, 삭제를 할 수 없다.

## 주문

- 상품을 조회할 수 있다.
- 주문자는 주문번호로 주문내역을 조회할 수 있다.
- 주문 완료 시 주문번호를 주문자에게 제공한다.
- 주문자는 우편번호 또는 주소를 수정할 수 있다.
- 주문자는 원하는 상품을 추가해서 주문을 생성할 수 있다.
    - 비회원은 주문 시 이메일, 주소, 우편번호를 작성해야 한다.
- 주문은 매일 오후 2시에 전날 오후 2시부터의 주문을 일괄 배송한다.
    - 주문 스케쥴러는 멀티 서버에서도 안정적을 동작한다.

## 결제

- 주문자는 토스 결제 위젯을 통해 결제를 할 수 있다.
- 주문자는 결제 정보를 조회할 수 있다.
- 주문자는 결제를 취소할 수 있다.
# ✅ 개발환경 정리


> 📌 **IDEA**: InteliJ
> 
>📌 **RDBMS**: MySQL
> 
>📌 **Backend**:Spring Boot
> 
>📌 **Data Access** : JPA

# ✅ 코드 컨벤션

## 클래스명

- postfix 에 response, request 붙이기(ex: OrderResponse)
- postfix 에 layer 명 붙이기 (ex: OrderService)

## 변수명

## 깃 커밋 규칙

### 커밋 타이틀

- `feat` : 새로운 기능 추가, 기존의 기능을 요구 사항에 맞추어 수정
- fix : 기능에 대한 버그 수정
- build : 빌드 관련 수정
- chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
- ci : CI 관련 설정 수정
- docs : 문서(주석) 수정
- style : 코드 스타일, 포맷팅에 대한 수정
- refactor : 기능의 변화가 아닌 코드 리팩터링 ex) 변수 이름 변경
- test : 테스트 코드 추가/수정
- release : 버전 릴리즈

### 커밋 바디
- “-” 붙여서 상세 추가/변경 내역 붙이기
- 예시:

    ```
    build : 구글 컨벤션 및 spotless 적용
    
    - task를 통해 커밋 전에 구글 컨벤션 체크 및 적용
    ```

# ✅ ERD

![image](https://github.com/user-attachments/assets/ba1381d7-b0ec-4aa3-b432-d0d8c852c394)



# ✅ DDL

주문(Orders)

```sql
CREATE TABLE `orders` (
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `postcode` varchar(255) NOT NULL,
  `order_id` binary(16) NOT NULL,
  `order_status` enum('ACCEPTED','CANCELLED','PAYMENT_CONFIRMED','READY_FOR_DELIVERY','SETTLED','SHIPPED') NOT NULL,
  PRIMARY KEY (`order_id`)
)
```

## 회원(User)

```sql
CREATE TABLE users
(
   `user_id` binary(16) NOT NULL PRIMARY KEY,
   `email` varchar(30) NOT NULL,
   `password` varchar(100) NOT NULL,
   `role` varchar(30) NOT NULL,
   `address` varchar(200) NOT NULL,
   `postcode` varchar(200) NOT NULL,
   `created_at` datetime(6) NOT NULL,
   `updated_at` datetime(6) NOT NULL
);
```

# ShedLock

```sql
CREATE TABLE `shedlock` (
  `name` varchar(64) NOT NULL,
  `lock_until` timestamp(3) NOT NULL,
  `locked_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `locked_by` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
)
```

## 상품(products)

```sql
CREATE TABLE products
(
    product_id   BINARY(16) PRIMARY KEY,
    product_name VARCHAR(20) NOT NULL,
    category     VARCHAR(50) NOT NULL,
    price        bigint      NOT NULL,
    description  VARCHAR(500) DEFAULT NULL,
    created_at   datetime(6) NOT NULL,
    updated_at   datetime(6)  DEFAULT NULL
);
```

## 상품 이미지(product_images)

```sql
CREATE TABLE product_images
(
		product_image_id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_id 		binary(16)  NOT NULL,
    saved_path      VARCHAR(300) NOT NULL,
    image_name     	VARCHAR(100) NOT NULL,
    CONSTRAINT fk_product_image_to_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);
```

## 상품 리뷰(reviews)

```sql
CREATE TABLE reviews(
	review_id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_id  binary(16)  NOT NULL,
    user_id binary(16) not null,
    review_name VARCHAR(50) NOT NULL,
    content VARCHAR(500) NOT NULL,
    rate double not null,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL,
    CONSTRAINT fk_review_to_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_review_to_user FOREIGN KEY (user_id) REFERENCES user (user_id)
);
```

## 결제(Payments)

```sql
CREATE TABLE payments (
    payment_id VARBINARY(255) NOT NULL PRIMARY KEY,
    order_id BINARY(16) NOT NULL UNIQUE,
    toss_order_id VARCHAR(255) NOT NULL,
    toss_payment_key VARCHAR(255) NOT NULL UNIQUE,
    toss_payment_method ENUM('가상계좌','간편결제','게임문화상품권','계좌이체','도서문화상품권','문화상품권','카드','휴대폰') NOT NULL,
    toss_payment_status ENUM('ABORTED','CANCELED','DONE','EXPIRED','IN_PROGRESS','PARTIAL_CANCELED','READY','WAITING_FOR_DEPOSIT') NOT NULL,
    total_amount BIGINT NOT NULL,
    approved_at DATETIME(6) DEFAULT NULL,
    requested_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

```