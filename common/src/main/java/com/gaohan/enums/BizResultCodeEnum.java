package com.gaohan.enums;

/**
 * 操作返回码枚举
 *
 */
public enum BizResultCodeEnum {

	/* 操作成功 */
	SUCCESS("SUCCESS", 1000, "操作成功"),

	/* 系统错误 */
	SYSTEM_FAILURE("SYSTEM_FAILURE", 1001, "系统错误，稍后再试"),

	/* 参数为空 */
	NULL_ARGUMENT("NULL_ARGUMENT", 1002, "参数为空"),

	/* 新增的参数已经存在(唯一性约束) */
	DUPLICATE_KEY("DUPLICATE_KEY", 1003, "新增的参数已经存在(唯一性约束)"),

	/* 参数不正确 */
	ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", 1004, "参数不正确"),

	/* 逻辑错误 */
	LOGIC_ERROR("LOGIC_ERROR", 1005, "逻辑错误"),

	/* 外部接口调用失败 */
	DEPEND_FAILURE("DEPEND_FAILURE", 1006, "依赖外部接口失败"),

	/* 认证失败 */
	AUTHORIZE_FAILURE("AUTHORIZE_FAILURE", 1007, "用户认证失败"),

	/* 连接超时 */
	SESSION_TIMEOUT("SESSION_TIMEOUT", 1008, "会话连接超时"),

	/* 校验失败 */
	VALIDATE_FAILURE("VALIDATE_FAILURE", 1009, "校验失败"),

	/* 重复提交错误 */
	RESUBMIT_ERROR("RESUBMIT_ERROR", 1010, "重复提交错误"),

	/* 数据不存在 */
	NOT_EXISTED("NOT_EXISTED", 1011, "数据不存在"),

	/* 用户未激活 */
	USR_NOT_ACTIVE("USR_NOT_ACTIVE", 1012, "用户未激活"),

	/* 用户已注销 */
	USR_CLOSED("USR_CLOSED", 1013, "用户已注销"),
	
	USE_PWD_ERROR("USE_PWD_ERROR", 1014, "密码不正确"),

	USER_TYPE_ERROR("USER_TYPE_ERROR", 1015, "用户类型不正确"),

	USER_NOT_EXISTED("USER_NOT_EXISTED",1016,"用户不存在"),

	/* =================== 订单相关错误(20**) ================== */

	/* 订单未找到 */
	ORDER_NOT_FOUND("ORDER_NOT_FOUND", 2001, "订单未找到"),

	/* 订单未支付 */
	ORDER_NOT_PAID("ORDER_NOT_PAID", 2002, "订单未支付"),
	
	/* 订单类型不存在*/
	ORDER_TYPE_NOT_FOUND("ORDER_TYPE_NOT_FOUND", 2003, "订单类型不存在"),
	
	/* 支付编码不存在*/
	PAY_CODE_NOT_FOUND("PAY_CODE_NOT_FOUND", 2004, "支付编码不存在"),

	/* 商品订单未找到 */
	ORDER_GOODS_NOT_FOUND("ORDER_GOODS_NOT_FOUND", 2005, "商品订单未找到"),

	/* 订单类型为空 */
	ORDER_TYPE_NULLABLE("ORDER_TYPE_NULLABLE",2006,"订单类型为空"),

	/* 订单编号为空 */
	ORDER_SN_NULLABLE("ORDER_SN_NULLABLE",2007,"订单编号为空"),

	/* 订单信息和用户信息不匹配 */
	ORDER_USER_UNMACTHING("ORDER_USER_UNMACTHING",2008,"订单信息和用户信息不匹配"),

	/* 非法的取消订单的订单状态 */
	ILLLEGAL_CANCEL_ORDER_STATUS("ILLLEGAL_CANCEL_ORDER_STATUS",2009,"非法的取消订单的订单状态"),

	/* 非法的确认收货的订单状态 */
	ILLLEGAL_CONFIRM_ORDER_STATUS("ILLLEGAL_CONFIRM_ORDER_STATUS",2010,"非法的确认收货的订单状态"),

	/* 发货包已存在 */
	PACKAGE_EXISTS("PACKAGE_EXISTS", 2101, "发货包已经存在"),

	/* 发货包不存在 */
	PACKAGE_NOT_EXISTS("PACKAGE_NOT_EXISTS", 2102, "发货包不存在"),

	PACKAGE_GOODS_EMPTY("PACKAGE_GOODS_EMPTY", 2103, "发货包商品为空"),
	


	/* =================== 产品相关错误(30**) ================== */

	/* 库存不足 */
	STOCK_NOT_ENOUGH("STOCK_NOT_ENOUGH", 3000, "库存不足"),

	/* 商品数量不正确 */
	GOODS_NUM_INCORRECT("GOODS_NUM_INCORRECT", 3001, "商品数量不正确"),

	/* 库存更新失败 */
	STOCK_UPDATE_FAIL("STOCK_UPDATE_FAIL", 3002, "库存更新失败"),

	/* 库存信息不存在 */
	STOCK_NO_EXIST("STOCK_NO_EXIST", 3003, "库存信息不存在"),

	/* 被占数量不正确 */
	OCCUPIED_NUM_INCORRECT("OCCUPIED_NUM_INCORRECT", 3004, "被占数量不正确"),

	/* 真实库存数量不正确 */
	REAL_NUM_INCORRECT("REAL_NUM_INCORRECT", 3005, "真实库存数量不正确"),

	/* 订单信息不存在 */
	ORDER_NOT_EXIST("ORDER_NOT_EXIST", 3006, "订单信息不存在"),

	/* 库存信息不完整 */
	STOCK_NOT_COMPLETE("STOCK_NOT_COMPLETE", 3007, "库存信息不完整"),

	/* 订单与商品库存信息不匹配 */
	ORDER_STOCK_NOT_MATCH("ORDER_STOCK_NOT_MATCH", 3008, "订单与商品库存信息不匹配"),

	/* 入库单已存在 */
	ASN_IS_EXIST("ASN_IS_EXIST", 3009, "入库单已存在"),

	/* 入库单已存在 */
	PARAMS_IN_VALID("PARAMS_IN_VALID", 3010, "sku,spu,item,goodsSn必须要存在一个"),

	/* 入库单商品信息不完整 */
	ERR_ASN_GOODS_NOT_COMPLETE("ERR_ASN_GOODS_NOT_COMPLETE", 3011, "入库单商品信息不完整"),

	/* =================== 支付相关错误(40**) ================== */
	/* v券不足 */
	VQUAN_NOT_ENOUGH("VQUAN_NOT_ENOUGH", 4001, "v券不足"),

	/* v币不足 */
	CREDIT_NOT_ENOUGH("CREDIT_NOT_ENOUGH", 4002, "v币不足"),

	/* v券和v币都不足 */
	VQUAN_AND_CREDIT_NOT_ENOUGH("VQUAN_AND_CREDIT_NOT_ENOUGH", 4003, "v券和v币都不足"),

	/* 实际库存不足 */
	REAL_STOCK_NOT_ENOUGH("REAL_STOCK_NOT_ENOUGH", 3000, "实际库存不足"),

	THIRD_PARTY_REDIRECT("THIRD_PARTY_REDIRECT", 302, "请跳转"),

	VERIFY_NOTIFY_PARAM_FAILED("VERIFY_NOTIFY_PARAM_FAILED", 4002,
			"第三方通知回调参数校验失败"),

	TRADE_NO_EXIST("TRADE_NO_EXIST", 4003, "交易号已存在"),

	ALREADY_PAYED("ALREADY_PAYED", 4004, "该笔订单已经支付过了"),

	/* ====================短信相关错误（50**）========================== */

	SMS_LOGIN_ERROR("SMS_LOGIN_ERROR", 5001, "有参数为空"),

	SMS_MOBILE_ERROR("SMS_MOBILE_ERROR", 5002, "电话号码异常"),

	SMS_MOBILE_NUMBER_ERROR("SMS_MOBILE_NUMBER_ERROR", 5003, "电话号码数量不正确"),

	SMS_SYSTEM_ERROR("SMS_SYSTEM_ERROR", 5004, "短信服务商处异常"),

	/* =======================签名验证============================ */
	SIGN_CHECK_ERROR("SIGN_CHECK_ERROR", 6000, "签名不正确"),

	REQUEST_TIME_OUT("REQUEST_TIME_OUT", 6001, "请求超时"), 

	/* =======================积分相关============================ */
	JIFEN_INSERT_ERROR("JIFEN_INSERT_ERROR", 7000, "积分明细插入失败"), 
	
	JIFEN_CONSUME_ERROR("JIFEN_COMSUME_ERROR", 7001, "消费积分大于用户总积分"), ;

	/**
	 * 枚举值
	 */
	private final String code;

	/**
	 * 数值型错误码
	 */
	private final int codeNumber;

	/**
	 * 枚举信息
	 */
	private final String description;

	/**
	 * 构造函数
	 *
	 * @param code
	 *            枚举值
	 * @param codeNumber
	 *            数值型错误码
	 * @param description
	 *            枚举信息
	 */
	BizResultCodeEnum(String code, int codeNumber, String description) {
		this.code = code;
		this.codeNumber = codeNumber;
		this.description = description;
	}

	/**
	 * 根据code获取枚举
	 *
	 * @param code
	 * @return
	 */
	public static BizResultCodeEnum getByCode(String code) {
		for (BizResultCodeEnum item : values()) {
			if (code.equals(item.getCode())) {
				return item;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public int getCodeNumber() {
		return codeNumber;
	}
}
