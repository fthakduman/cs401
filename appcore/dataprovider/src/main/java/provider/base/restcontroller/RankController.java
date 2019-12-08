package provider.base.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import provider.base.model.RankRequest;
import provider.base.model.RankResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provider.base.util.CommonUtils;
import userapi.base.service.UserService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/v1")
public class RankController {

    @Autowired
    RankCreator rankCreator;

    @GetMapping("/rank/{month}")
    public ResponseEntity<List<RankResponse>> getByMonth(@RequestBody RankRequest request,@PathVariable String month) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
         rankCreator.setBankNames(banks);
         List<RankResponse> responses = new ArrayList<RankResponse>();
         List<RankResponse> rankResponses= rankCreator.getRank(2019, Month.valueOf(month));
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
    @PostMapping("/rank/{month}/{currencyDayOfMonthValue}")
    public ResponseEntity<List<RankResponse>> getByMonth(@RequestBody RankRequest request,@PathVariable String month, @PathVariable int currencyDayOfMonthValue) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRank(2019, Month.valueOf(month),currencyDayOfMonthValue);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
    @GetMapping("/rank/{month}/{currencyDayOfMonthValue}/{currencyHour}")
    public ResponseEntity<List<RankResponse>> getByMonth(@RequestBody RankRequest request, @PathVariable String month,@PathVariable int currencyDayOfMonthValue,@PathVariable int currencyHour) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRank(2019, Month.valueOf(month),currencyDayOfMonthValue,currencyHour);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
//    @GetMapping("/rank/lastweek")
//    public ResponseEntity<List<YKBRateImpl>> getByMonth( ) {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/lastday")
//    public ResponseEntity<List<YKBRateImpl>> getByMonth( int sdad) {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
    @GetMapping("/rank/last6h")
    public ResponseEntity<List<RankResponse>> getByLast6H(@RequestBody RankRequest request) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRankByHours(5);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
    @GetMapping("/rank/last12h")
    public ResponseEntity<List<RankResponse>> getByLast12H(@RequestBody RankRequest request) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRankByHours(11);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
    @GetMapping("/rank/last18h")
    public ResponseEntity<List<RankResponse>> getByLast18H(@RequestBody RankRequest request) {
        List<String> banks = new ArrayList<String>();
        request.getBankNames().forEach(names -> banks.add(names.get("bankName")));
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRankByHours(17);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }


}