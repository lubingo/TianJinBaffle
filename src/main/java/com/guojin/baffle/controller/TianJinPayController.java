package com.guojin.baffle.controller;

import com.guojin.baffle.thread.ExecutorServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/9/4 16:15
 * @since
 */
@Slf4j
@RestController
@RequestMapping("/tianjin")
public class TianJinPayController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/payBack" ,method = {RequestMethod.GET,RequestMethod.POST})
    public String payBack(String str){

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();

        StringBuilder stringBuilder = new StringBuilder() ;
        stringBuilder.append("AcqSsn=999999999999").append("~|~") ;
        stringBuilder.append("MercCode=201909090001").append("~|~") ;
        stringBuilder.append(str).append("~|~") ;
        stringBuilder.append("MercDtTm=20190909").append("~|~") ;
        stringBuilder.append("RespCode=ZF0000").append("~|~") ;
        stringBuilder.append("SettDate=20190909").append("~|~") ;
        stringBuilder.append("TermCode=").append("~|~") ;
        stringBuilder.append("TranAmt=1").append("~|~") ;
        stringBuilder.append("TransName=QPAR")  ;


        paramMap.add("Plain",stringBuilder.toString());
        restTemplate.postForObject("http://192.168.17.73:7010/api/tjpay/baffleUse" ,paramMap,String.class);
        log.info("调用挡板成功！");
        return "\"https://testtjbank.guojingold.com/template/paySuccess.html\"";
    }

    @RequestMapping(value = "/test" ,method = {RequestMethod.GET,RequestMethod.POST})
    public void test(int  count   ){
        ExecutorService eu = ExecutorServiceUtil.getInstance().getExecutorService()  ;
        for (int k = 0; k < count; k++) {

            for (int i = 0; i < 5; i++) {
                eu.submit(new Runnable() {
                    @Override
                    public void run() {
                        String ssss = "{'openid':'oOdykt0c5L0QaM8FgyAcYhlQ7N0Q','id':'551','payPrice':'1999.00','remark':'','redeemCode':'','province':'北京市','city':'北京市','county':'通州区','street':'附近的鸡蛋汉堡','deliveryStatus':1,'consignee':'陆兵','phone':'15810172080','isBill':0,'orderProductList':[{'productImg':'https://cpkimg.guojingold.com:443/productSpecThumbnail%2F229d0117-d059-47ad-a466-35624aae7ba5.jpg','productMainId':'128','number':'1','model':'普通版','price':'1999.00','productName':'无限宝石','id':'9941','incomeRatio':'15'}]}" ;
                        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
                        paramMap.add("order",ssss);
                        paramMap.add("channelNumber","eaa354qz365pkkdxmazpul4rpq91l0");
                        restTemplate.postForObject("https://testtjbank.guojingold.com/api/order/addOrderBean" ,paramMap,String.class);
                    }
                }) ;
            }
        }

        log.info("OK");
    }

    @RequestMapping(value = "/toPrint" ,method = {RequestMethod.GET,RequestMethod.POST})
    public void toPrint(String fileType , String printName , String token , String fileUrls ){
        log.info("fileType={}",fileType);log.info("printName={}",printName);log.info("fileUrls={}",fileUrls);
        String[] strList = fileUrls.split(",") ;
        Map  map = new HashMap();
        map.put("fileType",fileType);
        map.put("printName",printName);
        map.put("fileUrls",strList);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("msg" , map );
        restTemplate.postForObject("http://172.21.98.97:8085/print/invoiceOrDeliveryOrder" ,paramMap,String.class);
    }



}
