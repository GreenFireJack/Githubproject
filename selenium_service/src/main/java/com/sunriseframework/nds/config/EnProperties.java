package com.sunriseframework.nds.config;

/**
 * @Title 业务参数枚举类
 * @Description 描述业务参数的key，参数的key与NdsServiceApp.properties的key保存一致即可
 * @author chenhao
 * @version 1.0 2021-09-22
 */
public enum EnProperties {

	//登录
	REQUEST_URL("webDriver.request.url"),
	TO_LOGIN("document.element.toLoginBtn.className"),
	CHECK_LOG_WAY("document.element.checkLoginWay.linkTest"),
	USERNAME_INPUT("document.element.userNameInput.id"),
	PASSWORD_INPUT("document.element.passwordInput.id"),

	USERNAME("document.element.userNameInput.sendKeys"),
	PASSWORD("document.element.passwordInput.sendKeys"),
	LOGIN_SUBMIT("document.element.loginSubmitBtn.id"),
	LOGIN_SLIDE_VALIDATION_BTN("document.element.loginSlideValidationBtn.cssSelector"),

	LOGIN_IS_SUCCESS("login.success.flag"),
	BIG_IMG_PARENT_NODE("document.element.SlideValidation.bigImgParentNode.className"),
	BIG_IMG_XPATH("document.element.SlideValidation.bigImg.xpath"),
	BIG_IMG_FILENAME("document.element.SlideValidation.bigImg.fileName"),
	BIG_IMG_LOCALPATH("document.element.SlideValidation.bigImg.localPath"),
	TEMPLATE_IMG_LOCAL_PATH("document.element.SlideValidation.templateImg.localPath"),
	MOVE_DISTANCE_SCALE_RADIO("document.element.SlideValidation.distance.Scale.radio"),
	SLIDE_VALIDATION_PAUSE_TIME("SlideValidation.pauseTime"),

	//登录成功后搜索商品下单
	JDGOODS_SEARCH_PARENT_NODE_ID("document.element.JDgoodsSearch.parentNode.id"),
	JDGOODS_SEARCH_INPUT_XPATH("document.element.JDgoodsSearchInput.xpath"),
	JDGOODS_SEARCH_INPUT_SENDKEYS("document.element.JDgoodsSearchInput.sendKeys"),
	JDGOODS_SEARCH_BTN_XPATH("document.element.JDgoodsSearchBtn.xpath"),
	JDGOODS_WAITLOAD_TIME("document.element.JDgoodsWaitLoadTime"),
	JDGOODS_RESULTSET_ID("document.element.JDgoodsResultSet.id"),
	JDGOODS_RESULTSET_ITEM_XPATH("document.element.JDgoodsResultSet.item.xpath"),
	JOIN_SHOP_CARD_BTN_LINKTEXT("document.element.joinShopCartBtn.linkText"),
	TO_SHOP_CARD_ACCOUNT_BTN_LINKTEXT("document.element.toShopCartAccountBtn.linkText"),
	TO_ACCOUNT_BTN_LINKTEXT("document.element.toAccountBtn.linkText"),
	COMMIT_ORDER_BTN_ID("document.element.commitOrderBtn.id");


	private String key;

	private EnProperties(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
