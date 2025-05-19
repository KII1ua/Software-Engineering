# Software-Engineering


## Sequence Diagram
![Image](https://github.com/user-attachments/assets/15f5ce58-d7eb-42aa-a519-ce91f0d38e1d)
- 위 사진의 시퀀스 다이어그램은 카페 키오스크 시스템에서 손님이 메뉴를 선택하고 결제하는 과정을 나타냅니다.
- 참여자는 총 4명으로 구성되어 있습니다.
- Customer(손님) : 키오스크에서 메뉴를 선택하고 결제를 진행합니다.
- Kiosk(키오스크) : 손님의 선택한 메뉴 정보를 받아 결제 정보를 전달합니다.
- Payment(결제 대행사) : 결제 요청을 받아 승인 또는 실패를 처리하여 키오스크에 전달합니다.
- Kitchen(주방 모니터) : 결제가 완료된 주문 정보를 받아 준비 상태를 표시하며 메뉴를 만들었을 경우 주문 정보와 주방 모니터 정보를 수정합니다.
