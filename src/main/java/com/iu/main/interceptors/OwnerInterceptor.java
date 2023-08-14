package com.iu.main.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.main.board.BoardDTO;
import com.iu.main.member.MemberDTO;

public class OwnerInterceptor extends HandlerInterceptorAdapter{
	
//	dao가져오서 조회가능
	
//	게시판
//	수정 시 로그인한 유저와 작성자가 일치
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		메소드형식을 구분하지 않음
		// TODO Auto-generated method stub
	    String method = request.getMethod();
	    if(method.equals("POST")) {
	    	return;
	    }
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("login");
		Map<String, Object> map = modelAndView.getModel(); 
       
		BoardDTO boardDTO = (BoardDTO)map.get("bto");
		if(!boardDTO.getWriter().equals(memberDTO.getId())) {
			modelAndView.addObject("message", "작성자만 가능합니다");
			modelAndView.addObject("url", "./list");
		   	modelAndView.setViewName("commons/result"); 
		}
		
	}

}
