package com.estsoft.mysite.web.action.guestbook;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("add".equals(actionName)) { // 리스트에 추가
			action = new AddAction();			
		} else if("deleteform".equals(actionName)){	
			action = new DeleteFormAction();
		}else if ("delete".equals(actionName)) {	// 삭제
			action = new DeleteAction();
		}else if ("ajax".equals(actionName)) {	
			action = new AjaxAction();
		}else if ("ajax-list".equals(actionName)) {
			action = new AjaxListAction();
		}else if ("ajax-insert".equals(actionName)) {	
			action = new AjaxInsertAction();
		}else if ("ajax-delete".equals(actionName)) {	
			action = new AjaxDeleteAction();
		}else{	// list
			action = new ListAction();
		}
		
		return action;
	}

}
