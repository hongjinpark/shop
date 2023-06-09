

# 쇼핑몰 프로젝트

- 쇼핑몰과 문의기능을 활용한 웹애플리케이션 프로젝트입니다.
- 백과 프론트를 분리한 **Decoupled Architecture**입니다.
    - 백과 프론트를 분리하지 않은 **Monolithic Architecture**는 설정이 쉽다는 장점이 있지만, 프로그램의 확장이나 유지보수를 빠르게 하는 것에는 어려움이 있습니다. 또한 자원 최적화나 기술 전환 측면에서 용이하여 프로젝트를 분리하는 선택을 했습니다.
    
- 백엔드 - https://github.com/hongjinpark/shop
- 프론트엔드 - https://github.com/hongjinpark/shop_web

- 세부구현기능
    - 마이페이지
    - 상품관리
    - 주문관리
    - 장바구니관리
    - 문의작성답변기능

## 개발환경

- Spring boot
- Jpa
- Mybatis
- JDK 11
- AWS
- Mysql
- Linux
- Tomcat 8.5
- Jsp, Css, Js
- BootStrap 4

## Team Members

- 박홍진
    - Github - https://github.com/hongjinpark
    - Mail - ektkfk180@naver.com

- 권혁민
    - Github - https://github.com/hyeok-min
    - Mail - gurals04@naver.com

- 오선영
    - Github - https://github.com/xymnl
    - Mail - xymnl@naver.com

- 안광민
    - Github - https://github.com/AnGwangMin1
    - Mail - dksrhkd22@gmail.com
    

## ERD Diagram
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/Untitled.png">
</p>

## Main
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/readmeImg/main.PNG">
</p>

- Oauth2.0 을활용한 구글/네이버/카카오계정 로그인/회원가입 기능.
- 회원가입시 입력정보 조건에 알맞도록 제어.
- 회원페이지 정보조회 및 회원정보 수정/회원 탈퇴.
- 관리자계정으로 로그인시 주문/상품 제어권한 부여.
- 메인화면 상품 검색 기능

## Product
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/readmeImg/product.PNG">
</p>

- 상품 등록/수정/삭제 기능 **※ 수정, 삭제 기능은 추가예정**
- 상품 장바구니 추가/삭제 기능
- 상품 검색 기능

## MyPage
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/readmeImg/mypage.PNG">
</p>

- 마이페이지 사용자 주문/개인정보 수정, 회원탈퇴 기능
- 주문, 주문취소기능

## Order
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/readmeImg/order.PNG">
</p>

- 회원별 주문정보/주문 취소 기능 **※주문 취소 기능 수정**

## Cart
<p align="center">
  <img src="https://github.com/hongjinpark/shop/blob/master/readmeImg/cart.PNG">
</p>

- 장바구니 상품 주문/취소/개수 추가 기능
- 장바구니 상품 총 가격

## 배포 AWS

http://3.34.134.70:8080/

관리자 ID : admin@test.com

password :
