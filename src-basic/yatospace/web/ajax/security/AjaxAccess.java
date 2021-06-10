package yatospace.web.ajax.security;

/**
 * Општи модел за објекте права приступа при АЈАКСу. 
 * @author MV
 * @version 1.0
 */
public abstract class AjaxAccess {
	public abstract boolean canAccess(); 
}
