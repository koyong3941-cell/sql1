package com.kh.delivery.controller;

import java.util.InputMismatchException;

import com.kh.delivery.dto.DeliMemberDto;
import com.kh.delivery.service.MemberService;
import com.kh.delivery.view.DeliveryMenu;
import com.kh.delivery.vo.RestaurantVo;

public class MemberController {
	// public static DeliMemberDto loginUser;
	MemberService memberService = new MemberService();
	
	public void start() {
		DeliveryMenu view = new DeliveryMenu();
		try {
			view.mainMenu();}
		catch(InputMismatchException e){
			System.out.println("잘못된 입력 값 입니다.");
			e.printStackTrace();
		}
	}
	
	public int signUp(DeliMemberDto memberDto) {
		return memberService.signUp(memberDto);
	}
	
	public DeliMemberDto selectLogin(DeliMemberDto memberDto) {
		return memberService.selectLogin(memberDto);
	}


}
