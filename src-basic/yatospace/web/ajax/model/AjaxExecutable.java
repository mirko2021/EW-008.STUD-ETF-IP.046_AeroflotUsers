package yatospace.web.ajax.model;

import yatospace.web.ajax.element.AjaxRequestContext;

/**
 * Извршни елементи, обично зрна са функцијом 
 * попуњавања и узимања података са истих. 
 * Обрађује их контрола за АЈАКС примпредају захтијева. 
 * @author MV
 * @version 1.0
 */
public interface AjaxExecutable {
	public void importFrom(AjaxRequestContext request); 
	public void exportTo(AjaxRequestContext request);
}
