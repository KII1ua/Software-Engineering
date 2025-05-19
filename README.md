# Software-Engineering


## Sequence Diagram 설명
![Image](https://github.com/user-attachments/assets/4996115c-5a87-44da-9af1-f0394fa2060d)
- 위 사진의 시퀀스 다이어그램은 카페 키오스크 시스템에서 손님이 메뉴를 선택하고 결제하는 과정을 자동화한 시스템입니다. 결제 완료 후 주방 모니터에 주문 정보를 전달하여 준비 상태를 확인할 수 있습니다.
### 구성요소
  - 액터(Actor) : 시스템과 상호작용하는 외부 요소로 손님이 해당됩니다.
  - 객체(Object) : 시스템 내부의 컴포넌트로, 키오스크, 결제 시스템, 주방 모니터가 객체로 표현됩니다.
  - 활성박스(Activation Box) : 객체가 활성 상태에서 실행 중임을 나타내는 직사각형 박스입니다.
  - 생명주기(Lifeline) : 객체의 존재와 상호작용이 시간에 따라 어떻게 이어지는지를 나타내는 수직적점선으로 객체의 생명주기를 표현하며 위에서 아래로 시간이 흐릅니다.
  - 메시지(Message) : 서로 다른 객체간의 상호작용 혹은 의사소통 통신을 정의하는 요소로 여기서는 메뉴 선택, 결제 요청, 주문 정보 전달 등이 있습니다.


### 단계적 설명
  1. 메뉴 선택 및 주문 생성 : 손님이 키오스크에서 메뉴를 선택하고 주문을 생성합니다.
  2. 주문 내역 확인 및 결제 요청 : 키오스크가 주문 내역을 손님에게 보여주고 결제 요청을 진행합니다.
  3. 결제 진행 : 키오스크가 결제 대행사와 통신하여 결제를 시도합니다.
  4. 결제 승인 결과 전달 : 이때 결제 대행사와 통신하여 결제가 성공했는지 실패했는지 여부에 따라 나뉩니다. 이때 결제가 성공했을 경우 키오스크는 주방 모니터에 주문 정보를 출력하고, 손님에게 영수증을 출력해줍니다. 하지만 실패했을 경우 키오스크는 손님에게 결제가 실패했다는 알림을 출력해줍니다.

<br><br><br>

## Sequence Diagram을 기반으로 구현 설명
  ### Entity
  1. Menu Entity
<img width="514" alt="Image" src="https://github.com/user-attachments/assets/c27e2bfa-3772-4c0b-b78f-31b55d196355" />

 - 메뉴 정보를 담고 있는 엔티티로 메뉴 이름, 가격, 기본키(Id)를 포함하고 있습니다.

<br><br>

  2. Order Entity
<img width="799" alt="Image" src="https://github.com/user-attachments/assets/838d7fcb-ac91-4473-a8d0-ad53369df5e6" />

 - 주문 정보를 담고 있는 엔티티로 여러 메뉴에 하나의 주문 정보가 선택될 수 있기 때문에 ManyToOne의 관계를 맺고 있습니다.(하나의 메뉴만 선택 가능함)
 - 주문 시간, 수량, 총 가격, 주문상태를 포함하고 있습니다.
 - 이때 주문이 생성되었을때 초기에는 음료를 만들어야하기 때문에 준비상태로 만들기위한 생성자를 생성하였다.

<br><br>

  3. Kitchen Entity
<img width="456" alt="Image" src="https://github.com/user-attachments/assets/25f1b1f5-2a61-4b64-811c-40cc3f33dd31" />

- 주방 모니터 정보를 담고 있는 엔티티로 하나의 주문에는 주방 모니터의 하나의 주문만 매핑될 수 있어 OneToOne 관계를 맺습니다. (한 주문은 하나의 모니터에만 출력가능)
- 현재 주문상태에 대한 정보를 포함하고 있습니다.
- Order과 마찬가지로 초기에 바로 주문자에게 제공할 수 없기 때문에 결제완료 상태로 만들기 위한 생성자를 생성하였다.

<br><br>

  4. OrderStatus
<img width="376" alt="Image" src="https://github.com/user-attachments/assets/e51da4fb-bbd9-442f-a628-01ea9efe60bb" />

- 주문상태 정보에 값으로 두개의 상태만 해당이 가능하기때문에(Paid(결제완료), Completed(준비완료)) 열거형(Enum)으로 선언하였습니다.

<br><br>

  5. KitchenStatus
<img width="358" alt="Image" src="https://github.com/user-attachments/assets/a22404ec-5827-469b-acec-e1dc336393be" />

- 주방상태 정보에 대한 값으로 두개의 상태만 해당이 가능하기때문에(Waiting(준비중), Ready(준비완료)) 열거형(Enum)으로 선언하였습니다.

<br><br><br>
---

  ### Service
  1. MenuService

![Image](https://github.com/user-attachments/assets/e832e71d-0c59-4bf9-9242-d931b4f9f90d)

- Menu 테이블에 저장된 모든 객체를 다 조회하는 기능으로 메뉴 엔티티의 기본 키, 메뉴 이름, 메뉴 가격 총 3개의 대한 정보를 출력합니다.

<br><br>

  2. OrderService

![Image](https://github.com/user-attachments/assets/dad16b85-9930-44e0-ac23-38ba86872c72)

- 사용자가 결제를 요청했을 때 성공적으로 결제가 완료됬을 경우 사용자가 선택한 메뉴, 개수 정보를 가지고 총합이 얼마인지 계산후 Order 테이블에 저장합니다.
- 이때 주문이 완료된 후 바로 주방 모니터에 출력하기 위해서 Kitchen 테이블에 저장한다.

<br><br>

  3. KitchenService
     
![Image](https://github.com/user-attachments/assets/f6f18625-90c6-4694-8bce-043ebac08c6d)

- 주방에서 조리되는 시간을 설정하여 주문완료후 2분 뒤에 준비완료된 상태로 만들기 위해 스케쥴러를 사용하였다.
- 이때 주문테이블도 완료된 상태여야 하기 때문에 같이 갱신을 해주었다.

<br><br><br>
---

  ### DTO
  1. FindByAllMenu

![Image](https://github.com/user-attachments/assets/44ac588d-db45-4b5d-9fe3-a1c62f51dd9f)

- Menu 테이블에서 따로 필요한 내용만 골라서 출력하기 위해서 DTO를 만들었다.

<br><br>

  2. OrderRequestDTO

![Image](https://github.com/user-attachments/assets/2837ce85-b13c-49b5-b1aa-b962d82ae2c4)

- 결제 요청을 할때 결제 고유번호, 주문 번호, 메뉴 정보가 필요하기 때문에 따로 DTO를 만들었다.

<br><br><br>
---

  ### Controller
  1. MenuController

![Image](https://github.com/user-attachments/assets/7a7b5f04-e929-44e5-b6d6-7aff6eeb9cbc)

  #### 결과화면

![Image](https://github.com/user-attachments/assets/9e4664d9-6827-41b4-a150-7121a0d239f2)

- MenuService 클래스의 findByAllMenu 메서드를 호출하는 api 엔드포인트 구조를 만들었다. /api/findByAllMenu 엔드포인트로 Get요청을 보내면 로컬에 저장되어 있는 Menu 테이블의 모든 정보가 출력된다.

<br><br>

  2. OrderController

![Image](https://github.com/user-attachments/assets/d16db2ef-8824-4986-8d1f-65da06a64b00)

- OrderService 클래스의 createOrder 메서드를 호출하는 api 엔드포인트로 사용자가 결제를 하고 성공했을 경우 /api/paymentOrder 엔드포인트에 Post요청을 보내 Order테이블과 Kitchen 테이블에 사용자가 선택한 메뉴의 정보가 저장이된다.

<br><br>

  3. HomeController

![Image](https://github.com/user-attachments/assets/267516c3-ca26-4c88-80ce-58c367379d5f)

- 결제 페이지를 만들기위해서 localhost:8080 루트 경로에 index.html을 렌더링하기위한 컨트롤러이다.

  #### 결과화면
  ![Image](https://github.com/user-attachments/assets/53d44404-cdd7-46fe-a4ce-831e7dccb119)

  ![Image](https://github.com/user-attachments/assets/fad129e9-df33-408a-ac38-ad3449d1c868)

  ![Image](https://github.com/user-attachments/assets/9a0b83aa-fc6b-4e01-b13e-dd1e25d3e6b2)
  
<br><br><br>

  ### config
  1. MenuInitializer

![Image](https://github.com/user-attachments/assets/867f3da5-5a74-4941-af10-0ba9c5168824)

- 초기에 메뉴 데이터들을 DB에 넣어주기 위한 클래스로 spring boot를 실행할때 유효성검사를 체크한 후 데이터가 없을경우 넣어준다.

<br><br>

# HOW TO USE🚀

1. git pull을 먼저 받는다.

![Image](https://github.com/user-attachments/assets/507c9a06-3809-4bff-b011-25a9f4b94a04)

<br>

2. 따로 빈 디렉토리에 origin을 추가하여 pull을 받는다.

![Image](https://github.com/user-attachments/assets/5ed2f30e-a7cc-49e9-ab4e-083ded5181f6)

<br>

```
git init
git remote add origin https://github.com/KII1ua/Software-Engineering.git
git pull origin main
```

3. application.yml 수정

![Image](https://github.com/user-attachments/assets/82865402-45c9-440a-9297-8c8b146146f5)

- 경로 demo/src/main/resources

<br>

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/kiosk
    username: {로컬에 설치되어 있는 mysql username 입력}
    password: {mysql username에 대응하는 password 입력}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        jdbc:
          time_zone: Asia/Seoul
    #    database-platform:
```

<br>

4. MySql에 접속하여 kiosk 데이터베이스 생성

```
create database kiosk;
```

5. demo 폴더로 이동하여 클린 빌드

```
./gradlew clean build
```

![Image](https://github.com/user-attachments/assets/cb57c408-07d5-43c2-96cf-62f73ff56aaf)
ㅁ
- 위 사진이 나오면 빌드 완료

<br>

6. build/libs 폴더로 이동하여 jar 파일 실행

```
java -jar demo-0.0.1-SNAPSHOT.jar
```

- 프로그램이 실행되면 이후 localhost:8080 으로 접속하여 테스트 결제 진행

<br><br><br>

---

### 샘플 코드 모듈 평가 결과

