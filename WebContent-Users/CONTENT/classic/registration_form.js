"UTF-8"

/**
 *  Јаваскрипт за форме које су услућне за разне провејре на фоамама регистрације. 
 */

function testPasswords(){
	var password1 = document.getElementById("register_form_password").value; 
	var password2 = document.getElementById("register_form_password2").value;
	if(password1!=password2){
		/*Корисник није регистрован. Лозинке се не слажу.*/
		alert('\u041a\u043e\u0440\u0438\u0441\u043d\u0438\u043a \u043d\u0438\u0458\u0435 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u043e\u0432\u0430\u043d\u002e \u041b\u043e\u0437\u0438\u043d\u043a\u0435 \u0441\u0435 \u043d\u0435 \u0441\u043b\u0430\u0436\u0443\u002e'); 
		return false; 
	}
	return true;
}