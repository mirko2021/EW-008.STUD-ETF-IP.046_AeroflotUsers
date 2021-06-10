package yatospace.web.ajax.error;

/**
 * Грешке услед изузетака с ајаксом. 
 * @author MV
 * @version 1.0
 */
public class AjaxException extends RuntimeException{
	private static final long serialVersionUID = 5161392224370157059L;

	public AjaxException() {
		super();
	}

	public AjaxException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AjaxException(String arg0) {
		super(arg0);
	}

	public AjaxException(Throwable arg0) {
		super(arg0);
	}
}
