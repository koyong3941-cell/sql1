package com.kh.delivery.view;

import java.util.List;
import java.util.Scanner;

import com.kh.delivery.controller.MemberController;
import com.kh.delivery.controller.OrderController;
import com.kh.delivery.controller.RestaurantController;
import com.kh.delivery.dto.DeliMemberDto;
import com.kh.delivery.dto.OrderDto;
import com.kh.delivery.vo.MenuVo;
import com.kh.delivery.vo.RestaurantVo;


public class DeliveryMenu {
	private MemberController memberController = new MemberController();
	private RestaurantController restaurantController = new RestaurantController();
	private OrderController orderController = new OrderController();
	private static DeliMemberDto userGetName = null;
	private Scanner sc = new Scanner(System.in);
	
	public DeliveryMenu() {}

	
		public void mainMenu() {
			System.out.println("KH 배달 서비스입니다, 하단의 메뉴에서 선택해주세요.");
			System.out.println("1. 회원가입하러 가기\n2. 로그인하러 가기\n3. 전체 가게 목록 조회하러 가기\n4. 카테고리 별 검색하기\n5. 가게 별 메뉴 조회\n9. 쿠폰받기");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) { // 조회 기능은 비회원, 회원 메뉴 단에서 다 뿌려줘야함.
			case 1 -> signUp();
			case 2 -> selectLogin();
			case 3 -> selectAllRestaurant(); 
			case 4 -> selectByCategory();
			case 5 -> selectDetail();
			case 9 -> {System.out.println("비정상적인 접근입니다. 시설 폐쇄 및 핵 자폭 시퀀스 가동. 원자로 제어봉 강제 배출 완료.\n노심온도 상승 중(Current: 1,850°C)"
					+ "\n원자로 붕괴 가속화. 배달 자동화 시설 1클릭 내 모든 인원은 30초안에 대피해주세요.\n행운을 빕니다:)"); sc.close();}
			default -> System.out.println("잘못된 접근입니다.");
			}
		}
	
		private void signUp() { // 2개만 보내고 받을 때는 리턴값만 체크(날짜는 시스데이트로 처리)
			System.out.println("KH 배달 가입 서비스입니다. 하단의 필수값을 입력해주세요.");
			System.out.print("*아이디: ");
			String memberId = sc.nextLine();
			System.out.print("*비밀번호: ");
			String memberPw = sc.nextLine();
			System.out.print("*회원명: ");
			String memberName = sc.nextLine();
			System.out.print("주문 주소: ");
			String address = sc.nextLine();
						
			int result = memberController.signUp(new DeliMemberDto(memberId, memberPw, memberName, address));
			
			if(result > 0) {
				System.out.println("아이디 등록 성공!, 로그인 페이지로 진입합니다.");	
				selectLogin(); // 검증 완료 시 로그인 페이지 랜딩
			}
			else {
				System.out.println("아이디 등록 실패!");	
				mainMenu(); // 검증 실패 시 회원 페이지로 튕겨냄 
			}
		}

		private void selectLogin() {
			System.out.println("로그인 서비스입니다.");
			System.out.print("아이디를 입력해주세요: ");
			String memberId = sc.nextLine();
			System.out.print("비밀번호를 입력해주세요: ");
			String memberPw = sc.nextLine();
			
			DeliMemberDto login = new DeliMemberDto();
			login.setMemberId(memberId);
			login.setMemberPw(memberPw);
			
			DeliMemberDto delimemberDto = memberController.selectLogin(login);
			
				if(delimemberDto != null) {
					System.out.println("로그인 성공");
					this.userGetName = delimemberDto;
					userMenu();
				}
				else {
					System.out.println("로그인 실패");
					selectLogin();
				}
		}
		
		private void userMenu() { //유저  > 유저 메뉴 메서드 검색용 키워드
			System.out.println("안녕하세요, " + userGetName.getMemberName() +"님 KH 배달 서비스입니다, 하단의 메뉴에서 선택해주세요.");
			System.out.println("1. 전체 가게 목록 조회하기\n2. 카테고리 별 검색하기\n3. 가게 별 메뉴 조회\n4. 주문하기\n5. 주문 내역\n6. 주문 취소하기\n9. 로그아웃");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 -> selectAllRestaurant();
			case 2 -> selectByCategory();
			case 3 -> selectDetail();
			case 4 -> insertOrder();
			case 5 -> selectMyOrder();
			case 6 -> orderCancel();
			case 9 -> {System.out.println(userGetName.getMemberName() + "님의 계정 로그아웃을 실행합니다."); mainMenu();}
			default -> System.out.println("잘못된 접근입니다.");
			}
		}
		
		private void selectAllRestaurant(){ 
			int tryNum = 0;
			System.out.println("조회 목록입니다.");
			List<RestaurantVo> restaurantList = restaurantController.selectAllRestaurant();
			
				if(restaurantList.isEmpty()) {
					System.out.println("조회된 결과가 없습니다. 회원 메인 화면으로 이동합니다.");
					selectLogin();
				}
				else {
				for(RestaurantVo resVo : restaurantList) {
					System.out.println("#"+resVo.getRestNo()
					+ " 가게명: " + resVo.getRestName()
					+ " 카테고리: " + resVo.getCategory()
					+ " 최소 주문 가격: " +resVo.getMinPrice()
					+ " 배달비: " +resVo.getDeliveryFee());
					}
				}
			
			System.out.print("조회 끝\n1.돌아가기 2. 다시 조회하기 9. 종료 |선택: ");
			tryNum = sc.nextInt();
			sc.nextLine();
			
				if(userGetName == null) { // 비회원
					switch(tryNum) { // 선택한 메뉴를 제외하고 공란 혹은 오기입시 전체 메뉴 던짐
					case 1 -> mainMenu();
					case 2 -> selectAllRestaurant();
					case 9 -> sc.close();
					default -> System.out.println("잘못된 접근입니다.");}
					
				} else if(userGetName.getMemberId() != null) { // 회원
					switch(tryNum) { 
					case 1 -> userMenu(); 
					case 2 -> selectAllRestaurant();
					case 9 -> sc.close();
					default -> System.out.println("잘못된 접근입니다.");}
				}
		}
		
		private void selectByCategory() {
			String category = null;
			String menu = null;
		
			// 번호 입력 시 단건 주문 노출 및 공란으로 선택 시 상단의 전체조회 목록 메서드 호출
			System.out.println("음식 카테고리로 선택해주세요, 미입력 혹은 카테고리 오기입 시 전체 목록을 조회합니다.\n1. 일식\n2. 피자\n3. 중식\n4. 치킨\n5. 분식");
			System.out.print("*카테고리: ");
			menu = sc.nextLine();
			//메뉴 string으로 바꾸고 isblank처리해서 로직검사
				if(menu.isBlank()){
					System.out.println("지정된 카테고리가 없습니다. 전체결과를 조회합니다..");
					selectAllRestaurant();
					return;
				}
					else {
					switch(menu) { // 선택한 메뉴를 제외하고 공란 혹은 오기입시 전체 메뉴 던짐 > 인트형, 스트링형 검색 메서드 2개 생성 후 호출해도 되는데 시간없어서 패스
					case "1" -> category = "일식";
					case "2" -> category = "피자";
					case "3" -> category = "중식";
					case "4" -> category = "치킨";
					case "5" -> category = "분식";
					case "일식" -> category = "일식";
					case "피자" -> category = "피자";
					case "중식" -> category = "중식";
					case "치킨" -> category = "치킨";
					case "분식" -> category = "분식";
					default -> {System.out.println("지정된 카테고리가 없습니다. 전체결과를 조회합니다.."); return;}
					}
					}
			List<RestaurantVo> restaurantList = restaurantController.selectByCategory(category);
			
				if(restaurantList.isEmpty()) {
					System.out.println("조회된 결과가 없습니다. \n회원 메인 화면으로 이동합니다.");
					selectLogin();
				}
				else  {
					for(RestaurantVo resVo : restaurantList) {
						System.out.println("#"+resVo.getRestNo()
						+ " 가게명: " + resVo.getRestName()
						+ " 카테고리: " + resVo.getCategory()
						+ " 최소 주문 가격: " + resVo.getMinPrice()
						+ " 배달비: " + resVo.getDeliveryFee());
						}
					}
				}
			
		private void selectAllRestaurantProxy() {
			List<RestaurantVo> restaurantList = restaurantController.selectAllRestaurantProxy();
			if(restaurantList.isEmpty()) {
				System.out.println("조회된 결과가 없습니다. \n회원 메인 화면으로 이동합니다.");
				selectLogin();
				}
			else {
			for(RestaurantVo resVo : restaurantList) {
				System.out.println("#"+resVo.getRestNo()
				+ " 가게명: " + resVo.getRestName()
				+ " 카테고리: " + resVo.getCategory()
				+ " 최소 주문 가격: " + resVo.getMinPrice()
				+ " 배달비: " + resVo.getDeliveryFee());
				}
			}
		}
		
		private void selectDetail() {
		    selectAllRestaurantProxy();
		    String menu = null;
		    int restNo = 0;
		    
		    System.out.println("조회된 결과에서 가게 번호를 선택해주세요: ");	   
			    try {
			    restNo = Integer.parseInt(sc.nextLine());}
			    catch(NumberFormatException e) {
			    	System.out.print("잘못된 입력입니다.");
			    }
			    
		    List<MenuVo> menuList = restaurantController.selectDetail(restNo);	    
		    if(menuList.isEmpty()) {
		        System.out.println("조회된 결과가 없습니다. \n회원 메인 화면으로 이동합니다.");
		        selectLogin();
		        return;
		    }
		    
		    for(MenuVo menuVo : menuList) {
		        String status = menuVo.isSoldOut() ? "판매 소진" : "판매 중";
		        System.out.println("#" + menuVo.getMenuNo()
		            + " 메뉴명: " + menuVo.getMenuName()
		            + " 가격: " + menuVo.getPrice()
		            + " 매진 여부 : " + status);
		    }
		}
		
		private void insertOrder() {
			OrderDto order = new OrderDto();
			MenuVo menuVo = new MenuVo();
			
			selectDetail(); // 리스트 받아옴
			System.out.print("메뉴 번호를 선택해주세요: ");
			int menuNo =sc.nextInt();
			sc.nextLine();
			System.out.print("수량을 선택해주세요: ");
			int quantity =sc.nextInt();
			sc.nextLine();
			
			boolean isSoldOut = orderController.checkSoldOut(menuNo);
			
				if (isSoldOut) {
			        System.out.println("해당 메뉴는 현재 판매 소진되어 구매가 불가능합니다.");
			        return;
			    }
				else {
				order.setMenuNo(menuNo);
				order.setQuantity(quantity);
			
					if (userGetName != null) {
				        int userNo = userGetName.getMemberNo(); 
				        order.setMemberNo(userNo); }
					
					int result = orderController.insertOrder(order);
					
					if(result > 0){
						System.out.println("주문 성공!");
						userMenu();
					} else {
						System.out.println("주문 실패");	
						selectLogin();}
				}	
		}
		
		public void selectMyOrder() { // userGetName로 띄운 멤버 id를 바탕으로 내 주문 join 쿼리를 자동으로 띄워줘야함
			OrderDto order = new OrderDto();
			if (userGetName.getMemberId() != null) {
		        int userNo = userGetName.getMemberNo(); 
		        order.setMemberNo(userNo); //오더Dto에 유저넘 넣기

			List<OrderDto> myOrderList = orderController.selectMyOrder(userNo);
			
			if(myOrderList.isEmpty()) {
				System.out.println("조회된 결과가 없습니다. 회원 메인 화면으로 이동합니다.");
				} else {
						System.out.println(userGetName.getMemberName()+ "님의" +" 주문 내역 목록입니다.");
						for(OrderDto ordto : myOrderList) {
							if (ordto == null || ordto.getOrderNo() ==0) { // npe 방어 로직, 없으면 터짐
								System.out.println("조회된 결과가 없습니다.");
								return; 
					        }
							System.out.println("주문번호: " + ordto.getOrderNo()
							+ " 가게명: " + ordto.getRestName()
							+ " 메뉴명: " + ordto.getMenuName()
							+ " 수량: " + ordto.getQuantity()
							+ " 주문 가격: " +	 (ordto.getPrice()*ordto.getQuantity())					
							+ " 총 주문 가격: " + (ordto.getPrice()*ordto.getQuantity()+ordto.getDeliveryFee())
							+ " 주문 상태: " + ordto.getStatus()); // 가격 & 가격 * 수량 가격*수량+배달비?												
								}
					 		}	
						}
					}
		
		public void orderCancel() { //주문번호와 내 회원키(static으로 띄움)가 일치하는 주문에 대해서만 업데이트 처리가 가능해야함.
			if(userGetName == null) {
				System.out.println("잘못된 접근입니다.");
			}
			
			int orderNo = 0;
			selectMyOrder();
			System.out.print("취소할 주문의 번호를 입력해주세요, [주문접수] 상태의 주문 만 가능합니다: ");
			orderNo = sc.nextInt();
			sc.nextLine();
			
			int result = orderController.orderCancel(orderNo , userGetName.getMemberNo());
				
				if(result>0) {
					System.out.println("주문 취소 성공, 영업일 기준 7일 이내에 환불이 진행됩니다.");
				} else {
					System.out.println("주문 취소 실패, [주문접수] 상태가 아니거나 잘못된 주문번호입니다..");
				}
			}
			
	}

		

