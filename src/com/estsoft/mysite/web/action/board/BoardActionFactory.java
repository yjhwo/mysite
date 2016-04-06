package com.estsoft.mysite.web.action.board;



import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("write".equals(actionName)) { 			// 게시물 입력
			action = new BoardAddAction();			
		}else if ("addForm".equals(actionName)) { 	// 게시물 입력 Form
			action = new BoardAddFormAction();			
		}else if("view".equals(actionName)){		// 게시물 view					
			action = new BoardViewAction();
		}else if("modifyForm".equals(actionName)){	// 게시물 수정 Form
			action = new BoardModifyFormAction();
		}else if("modify".equals(actionName)){		// 게시물 수정
			action = new BoardModifyAction();			
		}else if("delete".equals(actionName)){		// 게시물 삭제
			action = new BoardDeleteAction();			
		}else if("search".equals(actionName)){		// keyword 검색
			action = new BoardSearchAction();
		}else if("reply".equals(actionName)){		// 답글
			action = new BoardReplyAction();				
		}else{										// 게시물 리스트
			action = new BoardListAction();
		}
		
		return action;
	}

	
}
