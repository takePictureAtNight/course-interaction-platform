/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package utils;

import org.apache.http.HttpStatus;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;



	private int code;

	private Boolean state;


	private String message;

	private Map<String,Object> data = new HashMap();

	private long count;
	
	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		R responseUtils = new R();
		responseUtils.setCode(0);
		responseUtils.setState(true);
		responseUtils.setMessage("成功");
		return responseUtils;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public Object get(String key){
		return super.get(key);
	}

	public R message(String message){
		this.setMessage(message);
		return this;
	}
	public R message(String message, Object ... objects){
		this.setMessage(MessageFormat.format(message, objects));
		return this;
	}

	public R data(Map<String,Object> map){
		this.setData(map);
		return this;
	}

	public R data(String key, Object value){
		this.data.put(key,value);
		return this;
	}

    public R count(long total) {
		this.setCount(count);
		return this;
    }

	public void setCode(int code) {
		this.code = code;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
